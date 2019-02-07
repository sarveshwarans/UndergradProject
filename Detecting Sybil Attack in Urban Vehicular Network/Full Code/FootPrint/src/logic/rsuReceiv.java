package logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.lang.String;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JOptionPane;

public class rsuReceiv extends Thread {
    public String NameRsu,PortRsu;
    public int CurMinVal,CurMaxVal;
    public valueobject vo1 = new valueobject();
    public String obuRequest="",ss="",ObuName="",obuReq="";
    Vector obData2=new Vector();
    Vector obdat=new Vector();
    public HashMap AllOBU=new HashMap();
    public TreeMap AllRSUInfo=new TreeMap();
    public String name,port,Request,key,rsuName,sysName;
    HashMap rsuname=new HashMap();
    public HashMap thisObu=new HashMap();
    private String priiKey;




    public rsuReceiv(String vname,String portno,int minV,int maxV)
    {
        NameRsu=vname;
        PortRsu=portno;
        CurMinVal=minV;
        CurMaxVal=maxV;

        start();
    }


    public void run()
    {
        try
        {
             ServerSocket ss=new ServerSocket(Integer.parseInt(PortRsu));
             while(true)
             {
                Socket s=ss.accept();
                ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                    String rsuInfo = (String)ois.readObject().toString();
                    if(rsuInfo.equals("TAKEY")) {
                        Random rr=new Random();
                        String Takey = (String)ois.readObject().toString();
                        thisObu=(HashMap)ois.readObject();
                        String rsuuId = (String)ois.readObject().toString();
                        StringTokenizer stri3 = new StringTokenizer(Takey," ");
                        String pubKey = stri3.nextToken().toString().trim();
                        String priKey=stri3.nextToken().toString().trim();
                        String genrat=stri3.nextToken().toString().trim();
                        String primNum=stri3.nextToken().toString().trim();

                        

                        String keys="PublicKey:"+pubKey+"\nPrivateKey"+priKey;
                      //  System.out.println("This the curretn OBU key--------"+priKey);
                        vo1.setKeys(keys);
                        System.out.println("data--"+!thisObu.isEmpty()+"--"+thisObu);
                        if(!thisObu.isEmpty()){
                        Set set = thisObu.entrySet();
                        Iterator it=set.iterator();

                        while (it.hasNext()) {
                            Map.Entry me=(Map.Entry)it.next();
                            String val= me.getValue().toString();
                            String obuName=me.getKey().toString();
                            Vector v2=new Vector();
                            HashMap storeval=new HashMap();
                          
                            if (rsuuId.equals("NoNet")){
                                System.out.println("this no network");
                            }else{
                                if(rsuname.containsKey(obuName)&&rsuname.containsValue(rsuuId)) {
                                    System.out.println("already-----"+obuName+"--"+rsuuId);

                                }else{
                                rsuname.put(obuName,rsuuId);
                                System.out.println("net word has been changed"+rsuname);
                                priiKey=String.valueOf(rr.nextInt(10))+String.valueOf(rr.nextInt(10))+String.valueOf(rr.nextInt(10))+String.valueOf(rr.nextInt(10));
                                }
                            }
                           System.out.println("This is outer");

                            StringTokenizer stri4 = new StringTokenizer(val,"->");
                            String obusystem = stri4.nextToken().toString().trim();
                            String obuport=stri4.nextToken().toString().trim();
                            System.out.println("OBU VAL------>>"+val+"---"+obusystem+"---"+obuport);
                            Socket socet= new Socket(obusystem,Integer.parseInt(obuport));
                            ObjectOutputStream ooss1 = new ObjectOutputStream(socet.getOutputStream());
                            ooss1.writeObject("SENDOBUKEY");
                            ooss1.writeObject(pubKey);
                            ooss1.writeObject(priiKey);
                            socet.close();
                            System.out.println("DATA SEND---"+obusystem+"--"+obuport);

                        }
                        }
                        

                    }
                    if(rsuInfo.equals("OBUDATA"))
                    {
                        String obuSource = (String)ois.readObject();
                        String obuDest = (String)ois.readObject();
                        String obuData = (String)ois.readObject();
                        HashMap allOb=(HashMap)ois.readObject();

                        AllOBU=allOb;
                        ObuName=obuSource+"@"+obuDest+"@"+obuData;
                        TreeMap AllRs=(TreeMap)ois.readObject();
                        AllRSUInfo=AllRs;
                    }
                    if(rsuInfo.equals("ATTACKDATA"))
                    {
                        String obuSource = (String)ois.readObject();
                        String obuKey = (String)ois.readObject();
                        //String obuData = (String)ois.readObject();
                        HashMap allOb=(HashMap)ois.readObject();
                        AllOBU=allOb;
                        System.out.println("Checking the value in rsu recis***"+obuSource+obuKey);
                        ObuName=obuSource+"@"+obuKey;
                        TreeMap AllRs=(TreeMap)ois.readObject();
                        AllRSUInfo=AllRs;

                        if(ObuName.equals(obuSource)&&(priiKey.equals(obuKey))){
                            JOptionPane.showConfirmDialog(null,"Node is a Attacker");

                        }

                    }


             }
         }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void sendData(String obuDa,String listOb1) throws UnknownHostException, IOException//send neighbor node data
    {
        String obuList1=listOb1;
        String obuInfo=obuDa;
        StringTokenizer str2 = new StringTokenizer(obuList1,"\n");
        while(str2.hasMoreTokens())
         {
             String name = str2.nextToken().toString().trim();
             obdat.add(name);
         }
            StringTokenizer str3 = new StringTokenizer(obuInfo,"@");
            String obuSource = str3.nextToken().toString().trim();
            String obuDest=str3.nextToken().toString().trim();
            String obuData=str3.nextToken().toString().trim();

            if(obdat.contains(obuSource))
            {

               if(obdat.contains(obuDest))
               {
                       
                        String sendObu=AllOBU.get(obuDest).toString();
                        StringTokenizer str4 = new StringTokenizer(sendObu,"->");
                        String obuSystemName = str4.nextToken().toString().trim();
                        String obuPortNum=str4.nextToken().toString().trim();

                        Socket sock1= new Socket(obuSystemName,Integer.parseInt(obuPortNum));
                        ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                        oos1.writeObject("NORDETA");
                        oos1.writeObject(obuSource);
                        oos1.writeObject(obuData);
                       // oos1.writeObject(pubKey);
                        //oos1.writeObject(priKey);
                        sock1.close();
                        //send source
                                    String send1=AllOBU.get(obuSource).toString();
                                    StringTokenizer tok = new StringTokenizer(send1,"->");
                                    String SystemName = tok.nextToken().toString();
                                    String portNum = tok.nextToken().toString();

                                        Socket sock2= new Socket(SystemName,Integer.parseInt(portNum));
                                        ObjectOutputStream oos2 = new ObjectOutputStream(sock2.getOutputStream());
                                        oos2.writeObject("SEND");
                                        oos2.writeObject("SM");
                                        oos2.writeObject(obuDest);
                                        sock2.close();

                }else
                    {
                        if(AllRSUInfo.size()>0)
                        {
                            AllRSUInfo.remove(NameRsu);
                            TreeMap tm1=new TreeMap();
                        
                            Collection c = AllRSUInfo.values();
                            Collection c1=AllRSUInfo.keySet();
                            Vector trset=new Vector();

                            Iterator itrV = c.iterator();
                            Iterator itrK = c1.iterator();

                            while(itrV.hasNext() && itrK.hasNext())
                            {
                              tm1.put(itrV.next(),itrK.next());
                            }

                            Collection c2 = tm1.values();
                            Iterator itr2 = c2.iterator();
                            for(int i=0;i<AllRSUInfo.size();i++)
                            {
                                String st11=AllRSUInfo.get(itr2.next()).toString();
                                StringTokenizer str4 = new StringTokenizer(st11,"->");
                                String min = str4.nextToken().toString().trim();
                                String max=str4.nextToken().toString().trim();
                                String sys = str4.nextToken().toString().trim();
                                String port=str4.nextToken().toString().trim();

                                if(Integer.parseInt(min)<=CurMaxVal)
                		 {
                                     int ii=CurMaxVal-Integer.parseInt(min);
                                     trset.add(ii+"->"+sys+"->"+port);
                                 }

                             }
                            if(!trset.isEmpty())
                            {
                              for(int i=0;i<trset.size();i++)
                              {
                                    String NxtRsu=trset.get(i).toString();
                                    StringTokenizer tok = new StringTokenizer(NxtRsu,"->");
                                    String ValDis = tok.nextToken().toString();
                                    String SystemName = tok.nextToken().toString();
                                    String portNum = tok.nextToken().toString();
                                       
                                        Socket sock1= new Socket(SystemName,Integer.parseInt(portNum));
                                        ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                                        oos1.writeObject("OBUDATA");
                                        oos1.writeObject(obuSource);
                                        oos1.writeObject(obuDest);
                                        oos1.writeObject(obuData);
                                        oos1.writeObject(AllOBU);
                                        oos1.writeObject(AllRSUInfo);
                                }
                              
                            }else
                                {
//                                   System.out.println(" Sending failed..... ");
//                                   System.out.println(AllOBU);
                                    String notSend=AllOBU.get(obuSource).toString();
                                    StringTokenizer tok = new StringTokenizer(notSend,"->");
                                    String SystemName = tok.nextToken().toString();
                                    String portNum = tok.nextToken().toString();
//                                        System.out.println("system send to --"+SystemName+"---"+portNum);
                                    Socket sock1= new Socket(SystemName,Integer.parseInt(portNum));
                                    ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                                    oos1.writeObject("SEND");
                                    oos1.writeObject("SF");
                                    oos1.writeObject(obuDest);
                                    sock1.close();

                                }
                                trset.clear();

                            }

       
                        }
                }
                else
                {
                       if(obdat.contains(obuDest))
                       {
                                String sendObu=AllOBU.get(obuDest).toString();
                                StringTokenizer str4 = new StringTokenizer(sendObu,"->");
                                String obuSystemName = str4.nextToken().toString().trim();
                                String obuPortNum=str4.nextToken().toString().trim();

                                Socket sock1= new Socket(obuSystemName,Integer.parseInt(obuPortNum));
                                ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                                oos1.writeObject("NORDETA");
                                oos1.writeObject(obuSource);
                                oos1.writeObject(obuData);
                                sock1.close();

                                //send source
                                    String send1=AllOBU.get(obuSource).toString();
                                    StringTokenizer tok = new StringTokenizer(send1,"->");
                                    String SystemName = tok.nextToken().toString();
                                    String portNum = tok.nextToken().toString();

                                    Socket sock2= new Socket(SystemName,Integer.parseInt(portNum));
                                    ObjectOutputStream oos2 = new ObjectOutputStream(sock2.getOutputStream());
                                    oos2.writeObject("SEND");
                                    oos2.writeObject("SM");
                                    oos2.writeObject(obuDest);
                                    sock2.close();
                        }else
                        {

                            if(AllRSUInfo.size()>0)
                            {
                                AllRSUInfo.remove(NameRsu);
                                TreeMap tm1=new TreeMap();
                                Vector trset=new Vector();
                                Collection c = AllRSUInfo.values();
                                Collection c1=AllRSUInfo.keySet();

                                    Iterator itrV = c.iterator();
                                    Iterator itrK = c1.iterator();

                                while(itrV.hasNext() && itrK.hasNext())
                                    {
                                      tm1.put(itrV.next(),itrK.next());
                                    }

                                    Collection c2 = tm1.values();
                                    Iterator itr2 = c2.iterator();

                                    for(int i=0;i<AllRSUInfo.size();i++)
                                    {
                                        String st11=AllRSUInfo.get(itr2.next()).toString();
                                        StringTokenizer str4 = new StringTokenizer(st11,"->");
                                        String min = str4.nextToken().toString().trim();
                                        String max=str4.nextToken().toString().trim();
                                        String sys = str4.nextToken().toString().trim();
                                        String port=str4.nextToken().toString().trim();

                                         if(Integer.parseInt(min)<=CurMaxVal)
                                             {
                                                     int ii=CurMaxVal-Integer.parseInt(min);
                                                     trset.add(ii+"->"+sys+"->"+port);
                                             }
                                     }
                                    
                                    if(!trset.isEmpty())
                                    {
                                        for(int i=0;i<trset.size();i++)
                                          {
                                                String NxtRsu=trset.get(i).toString();
                                                StringTokenizer tok = new StringTokenizer(NxtRsu,"->");
                                                String ValDis = tok.nextToken().toString();
                                                String SystemName = tok.nextToken().toString();
                                                String portNum = tok.nextToken().toString();
                                                System.out.println("system send to --"+SystemName+"---"+portNum);
                                                Socket sock1= new Socket(SystemName,Integer.parseInt(portNum));
                                                ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                                                oos1.writeObject("OBUDATA");
                                                oos1.writeObject(obuSource);
                                                oos1.writeObject(obuDest);
                                                oos1.writeObject(obuData);
                                                oos1.writeObject(AllOBU);
                                                oos1.writeObject(AllRSUInfo);
                                            }
                                          
                                    }else
                                    {
                                        System.out.println(" Sending failed..... ");
                                        System.out.println(AllOBU);
                                        String notSend=AllOBU.get(obuSource).toString();
                                        StringTokenizer tok = new StringTokenizer(notSend,"->");
                                        String SystemName = tok.nextToken().toString();
                                        String portNum = tok.nextToken().toString();
                                        System.out.println("system send to --"+SystemName+"---"+portNum);
                                        Socket sock1= new Socket(SystemName,Integer.parseInt(portNum));
                                        ObjectOutputStream oos1 = new ObjectOutputStream(sock1.getOutputStream());
                                        oos1.writeObject("SEND");
                                        oos1.writeObject("SF");
                                        oos1.writeObject(obuDest);
                                        sock1.close();
                                    }
                                    trset.clear();



                                }




                        }




             

                }
            obdat.removeAllElements();

    }









}
