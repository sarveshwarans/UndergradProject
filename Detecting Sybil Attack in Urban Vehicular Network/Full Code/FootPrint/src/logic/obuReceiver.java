package logic;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;
import javafx.stage.Alert;
import javax.swing.JOptionPane;

    public class obuReceiver extends Thread {
    public String NameObu,vbdat,ALLOBU,webSerInfo="",sendInfo="";
    public int minVal,maxVal;
    public valueobject vo1 = new valueobject();
    public Vector addObu=new Vector();
    public HashMap allObuInfo=new HashMap();
    public Vector addReq=new Vector();
    public HashMap hm=new HashMap();
    public TreeMap AllRsuInfo=new TreeMap();
     public TreeMap AllObuInfo=new TreeMap();
    public String nam,sysnam,por;
    public String Key="";
    public String Key2="";
    private Object key2;
    public String Keychange="";
    public Vector keyObu=new Vector();
    public String obuDet,localsys,obuName,obuPort,obuDist,obuRange,obuMin,obuMax,obuSysName;
    public obuReceiver(String vname,String portno)//RSU Rec
    {
        NameObu=vname;
        vbdat=portno;
        
        start();
    }

   
    public void run()
    {
        try
        {
             ServerSocket ss=new ServerSocket(Integer.parseInt(vbdat));
             while(true)
             {
                    Socket s=ss.accept();
                    ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                    String rsuDET=(String)ois.readObject();
                    System.out.println("this is obu receiver---"+vbdat+"---"+rsuDET);
                    if(rsuDET.equals("RSUINFO"))
                    {

                    String rsuInfo = (String)ois.readObject();
                    StringTokenizer stok = new StringTokenizer(rsuInfo,"->");
                    nam = stok.nextToken().toString().trim();
                    sysnam = stok.nextToken().toString().trim();
                    por = stok.nextToken().toString().trim();
                    
                    String minRsu=stok.nextToken().toString().trim();
                    String maxRsu=stok.nextToken().toString().trim();
                    String syspor=minRsu+"->"+maxRsu+"->"+sysnam+"->"+por;
                    AllRsuInfo.put(nam, syspor);
                    String rsunet = (String)ois.readObject();
                    String changeDistObu = (String)ois.readObject();
                    String obuKey=(String)ois.readObject();
                    HashMap hs=(HashMap)ois.readObject();
                    allObuInfo=hs;
                   // System.out.println("List Of the OBU in the netrow######"+allObuInfo);

                    hm.put(nam, rsunet.trim());
                    if(!hm.containsValue("Net"))
                    {
                        vo1.setRsuInfo("NoNet");
                        vo1.setObuDist(changeDistObu);
                       // System.out.println("contai a dnan******#"+hm);
                       
                    }
                    else
                    {
                        Set set = hm.entrySet();
                        Iterator it = set.iterator();
                        while(it.hasNext())
                        {
                            Map.Entry me = (Map.Entry)it.next();
                            String RsuINFO = (String)me.getKey();                           
                            String val= me.getValue().toString();
                           
                            if(val.equals("Net"))
                            {
                                
                                vo1.setRsuInfo(RsuINFO);
                                if(keyObu.contains(RsuINFO))
                                {}else
                                {
                                  keyObu.add(RsuINFO);
                                }
                                System.out.println("Cuurrent OBU key value-------------"+obuKey);

                            }else{
                                  
                                 if(keyObu.contains(RsuINFO))
                                 {keyObu.remove(RsuINFO);} 

                            }
                        }
                      
                        vo1.setObuDist(changeDistObu);
                        
                    }
                 }
                 else if(rsuDET.equals("NORDETA"))
                    {
                        System.out.println("---------------NORDATA");
                        String recObuSour=(String)ois.readObject();
                        String recObuData=(String)ois.readObject();
                        vo1.setNormalDeta("FROM:"+recObuSour+"\n"+recObuData);

                    } else if(rsuDET.equals("SEND"))
                    {
                        System.out.println("---------------NORDATA11111111111111");
                        sendInfo=(String)ois.readObject();
                        
                        String sta=(String)ois.readObject();
                        vo1.setNotsend(sta+"\n"+sendInfo);

                    }else if(rsuDET.equals("SENDOBUKEY"))
                    {
                        System.out.println("---------------KEYrece");
                        String pubKey=(String)ois.readObject();
                        String priKey=(String)ois.readObject();
                        
                        if(!Keychange.equals(priKey)||Keychange.equals("")){
                            Key2=String.valueOf(Math.round(Math.random()*9000)+999);
                            Keychange=priKey;
                        }
                        vo1.setObuKeysend(pubKey+"\n"+Key2);
                
                    }

             }
         }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
     public void AttackSend(String name,String cont){ //Attack is perform
            String actname = JOptionPane.showInputDialog(null,"Enter the Attacker Vehicle name ");
            String tgsig = JOptionPane.showInputDialog(null,"Enter the Private Signature");
            if (actname.equalsIgnoreCase("")||tgsig.equalsIgnoreCase(""))
            {
              Alert.confirm("Please Enter the Input for Sybil Attack!!!");
            }
            System.out.println("Checking the name in anand"+actname+obuName+"Checking the key"+key2);
            if (actname.equalsIgnoreCase(obuName)) {
                tgsig.equals(key2);
                Alert.confirm("The Node signature is same");
            }else
            {
                Alert.confirm("This Vehicle Is A Attacker");
            }

             try
                {
                        Set set = hm.entrySet();
                        Iterator it = set.iterator();
                        while(it.hasNext())
                        {
                            Map.Entry me = (Map.Entry)it.next();
                            String RsuINFO = (String)me.getKey();

                            String val= me.getValue().toString();
                            if(val.equals("Net"))
                            {
                                String ss2=AllRsuInfo.get(RsuINFO).toString();

                                    StringTokenizer tok1 = new StringTokenizer(ss2,"->");
                                    String minVal = tok1.nextToken().toString();
                                    String maxVal = tok1.nextToken().toString();
                                    String SystemName = tok1.nextToken().toString();
                                    String portNum = tok1.nextToken().toString();

                                        Socket sock1= new Socket(SystemName,Integer.parseInt(portNum));
                                        ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                                        oos1.writeObject("ATTACKDATA");
                                        oos1.writeObject(obuName);;
                                        oos1.writeObject(key2);
//                                        oos1.writeObject(ObuData);
                                        oos1.writeObject(allObuInfo);
                                        oos1.writeObject(AllRsuInfo);

                            }
                        }


                    }

                catch(Exception e)
                  {
                  e.printStackTrace();
                    }



        }



        public void Obudatasend(String obuSo,String obuDe,String obuText)
            {
                 String ObuSour,ObuDest,ObuData;
                 ObuSour=obuSo;
                 ObuDest=obuDe;
                 ObuData=obuText;

                try
                {

                        Set set = hm.entrySet();
                        Iterator it = set.iterator();
                        while(it.hasNext())
                        {
                            Map.Entry me = (Map.Entry)it.next();
                            String RsuINFO = (String)me.getKey();

                            String val= me.getValue().toString();
                            if(val.equals("Net"))
                            {
                                String ss2=AllRsuInfo.get(RsuINFO).toString();

                                    StringTokenizer tok1 = new StringTokenizer(ss2,"->");
                                    String minVal = tok1.nextToken().toString();
                                    String maxVal = tok1.nextToken().toString();
                                    String SystemName = tok1.nextToken().toString();
                                    String portNum = tok1.nextToken().toString();

                                        Socket sock1= new Socket(SystemName,Integer.parseInt(portNum));
                                        ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                                        oos1.writeObject("OBUDATA");
                                        oos1.writeObject(ObuSour);;
                                        oos1.writeObject(ObuDest);
                                        oos1.writeObject(ObuData);
                                        oos1.writeObject(allObuInfo);
                                        oos1.writeObject(AllRsuInfo);
                               
                            }
                        }
                    
                        
                    }

                catch(Exception e)
                  {
                  e.printStackTrace();
                    }

            }

}
