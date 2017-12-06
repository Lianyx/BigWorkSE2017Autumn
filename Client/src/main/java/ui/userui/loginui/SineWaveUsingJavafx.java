package ui.userui.loginui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.CubicCurveBuilder;
import javafx.util.Duration;

public class SineWaveUsingJavafx extends Application {
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,200,300);
            primaryStage.setTitle("www.puretechy.com");
            primaryStage.setScene(scene);
            Group g = new Group();


            CubicCurve cubicCurve = CubicCurveBuilder.create()
                    .startX(0).startY(150)          // start pt (x1,y1)
                    .controlX1(30).controlY1(50)   // control pt1
                    .controlX2(80).controlY2(250)  // control pt2
                    .endX(90).endY(150)             // end pt (x2,y2)
                    .fill(Color.GREY)
                    .build();

            CubicCurve cubicCurve1 = CubicCurveBuilder.create()
                    .startX(90).startY(150)          // start pt (x1,y1)
                    .controlX1(120).controlY1(50)   // control pt1
                    .controlX2(170).controlY2(250)  // control pt2
                    .endX(200).endY(150)             // end pt (x2,y2)
                    .fill(Color.GREY)
                    .build();


            g.getChildren().add(cubicCurve);
            g.getChildren().add(cubicCurve1);

            root.getChildren().add(g);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}