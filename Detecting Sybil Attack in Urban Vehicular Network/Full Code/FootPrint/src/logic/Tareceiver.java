package logic;

import Security.ElGamal;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;


public class Tareceiver extends Thread{

public String ta_port;
public valueobject Tavo = new valueobject();
public HashMap<String, String> rsuInfoMap=new HashMap<String, String>();
public HashMap rsuObuInfo=new HashMap();
    ElGamal keygen=new ElGamal();
    public String rankey=keygen.initialize();
    
    public Tareceiver(String ta_po){
        ta_port=ta_po;
        start();
        
    }

    public void run(){
           try {
             ServerSocket ss=new ServerSocket(6767);
             while(true)
             {
                Socket s=ss.accept();
                ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
                    String rsuInfo = (String)ois.readObject().toString();
                  
                    if(rsuInfo.equals("RSUINFOR")) {
                        String rsuData=(String)ois.readObject().toString();
                        rsuObuInfo=(HashMap)ois.readObject();
                        String rsuId=(String)ois.readObject().toString();
                        System.out.println("obu TA info--send--"+rsuObuInfo);
                        StringTokenizer str3 = new StringTokenizer(rsuData,"->");
                        String rsuName =str3.nextToken().toString().trim();
                        String rsuSys=str3.nextToken().toString().trim();
                        String rsuPort=str3.nextToken().toString().trim();
                        String rsuDis=str3.nextToken().toString().trim();
                        String rsuRam=str3.nextToken().toString().trim();
                        Socket socke1= new Socket(rsuSys,Integer.parseInt(rsuPort));
                        ObjectOutputStream oos1 = new ObjectOutputStream(socke1.getOutputStream());
                        oos1.writeObject("TAKEY");
                        oos1.writeObject(rankey);
                        oos1.writeObject(rsuObuInfo);
                        oos1.writeObject(rsuId);
                        System.out.println("send RSU Infor--"+rsuSys+"--"+rsuPort+"--"+rsuObuInfo);
                        oos1.close();


                        if(!rsuInfoMap.containsKey(rsuName)){

                            rsuInfoMap.put(rsuName,rsuSys+"->"+rsuPort);
                            String key=rsuInfoMap.keySet().toString();

                                Set set = rsuInfoMap.entrySet();
                                Iterator it = set.iterator();
                                String val="";
                                while(it.hasNext())
                                {
                                    Map.Entry me = (Map.Entry)it.next();
                                    String rsuNa=me.getKey().toString();
                                    val=val+"\n"+rsuNa;
                                    sleep(2000);
                                }
//                          System.out.println("---->>"+key);
                            Tavo.setRsuInfoTA(val);
                      
                        }
                       


                 }
                    rsuObuInfo.clear();
             }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}
