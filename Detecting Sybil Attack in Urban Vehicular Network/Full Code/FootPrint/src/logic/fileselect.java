
package logic;

import java.io.*;
import javax.swing.JFileChooser;
import java.util.StringTokenizer;


public class fileselect
    {
        static JFileChooser ss1=new JFileChooser();
        public String data;

        public String chooser()
            {
                int val=ss1.showOpenDialog(null);
                if(val==JFileChooser.APPROVE_OPTION)
                    {
                        String fname=ss1.getSelectedFile().getName();
                        String fpath=ss1.getSelectedFile().getPath();
                        StringTokenizer stz=new StringTokenizer(fname,".");
                        stz.nextToken();
                        String ext=stz.nextToken();

                        if(ext.equals("doc"))
                            {
                            try
                                {
                                    InputStream in=new FileInputStream(fpath);
                                    //HWPFDocument doc=new HWPFDocument(in);
                                    //WordExtractor we=new WordExtractor(doc);
                                    //data=we.getText();
                                }

                            catch(Exception e1)
                                { }
                    }

                else if((ext.equals("txt") || ext.equals("java") || ext.equals("pdf")))
                            {
                            try
                                {
                                    FileInputStream fis=new FileInputStream(fpath);
                                    byte by[]=new byte[fis.available()];
                                    fis.read(by);

                                data=new String(by);
                                }
                            catch(Exception e2)
                                {
                                }
                    }
                }
return data;
}
}
