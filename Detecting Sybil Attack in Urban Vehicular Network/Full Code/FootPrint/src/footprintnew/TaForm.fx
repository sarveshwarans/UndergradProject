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
import java.lang.Void;
import java.util.Vector;
import java.lang.String;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.animation.Timeline;
import java.lang.Math;
import logic.Tareceiver;
import Security.ElGamal;
import logic.Sender;

public class TaForm
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
        

public function taformm()
{
    var sta:Stage;
    var ta_port:String=String.valueOf(Math.round(Math.random()*9000)+999);
    var tarece:Tareceiver=new Tareceiver(ta_port);
    var setRsu:setAdapter = new setAdapter();
    var sen:Sender=new Sender();
    setRsu.valueObject=tarece.Tavo;

   
sta=Stage {
	title: "Trust Authority"
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
                                color: Color.LIGHTSLATEGRAY
                                offset: 0.0
                            },
                            Stop {
                                color: Color.WHITESMOKE
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
                           fitHeight:400
                           fitWidth:650
                            image: Image {
                                    url: "{__DIR__}image/ta.jpg"
                            }
                    }
//                Rectangle {
//                    arcWidth:10
//                    arcHeight:10
//                    x: 564, y: 3
//                    width: 35, height: 20
//                    fill: LinearGradient {
//                    startX: 0.0
//                    startY: 0.0
//                    endX: 1.0
//                    endY: 0.0
//                    stops: [
//                        Stop {
//                            color: Color.CADETBLUE
//                            offset: 0.0
//                        },
//                        Stop {
//                            color: Color.PLUM
//                            offset: 1.0
//                        },
//                    ]
//                }
//                onMouseClicked: function(e: MouseEvent): Void {
//                        sta.iconified=true;
//
//                   }
//            }

            Label {
                translateX: 575
                translateY: -4
                text: "_"
                font: Font { name: "Forte" size: 20}
                }


                  Label {
                        text: "TRUST AUTHORITY"
                        translateX:100
                        translateY:50
                        font: Font { name: "Comic Sans MS" size: 28 }
                        textFill:Color.LIGHTYELLOW;
                    }

                   Label{
                        text:"R"
                        font: Font { name: "Comic Sans MS" size: 32 }
                        translateX:25
                        translateY:320
                        textFill:Color.SALMON;
                        rotate:25
                    }
                     Label{
                        text:"S"
                        font: Font { name: "Comic Sans MS" size: 32 }
                        translateX:45
                        translateY:328
                        textFill:Color.SALMON                        rotate:25
                    }
                     Label{
                        text:"U"
                        font: Font { name: "Comic Sans MS" size: 32 }
                        translateX:65
                        translateY:345
                        textFill:Color.SALMON;
                        rotate:25
                    }

                    obuList=TextBox {
                            text:bind setRsu.setTaRsu;
                            columns: 12
                            lines:8
                            multiline:true
                            translateX:150
                            translateY:160
                            selectOnFocus: true
                            editable:false
                            style: "border-width:15;"
                           // textFill:Color.YELLOWGREEN;
                      }


                    reqDis=Label {
                        text: "Req Rec....."
                        translateX:350
                        translateY:100
                        font: Font { name: "Comic Sans MS" size: 22 }
                        visible:false
                        textFill:Color.YELLOW;
                    }
                    frdDis=Label {
                        text: "Data frd....."
                        translateX:350
                        translateY:100
                        font: Font { name: "Comic Sans MS" size: 22 }
                        visible:false
                        textFill:Color.YELLOW;
                    }

                ]
	}
}
}
function st()
        {
            t1.stop();
            sendVe.removeAllElements();
            sendVe.clear();

            return;
        }

}