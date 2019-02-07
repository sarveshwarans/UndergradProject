package logic;


import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gts
 */
public class Reciver extends Thread{
//String state="false";
    public valueobject va=new valueobject();
    public Reciver()
    {
        
start();
}
public void run()
    {
    try
    {
        System.out.println("Test");
InetAddress ia=InetAddress.getByName("228.27.55.88");
        MulticastSocket mc=new MulticastSocket(4574);
        mc.joinGroup(ia);
        while(true)
        {
    byte b[]=new byte[1024];
    DatagramPacket dp=new DatagramPacket(b, b.length);
    mc.receive(dp);
    String str=new String(dp.getData()).trim();
    System.out.println("Rec Auth--->"+str);
    va.setAuth(str);
        }
    }
    catch(Exception e)
    {
    e.printStackTrace();
    }
}
}
