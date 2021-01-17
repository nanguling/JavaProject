package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;

public class Main extends Application {

    public static Parent build() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField textField = new TextField();
        gridPane.add(textField,0,0);

        Label label = new Label();
        label.setStyle("-fx-border-color: lightpink;-fx-border-width: 1;-fx-border-radius: 5");
        label.setPrefWidth(200);
        gridPane.add(label,0,2);

        Button button = new Button();
        button.setText("点击我");
        gridPane.add(button,0,1);

        Controller controller = new Controller();
        controller.inputField = textField;
        controller.label = label;

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.onClick(event);
            }
        });
        return gridPane;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //URL FXML对应的URL = new URL("file:/D:/biteJAVA/项目/20210117/src/sample/sample.fxml");
        //Parent root = FXMLLoader.load(FXML对应的URL);

        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        System.out.println(root.getClass().getCanonicalName());*/
        Parent root = build();
        primaryStage.setTitle("薇姐");
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
