package logic;

import java.util.Observable;

public class valueobject extends Observable {
private String setRsuNode,obuChDis,setObu,rsuNodeInfo,rsuTa,keys,obuKey;
private String obuChangeDist,obuReq,setREC,setNorDeta,setNot,auth;

public String getValue1()
    {       
        return setRsuNode;
    }
public void setValue1(String value)
    {
        this.setRsuNode = value;        
         done();
    }
public String getChaDis()
    {
        return obuChDis;
    }
public void setChaDis(String value)
    {
    this.obuChDis=value;
    done();
}
public String getObu()
    {
        return setObu;
    }
public void setObu(String value)
    {
    
    this.setObu=value;
    done();
}

public String getRsuInfo()
    {
   
        return rsuNodeInfo;
    }
public void setRsuInfo(String value)
    {
        this.rsuNodeInfo = value;
        
         done();
    }
public String getKeys()
    {

        return keys;
    }
public void setKeys(String value)
    {
        this.keys = value;

         done();
    }
public String getRsuInfoTA()
    {

        return rsuTa;
    }
public void setRsuInfoTA(String value)
    {
        this.rsuTa = value;

         done();
    }

public String getObuDist()
    {
    
        return obuChangeDist;
    }
public void setObuDist(String value)
    {
        this.obuChangeDist = value;
         done();
    }


public String getRequestObu()
    {
        return obuReq;
    }
public void setRequestObu(String value)
    {
        this.obuReq = value;
         done();
    }
public String getRECINFO()
    {

        return setREC;
    }
public void setRECINFO(String value)
    {
        this.setREC = value;
         done();
    }
public String getNormalDeta()
    {
        return setNorDeta;
    }
public void setNormalDeta(String value)
    {
        this.setNorDeta = value;
         done();
    }
public String getNotsend()
    {
        return setNot;
    }
public void setNotsend(String value)
    {
        this.setNot = value;
         done();
    }
public String getObuKeysend()
    {
        return obuKey;
    }
public void setObuKeysend(String value)
    {
        this.obuKey = value;
         done();
    }
public void setAuth(String s)
    {
auth=s;
done();
}
public String getAuth()
    {
return auth;
}
private void done()
    {
        setChanged();
        notifyObservers();
    }

  }
