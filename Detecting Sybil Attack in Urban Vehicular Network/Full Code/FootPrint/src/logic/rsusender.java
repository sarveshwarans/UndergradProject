package logic;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.StringTokenizer;
import java.util.Vector;

public class rsusender extends Thread {

    public String obuDet,localsys,obuName,obuPort,obuDist,obuRange,obuMin,obuMax,obuSysName;
    public int obuMini,obuMaxi,obuConmin;

    public Vector vecData=new Vector();

    public void setObuDet(String det)
    {

            obuDet=det;
            StringTokenizer str = new StringTokenizer(obuDet,"@");
            obuName = str.nextToken().toString().trim();
            obuPort = str.nextToken().toString().trim();
            obuDist = str.nextToken().toString().trim();
            obuRange = str.nextToken().toString().trim();
            obuMin = str.nextToken().toString().trim();
            obuMax = str.nextToken().toString().trim();
            obuMaxi=Integer.parseInt(obuMax);
            obuMini=Integer.parseInt(obuMin);
            obuConmin=obuMini;
            start();

    }
    public void run()//send OBU information to Rsu
    {
        try
        {
            while(true)
            {
                for(int i=obuMini;i<obuMaxi;obuMini+=5)
                {
                    if(obuMini<obuMaxi)
                    {
                        InetAddress ia=InetAddress.getByName("228.27.55.88");
                        MulticastSocket ms=new MulticastSocket(4574);
                        ms.joinGroup(ia);
                        String se;
                        localsys=InetAddress.getLocalHost().getHostName();
                        se="OBU"+"@"+obuName+"@"+localsys+"@"+obuPort+"@"+String.valueOf(obuMini);
                        DatagramPacket pack=new DatagramPacket(se.getBytes(),se.length(),ia,4574);
                        ms.send(pack);
                        Thread.sleep(10000);
                    }
                    else {
                        break;
                    }
                }
                for(int i=obuConmin;i<obuMini;obuMini-=5)
                {
                    if(obuConmin<obuMini)
                    {
                        InetAddress ia=InetAddress.getByName("228.27.55.88");
                        MulticastSocket ms=new MulticastSocket(4574);
                        ms.joinGroup(ia);
                        String se;
                        localsys=InetAddress.getLocalHost().getHostName();
                        se=obuName+"@"+localsys+"@"+obuPort+"@"+String.valueOf(obuMini);
                        DatagramPacket pack=new DatagramPacket(se.getBytes(),se.length(),ia,4574);
                        ms.send(pack);
                        Thread.sleep(10000);


                    }else
                    {
                       break;
                    }

               }
            }
        }catch(Exception e)
        {
                e.printStackTrace();
            }
    }




}
