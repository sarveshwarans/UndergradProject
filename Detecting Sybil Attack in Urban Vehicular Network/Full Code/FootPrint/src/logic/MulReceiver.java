package logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.xml.namespace.QName;

public class MulReceiver extends Thread  {

    public String rsu_id,rsu_port,sample;
    public int minval=0,maxval=0,cou=0;
    public valueobject vo = new valueobject();
    public String value=null;
    public Vector addObu=null;
    public Vector keyObu=new Vector();
    public String Key="";
    String rsuSys="";
    public String AllInfo;
    public Vector ObuDestnation=new Vector();
    public HashMap hs=new HashMap();
    public HashMap addobu1=new HashMap();
    public Properties pro;
    public FileInputStream fis;
    public String address1;




    public MulReceiver()
    {     }
    public void setdetailes(String rsuid,String rsuport,int minval1,int maxval1) throws Exception//send RSU Information
    {
        addObu=new Vector();
        addobu1=new HashMap();
        this.rsu_id=rsuid;
        this.rsu_port=rsuport;
        rsuSys=InetAddress.getLocalHost().getHostName().toString();
        this.minval=minval1;
        this.maxval=maxval1;
        AllInfo = rsu_id+"->"+rsuSys+"->"+rsu_port+"->"+String.valueOf(minval1)+"->"+String.valueOf(maxval1);

        start();
    }
    public void run()
    {
        try
        {
            while(true)
            {
               
                InetAddress ina=InetAddress.getByName("228.27.55.88");
                MulticastSocket ms=new MulticastSocket(4574);
                ms.joinGroup(ina);
                byte ba[]=new byte[1024];
                DatagramPacket pack=new DatagramPacket(ba,ba.length);
                ms.receive(pack);
                String deta=new String(pack.getData()).trim();
                StringTokenizer str = new StringTokenizer(deta,"@");
                String ObuName = str.nextToken().toString().trim();
                String ObuSys = str.nextToken().toString().trim();
                String ObuPort = str.nextToken().toString().trim();
                String ObiDis = str.nextToken().toString().trim();

                  pro=new Properties();
                  fis=new FileInputStream("address.properties");
                  System.out.println("Entered the read by anand");
                  try
                  {
                      pro.load(fis);
                      System.out.println("before  by andand");
                      address1=pro.getProperty("address");
                      System.out.println("Checking the address inside the proprty"+address1);
                      fis.close();
                  }
                  catch (IOException ex)
                  {
                       ex.printStackTrace();
                  }
                 
                if(hs.containsKey(ObuName))
                {}else
                {
                      String ObuDet=ObuSys+"->"+ObuPort;
                      hs.put(ObuName, ObuDet);
                }
                if(Integer.parseInt(ObiDis)>=minval && Integer.parseInt(ObiDis)<=maxval)
                {

                    if(!addObu.contains(ObuName)){
                        addObu.add(ObuName);
                        String syspo=ObuSys+"->"+ObuPort;
                        addobu1.put(ObuName, syspo);
                    }

                    Socket sock= new Socket(ObuSys,Integer.parseInt(ObuPort));
                    ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
                    oos.writeObject("RSUINFO");
                    oos.writeObject(AllInfo);
                    System.out.println("");
                    oos.writeObject("Net");
                    oos.writeObject(ObiDis);
                    oos.writeObject(Key);
                    oos.writeObject(hs);
                    //oos.writeObject(hs);
                    sock.close();
                    oos.close();
                    System.out.println("Before the socket send"+address1);
                    Socket sock2= new Socket(address1,6767);
                    ObjectOutputStream oos2 = new ObjectOutputStream(sock2.getOutputStream());
                    oos2.writeObject("RSUINFOR");

                    oos2.writeObject(AllInfo);
                    oos2.writeObject(addobu1);
                    oos2.writeObject(rsu_id);
                    oos2.close();
                   
                }
                else
                {

                    if(addObu.contains(ObuName)){

                            if(addObu.size()==1){
                                 addObu=new Vector();
                                 addobu1=new HashMap();
                            }
                            else{
                                addObu.remove(ObuName);
                                addobu1.remove(ObuName);
                            }
                    }
                
                    Socket sock1= new Socket(ObuSys,Integer.parseInt(ObuPort));
                    ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                    oos1.writeObject("RSUINFO");
                    oos1.writeObject(AllInfo);
                    oos1.writeObject("NoNet");
                    oos1.writeObject(ObiDis);
                    oos1.writeObject("--");
                    oos1.writeObject(hs);
                    sock1.close();
                    oos1.close();
                    Socket sock2= new Socket(address1,6767);
                    ObjectOutputStream oos2 = new ObjectOutputStream(sock2.getOutputStream());
                    oos2.writeObject("RSUINFOR");
                    oos2.writeObject(AllInfo);
                    oos2.writeObject(addobu1);
                    oos2.writeObject("NoNet");
                    oos2.close();

                }
                String name="";
                for(int i=0;i<addObu.size();i++){

                    name+=addObu.get(i)+"\n";
                }
                vo.setObu(name);

             }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

}