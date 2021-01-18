package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;

public class FileController {
    @FXML
    public GridPane rootGridPane;

    @FXML
    public TableView<FileMeta> tableView;

    @FXML
    public void chooseFile(MouseEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        Window window = rootGridPane.getScene().getWindow();
        File file = chooser.showDialog(window);

        if (file == null) {
            return;
        }
        Thread thread = new Thread(() -> traversalDepth(file));
        thread.setDaemon(true);
        thread.start();
    }

    public void traversalDepth(File root) {

        //涉及UI修改的放到主线程中修改
        Platform.runLater(() -> {
            FileMeta fileMeta = new FileMeta(root);
            tableView.getItems().add(fileMeta);
        });

        if (!root.isDirectory()) {
            return;
        }

        File[] files = root.listFiles();
        if (files == null) {
            return;
        }
        for (File fileChild:files) {
            traversalDepth(fileChild);
        }
    }


}
