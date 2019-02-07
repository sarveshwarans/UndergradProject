/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author gts
 */
public class Sender {
    public Sender(){
        try
        {
        InetAddress ia=InetAddress.getByName("228.27.55.88");
        MulticastSocket mc=new MulticastSocket(4574);
        mc.joinGroup(ia);
        String str="Authority";
        DatagramPacket dp=new DatagramPacket(str.getBytes(), str.length(), ia, 4574);
        System.out.println("Auth--->"+str);
        mc.send(dp);
        }
        catch(Exception e)
        {

        }
        }

}
