package logic;

import javax.swing.JOptionPane;

    public class Client extends Thread {
    public Client(){}
    public String dis,range,det,rsuDis,rsuRange;
    public String localsys;

    public void create()
    {
        while(true)
        {
            dis=JOptionPane.showInputDialog(null,"Enter the Distance :");
            if(dis.equals("null N"))
            System.exit(0);
            try
            {
                break;
            }
            catch(NumberFormatException e)
            {   }
        }
        while(true)
        {
            range=JOptionPane.showInputDialog(null,"Enter the network Range :");
            if(range.equals("null N"))
            System.exit(0);
            try
            {
                break;
            }
            catch(NumberFormatException e)
            {   }
        }
    }
    public void createRsu()
    {
        while(true)
        {
            rsuDis=JOptionPane.showInputDialog(null,"Enter the Distance");
            if(rsuDis.equals("null N"))
            System.exit(0);
            try
            {
                break;
            }
            catch(NumberFormatException e)
            {   }
        }
        while(true)
        {
            rsuRange=JOptionPane.showInputDialog(null,"Enter the network Range");
            if(rsuRange.equals("null N"))
            System.exit(0);
            try
            {
                break;
            }
            catch(NumberFormatException e)
            {   }
        }
    }
      public String get_dist()
      {
         return dis;
      }
      public String get_range()
      {
         return range;
      }
      public String get_rsuDist()
      {
         return rsuDis;
      }
      public String get_rsuRange()
      {  
         return rsuRange;
      }
      

}
