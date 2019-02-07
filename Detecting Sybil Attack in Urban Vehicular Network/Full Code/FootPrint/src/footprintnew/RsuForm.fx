package footprintnew;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.TextBox;
import logic.Client;
import footprintnew.setAdapter;
import logic.MulReceiver;
import logic.rsuReceiv;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import java.lang.Void;
import java.util.Vector;
import java.lang.String;
import javafx.util.Math;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import Security.ElGamal;
import java.lang.System;

public class RsuForm
{
        var mobile: Boolean = "{__PROFILE__}" == "mobile";
        var browser: Boolean = "{__PROFILE__}" == "browser";
        public var cli:Client=new Client();
        var maxval:Integer;
        var minval:Integer;
        public var t1:Timeline;
        public var reqDis:Label;
        public var frdDis:Label;
        public var obuList:TextBox;
        public var sendVe:Vector=new Vector();


public function Rsu()
{
    var sta:Stage;
//    vfr.ElGamal();
    cli.createRsu();
    var rsu_id:String="RS{String.valueOf(Math.round(Math.random()*9000)+999)}";
    var rsu_port:String=String.valueOf(Math.round(Math.random()*9000)+999);
    var rsuDis:String=cli.get_rsuDist();
    var rsuRange:String=cli.get_rsuRange();
    maxval = Integer.parseInt(rsuDis)+Integer.parseInt(rsuRange);
    minval = Integer.parseInt(rsuDis)-Integer.parseInt(rsuRange);  
    var setObu:setAdapter = new setAdapter();
    var Mrec:MulReceiver =new MulReceiver();
    setObu.valueObject=Mrec.vo;
    var setReq:setAdapter=new setAdapter();
    var rsuRec:rsuReceiv=new rsuReceiv(rsu_id,rsu_port,minval,maxval);
    setReq.valueObject=rsuRec.vo1;
    Mrec.setdetailes(rsu_id,rsu_port,minval,maxval);


 
    t1=Timeline {
                       
                        repeatCount:Timeline.INDEFINITE
                        keyFrames: [
                               KeyFrame{
                                   time:20s

                                   action:function():Void
                                   {
                                       frdDis.visible=false;
                                      

                                   }

                               }

                        ];

                };
                

                
                var t2veC:Timeline=Timeline {
                        repeatCount: Timeline.INDEFINITE

                        keyFrames: [
                               KeyFrame{
                                   time:2s
                                   action:function():Void
                                   {
                                       if(rsuRec.obuRequest.equals(""))//send to service data
                                       {
                                           t1.stop();
                                       }
                                       else
                                       {
                                    
                                       }

                                       if(rsuRec.ObuName.equals(""))//OBU TO OBU
                                       {}else{
                                           frdDis.visible=true;
                                           t3.play();
                                           rsuRec.sendData(rsuRec.ObuName,obuList.text)
                                       }
                                       rsuRec.ObuName="";
                                     
                                   }

                               }

                        ];
                }
                t2veC.play();


            var t3=Timeline {

                        repeatCount:Timeline.INDEFINITE
                        keyFrames: [
                               KeyFrame{
                                   time:15s

                                   action:function():Void
                                   {
                                          frdDis.visible=false;
                                   }

                               }

                        ];

                };

sta=Stage {
	title: "Road Side Unit"
	style:StageStyle.TRANSPARENT
	scene: Scene {
		width: 600
		height: 400
		content: [

                    


                Rectangle{
                arcHeight:25
                arcWidth:25
                strokeWidth: 5.0
                stroke: Color.color(0.11, 0.11, 0.02);
                x: 0, y: 0
                width: 600, height: 400
                fill: LinearGradient
                {
                        startX: 0.0
                        startY: 0.0
                        endX: 0.0
                        endY: 1.0
                       stops: [
                            Stop {
                                color: Color.LIGHTBLUE
                                offset: 0.0
                            },
                            Stop {
                                color: Color.LIGHTBLUE
                                offset: 1.0
                            },
                        ]
                        }
                        onMouseDragged: function(e: MouseEvent): Void {
                                                if (not browser and not mobile) {
                                                    sta.x = e.screenX - e.dragAnchorX;
                                                    sta.y = e.screenY - e.dragAnchorY;

                                                }
                                                }

                  }

                  ImageView {
                        fitWidth:600
                        fitHeight:400
	image: Image {
		url: "{__DIR__}image/rsu.jpg"
	}
}

ImageView {
                        fitWidth:50
                        fitHeight:35
translateX:190
translateY:5
	image: Image {
		url: "{__DIR__}image/r.png"
	}
}

ImageView {
                        fitWidth:50
                        fitHeight:35
translateX:275
translateY:5
	image: Image {
		url: "{__DIR__}image/s.png"
	}
}
ImageView {
                        fitWidth:50
                        fitHeight:35
translateX:365
translateY:5
	image: Image {
		url: "{__DIR__}image/u.png"
	}
}
                  ImageView {
                           fitHeight:400
                           fitWidth:650
                            image: Image {
                                    url: "{__DIR__}image/ren2.jpg"
                            }
                    }
                

                  Label {
                        text: "RSU ID        :"
                        translateX:40
                        translateY:110
                        font: Font { name: "Comic Sans MS" size: 20 }
                    }
                  Label {
                        text: "Port Number :"
                        translateX:40
                        translateY:155
                        font: Font { name: "Comic Sans MS" size: 20 }
                    }
                    Label{
                        text:"Distance       :"
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:40
                        translateY:195
                    }
                    Label{
                        text:"Range            :"
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:40
                        translateY:235
                    }
                   Label{
                        text:"Nodes"
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:490
                        translateY:180
                    }
//
//                   Label{
//                        text:"Key   :"
//                        font: Font { name: "Comic Sans MS" size: 22 }
//                        translateX:110
//                        translateY:300
//                    }

                    obuList=TextBox {
                            text:bind setObu.setObu;
                            columns: 14
                            lines:6
                            multiline:true
                            translateX:485
                            translateY:210
                            selectOnFocus: true
                            editable:false
                            style: "border-width:15;"
                    }
                    Label{
                        text:rsu_id
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:200
                        translateY:110
                    }
                    Label{
                        text:rsu_port
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:200
                        translateY:155
                    }
                    Label{
                        text:rsuDis
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:200
                        translateY:195
                    }
                    Label{
                        text:rsuRange
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:200
                        translateY:235
                    }

                     Label{
                        text:""
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:110
                        translateY:300
                    }
                     Label{
                        text:bind setReq.setKey
                        font: Font { name: "Comic Sans MS" size: 20 }
                        translateX:350
                        translateY:130
                    }
                    reqDis=Label {
                        text: "Req Rec....."
                        translateX:350
                        translateY:100
                        font: Font { name: "Comic Sans MS" size: 20 }
                        visible:false
                    }
                    frdDis=Label {
                        text: "Data Forwarded..."
                        translateX:350
                        translateY:100
                        font: Font { name: "Comic Sans MS" size: 20 }
                        visible:false
                    }
    
                    
                ]
	}
}
}
function st()//time line start
        {
            t1.stop();
            sendVe.removeAllElements();
            sendVe.clear();
            return;
        }
}