package footprintnew;

import java.util.Observer;
import java.util.Observable;
import logic.valueobject;
  
public class setAdapter extends Observer {

     public-read var setvalue1:String;
     public-read var obuChDis:String;
     public-read var setObu:String;
     public-read var setRsuInfo:String;
     public-read var setObuDist:String;
     public-read var setKey:String;
     public-read var setReq:String;
     public-read var setREC:String;
     public-read var setNorDeta:String;
     public-read var notsend:String;
     public-read var setTaRsu:String;
     public-read var setKeys:String;
     public-read var obukey:String;
     public-read  var auth:String;
     public var valueObject : valueobject


        on replace { valueObject.addObserver(this)}

      override function update(observable: Observable, arg: Object) {

         FX.deferAction(
            function(): Void {

      
       setvalue1=valueObject.getValue1();
       obuChDis=valueObject.getChaDis();
       setObu=valueObject.getObu();
       setRsuInfo=valueObject.getRsuInfo();
       setObuDist=valueObject.getObuDist();
       setTaRsu=valueObject.getRsuInfoTA();
       setReq=valueObject.getRequestObu();
       setREC=valueObject.getRECINFO();
       setNorDeta=valueObject.getNormalDeta();
       notsend=valueObject.getNotsend();
       setKey=valueObject.getKeys();
       obukey=valueObject.getObuKeysend();
       auth=valueObject.getAuth();
     }
         );
     }
}


