package com.mystudy.ui;

import com.mystudy.entity.FileMeta;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class AppController {
    @FXML
    public TextField searchField;
    @FXML
    public Label srcDirectory;
    @FXML
    public GridPane rootPane;
    @FXML
    public TableView<FileMeta> fileTable;
    @FXML
    public TableColumn<FileMeta,String> nameColumn;
    @FXML
    public TableColumn<FileMeta,String> sizeColumn;
    @FXML
    public TableColumn<FileMeta,String> lastModifiedColumn;

    public void choose(MouseEvent mouseEvent) {
    }
}
