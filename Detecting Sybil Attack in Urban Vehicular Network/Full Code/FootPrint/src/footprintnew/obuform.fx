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
import javafx.scene.control.Button;
import logic.Client;
import logic.fileselect;
import footprintnew.setAdapter;
import logic.obuSender;
import logic.obuReceiver;
import javax.swing.JOptionPane;
import javafx.scene.control.Hyperlink;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Math;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


public class obuform {
    var mobile: Boolean = "{__PROFILE__}" == "mobile";
    var browser: Boolean = "{__PROFILE__}" == "browser";
    public var cli:Client=new Client();
    public var file:fileselect=new fileselect();
    var obuMin:Integer;
    var obuMax:Integer;
    var req:TextBox;
    var KEY:Label;
    var RsuName:Label;
    var hyperLink: Hyperlink;
    var sndinfo:TextBox;
    var dest:TextBox;
    var serv:Label;
    var msend:Label;
    var vpr:ProgressIndicator;
    var re:Rectangle;
    var sndf:ImageView;
    var sndsus:ImageView;
//    var dsend:AttackSend;

    public var setRSU:setAdapter = new setAdapter();
    public var obusen:obuSender=new obuSender();
    public function obufrm()
    {   var stag:Stage;
        var vec_id:String="VH{String.valueOf(Math.round(Math.random()*9000)+999)}";
        var vec_port:String=String.valueOf(Math.round(Math.random()*9000)+999);
        var obuRec:obuReceiver=new obuReceiver(vec_id,vec_port);
        var setRsuName:setAdapter = new setAdapter();
        setRsuName.valueObject=obuRec.vo1;
        cli.create();
        var dis:String=cli.get_dist();
        var range:String=cli.get_range();
        obuMin=Integer.parseInt(dis)-Integer.parseInt(range);
        obuMax=Integer.parseInt(dis)+Integer.parseInt(range);
        obusen.setObuDet("{vec_id}@{vec_port}@{dis}@{range}@{String.valueOf(obuMin)}@{String.valueOf(obuMax)}");


                   var t3=Timeline {

                        repeatCount:Timeline.INDEFINITE
                        keyFrames: [
                               KeyFrame{
                                  time:8s

                                   action:function():Void
                                   {
                                          if (obuRec.sendInfo=="")
                                          {
                                              msend.visible=false;
                                              vpr.visible=false;
                                              sndf.visible=false;
                                              sndsus.visible=false;

                                          }else if(obuRec.sendInfo=="SF")
                                          {
                                                msend.text="Sending failed";
                                                msend.visible=true;
                                                vpr.visible=false;
                                                sndsus.visible=false;
                                                sndf.visible=true;
                                                obuRec.sendInfo="";

                                          } else if(obuRec.sendInfo=="SM")
                                              {
                                                msend.text="Msg delivered";
                                                vpr.visible=false;
                                                msend.visible=true;
                                                sndsus.visible=true;
                                                obuRec.sendInfo="";
                                             }
                                             serv.visible=false;


                                   }
                               }
                        ];
                };t3.play();

            var t4=Timeline {
                        repeatCount:Timeline.INDEFINITE
                        keyFrames: [
                               KeyFrame{
                                   time:2s

                                   action:function():Void
                                   {
                                   }
                               }
                        ];
                };t4.play();

         stag=Stage {
	title: "OBU"
        style:StageStyle.TRANSPARENT
        resizable:false
	scene: Scene {
		width: 820
		height: 500
		content: [


                Rectangle{
                arcHeight:25
                arcWidth:25
                strokeWidth: 5.0
                stroke: Color.color(0.11, 0.11, 0.02);
                x: 0, y: 0
                width: 820, height: 500
                fill: LinearGradient
                {
                        startX: 0.0
                        startY: 0.0
                        endX: 0.0
                        endY: 1.0
                        stops: [
                            Stop {
                                color: Color.BLANCHEDALMOND
                                offset: 0.0
                            },
                            Stop {
                                color: Color.GREY
                                offset: 1.0
                            },
                        ]
                        }
                       onMouseDragged: function(e: MouseEvent): Void {
                                if (not browser and not mobile) {
                                    stag.x = e.screenX - e.dragAnchorX;
                                    stag.y = e.screenY - e.dragAnchorY;

                                }
                                }
                  }

                     ImageView {
                        fitWidth:1500
                        fitHeight:1000
                        translateX:-50
	image: Image {
		url: "{__DIR__}image/car.jpg"
	}
}


                re=Rectangle {
                    arcWidth:10
                    arcHeight:10
                x: 763, y: 3
                width: 35, height: 20
                fill: LinearGradient {
                    startX: 0.0
                    startY: 0.0
                    endX: 1.0
                    endY: 0.0
                    stops: [
                        Stop {
                            color: Color.BLANCHEDALMOND
                            offset: 0.0
                        },
                        Stop {
                            color: Color.GREY
                            offset: 1.0
                        },
                    ]
                }
                onMouseClicked: function(e: MouseEvent): Void {
                        stag.iconified=true;

                   }
                onMouseEntered: function(e: MouseEvent): Void {
                        re.fill=Color.BLANCHEDALMOND;
                    }
                onMouseExited: function(e: MouseEvent): Void {
                        re.fill=Color.WHITESMOKE
                    }

            }
            Label {
                translateX: 775
                translateY: -10
                text: "_"
                font: Font { name: "Forte" size: 20}
                }

            Label {
                text: "Vehicle ID    :"
                translateX:70
                translateY:50
                font: Font { name: "Comic Sans MS" size: 22 }
            }
            Label {
                text: "RSU ID  :"
                translateX:500
                translateY:50
                font: Font { name: "Comic Sans MS" size: 22 }
            }
            Label {
                text: "Coverage :"
                translateX:500
                translateY:100
                font: Font { name: "Comic Sans MS" size: 22 }
            }
            Label {
                text: "Port   :"
                translateX:70
                translateY:100
                font: Font { name: "Comic Sans MS" size: 22 }
            }
            Label {
                text:"Distance  :"
                font: Font { name: "Comic Sans MS" size: 22 }
                translateX:70
                translateY:140
            }
            Label{
                text:"Range   :"
                font: Font { name: "Comic Sans MS" size: 22 }
                translateX:70
                translateY:180
            }
            Label{
                text: "Public Key:"
                font: Font { name: "Comic Sans MS" size: 22 }
                translateX : 70
                translateY :220
            }
            Label{
                text: "Signature:"
                font: Font { name: "Comic Sans MS" size: 22 }
                translateX : 70
                translateY :255
            }

          
            Label {
                    text: vec_id
                    font: Font { name: "Comic Sans MS" size: 22 }
                    translateX:225
                    translateY:50
            }
             Label {
                    text: vec_port
                    font: Font { name: "Comic Sans MS" size: 22 }
                    translateX:225
                    translateY:100
            }
            Label {
                    text: dis
                    font: Font { name: "Comic Sans MS" size: 22 }
                    translateX:225
                    translateY:140
            }
             Label {
                    text: range
                    font: Font { name: "Comic Sans MS" size: 22 }
                    translateX:225
                    translateY:180
            }
            RsuName=Label {
                text: bind setRsuName.setRsuInfo
                translateX:600
                translateY:50
                font: Font { name: "Comic Sans MS" size: 22 }

            }
            Label {
                text: bind setRsuName.setObuDist
                translateX:600
                translateY:100
                font: Font { name: "Comic Sans MS" size: 22 }
            }
            KEY=Label {
                text: bind setRsuName.obukey
                translateX:200
                translateY:224
                font: Font { name: "Comic Sans MS" size: 22 }
            }
            

                Label {
                    text: "Destination :"
                    translateX:500
                    translateY:145
                    font: Font { name: "Comic Sans MS" size: 22 }

                }

                dest=TextBox{
                 text: "VH"
                 font: Font { name: "Arial Black" size: 14 }
                 columns: 8
                 lines:1
                 translateX:640
                 translateY:145
                 selectOnFocus: true
                 multiline:false
                }
         Button {
                text: "Attack"
                translateX:450
                translateY:430
                font: Font { name: "Forte" size: 20 }
                onMouseClicked: function(e: MouseEvent): Void {
                        obuRec.AttackSend(vec_id,setRsuName.setKey);
                    

                }
                }

          Button {
                text: "Browse"
                translateX:70
                translateY:430
                font: Font { name: "Forte" size: 20 }
                action: function() {    }
                onMouseClicked: function(e: MouseEvent): Void {

                   var list=file.chooser();
                   sndinfo.text=list;
                   }
              }

           sndinfo=TextBox {
                 text: "send information box"
                 columns: 25
                 lines:4
                 translateX:70
                 translateY:330
                 selectOnFocus: true
                 multiline:true
             }
             TextBox {
                 text: bind setRsuName.setNorDeta
                 columns: 25
                 lines:4
                 translateX:330
                 translateY:330
                 selectOnFocus: true
                 multiline:true
             }
             Label {
                    text: "Rec msg:"
                    translateX:320
                    translateY:300
                    font: Font { name: "Comic Sans MS" size: 20 }

                }
                Label {
                    text: "Send msg:"
                    translateX:70
                    translateY:300
                    font: Font { name: "Comic Sans MS" size: 20 }

                }

                Button {
                text: "Send"
                translateX:320
                translateY:430
                font: Font { name: "Forte" size: 20 }
                onMouseClicked: function(e: MouseEvent): Void {

                   
                           if(dest.text=="VH" or dest.text.length()!=6)
                            {
                                JOptionPane.showMessageDialog(null,"Enter the correct destination");
                            }
                            else{
                               
                                msend.visible=true;
                                vpr.visible=true;
                                msend.text="Msg Sending...";
                                obuRec.Obudatasend(vec_id,dest.text,sndinfo.text);
                            }

                       
                    }

                }
                msend=Label {
                    translateX:680
                    translateY:200
                    font: Font { name: "Comic Sans MS" size: 20 }
                    visible:true
                  }


                 vpr=ProgressIndicator {
                            progress: -1
                            translateX:650
                            translateY:200
                            visible:false
                       }
                      sndf=ImageView {
                           fitHeight:25
                           fitWidth:25
                           translateX:650
                           translateY:200
                           visible:false
                            image: Image {
                                    url: "{__DIR__}image/sf.png"
                            }
                       }
                     sndsus=ImageView {
                           fitHeight:30
                           fitWidth:30
                           translateX:650
                           translateY:200
                           visible:false
                            image: Image
                            {
                                    url: "{__DIR__}image/sed.png"
                            }

                    }





                    ]
            }
        }//stage
    }
}
