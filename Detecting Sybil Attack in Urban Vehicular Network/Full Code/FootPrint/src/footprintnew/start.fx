package footprintnew;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Tooltip;
import footprintnew.obuform;
import footprintnew.RsuForm;
import footprintnew.TaForm;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;
import footprintnew.setAdapter;
import logic.Reciver;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import java.lang.System;
import javax.swing.JOptionPane;
import Security.collectionsFramework;

class start
{


    function mainform():Void
    {
        var sa:setAdapter=new setAdapter();
        var rec:Reciver=new Reciver();
         var TA:TaForm=new TaForm();
        sa.valueObject=rec.va;
    var mobile: Boolean = "{__PROFILE__}" == "mobile";
    var browser: Boolean = "{__PROFILE__}" == "browser";
    var str;


var t2=Timeline {
	repeatCount: Timeline.INDEFINITE
	keyFrames: [
		KeyFrame {
	time: 1s
	action: function() {
             str=String.valueOf(sa.auth);
           
   /* if(str.equalsIgnoreCase("Authority"))
    {
    b.visible=false;
    
        }*/
    }

}


	];
}

t2.play();




    var stage1 :Stage=Stage {
    title: "FOOTPRINT"
    style:StageStyle.TRANSPARENT
    scene: Scene {
        width: 650
        height: 350
        content: [
           
                       ImageView {
                           fitHeight:350
                           fitWidth:650
                           //translateY:-30


                            image: Image {
                                    url: "{__DIR__}image/new.jpg"
                            }
                            onMouseDragged: function(e: MouseEvent): Void {
                                                if (not browser and not mobile) {
                                                    stage1.x = e.screenX - e.dragAnchorX;
                                                    stage1.y = e.screenY - e.dragAnchorY;

                                                }
                                                }

                    }

                         ImageView {
                        translateX:210
                        translateY:120
                            image: Image {
                                    url: "{__DIR__}image/foot.png"
                            }
                            }

//-----------------------------------------------------------------------------------
//Text {
//    effect: DropShadow {
//                    offsetX: -10
//                    offsetY: -10
//                       }
//
//
//        translateX:250
//        translateY:120
//        content: "FOOTPRINT"
//        font: Font { name: "Copperplate Gothic Bold" size: 40 }
//        fill: Color.WHITE
//
//
//    }

//------------------------------------------------------------------------------------
    Button {
                   text: "TA"
                   translateX:225
                   translateY:280
                   visible:true
                   font: Font { name: "Comic Sans MS" size: 20 }
                   action: function()   {
                 //  var no:sender=new sender();
             //      no.sen();

                                        }
                    tooltip: Tooltip {
                           text: "Trust Authority"
                           }
                           onMouseClicked: function(e: MouseEvent): Void
                           {
                               if(str=="Authority")
                                    {
                                      JOptionPane.showMessageDialog(null, "Authority Already Created");
                                    }
                                else
                                {
                                    TA.taformm();
                                    }
                               
                                                         
                          // var obu:Taform=new Taform();
                          // obu.Taform;

                           }
                    




             }
   

     Button {
                   text: "OBU"
                   translateX:375
                   translateY:280
                   font: Font { name: "Comic Sans MS" size: 20 }
                   action: function()   {
                   //var no:sender=new sender();
                    ///        no.sen();
                                        }
                    tooltip: Tooltip {
                           text: "On Board Unit"
                           }
                           onMouseClicked: function(e: MouseEvent): Void {
                           var obu:obuform=new obuform();
                           obu.obufrm();

                    }

             }

      Button {
               text: "RSU"
               translateX:300
               translateY:280
               font: Font { name: "Comic Sans MS" size: 20 }
               tooltip: Tooltip {
                           text: "Rode side Unit"
                           }
               onMouseClicked: function(e: MouseEvent): Void {
                   var cf:collectionsFramework= new collectionsFramework();
                   if(cf.get()) {
                        var rsu:RsuForm=new RsuForm();
                        rsu.Rsu();
                    }
                   }
              }
     Button {
               text: "Exit"
               font: Font { name: "Comic Sans MS" size: 20 }
               translateX:450
               translateY:280
               action: function()   
               {
                   FX.exit();
               }
             }

                ]
            }
    }
}
}
var ss:start=new start();
ss.mainform();
