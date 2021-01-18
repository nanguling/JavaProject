package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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


        //关于“表”实验开始
        TableView<Student> tableView = new TableView<>();
        gridPane.add(tableView,0,3);

        //表中有哪些列
        TableColumn<Student,String> idColum = new TableColumn<>();
        idColum.setText("#ID");
        idColum.setCellValueFactory(param -> param.getValue().getIdValue());
        tableView.getColumns().add(idColum);

        TableColumn<Student,String> nameColum = new TableColumn<>();
        nameColum.setText("姓名");
        nameColum.setCellValueFactory(param -> param.getValue().getNameValue());
        tableView.getColumns().add(nameColum);

        TableColumn<Student,String> ageColum = new TableColumn<>();
        ageColum.setText("年龄");
        ageColum.setCellValueFactory(param -> param.getValue().getAgeValue());
        tableView.getColumns().add(ageColum);

        TableColumn<Student,String> genderColum = new TableColumn<>();
        genderColum.setText("性别");
        genderColum.setCellValueFactory(param -> param.getValue().getGenderValue());
        tableView.getColumns().add(genderColum);

        controller.tableView = tableView;
        //关于“表”实验结束


        return gridPane;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //URL FXML对应的URL = new URL("file:/D:/biteJAVA/项目/20210117/src/sample/sample.fxml");
        //Parent root = FXMLLoader.load(FXML对应的URL);

        Parent root = FXMLLoader.load(getClass().getResource("file-scan.fxml"));
        System.out.println(root.getClass().getCanonicalName());
        //Parent root = build();
        primaryStage.setTitle("薇姐");
        Scene scene = new Scene(root, 1100, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
