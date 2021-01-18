package sample;

import file_scan.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.Random;

public class Controller {
    @FXML
    public TextField inputField;

    @FXML
    public Label label;

    private Thread thread = null;

    private Thread scanThread = null;

    @FXML
    public TableView<Student> tableView;

    @FXML
    public GridPane rootGridPane;

    private int id = 1;
    private final Random random = new Random(20210118);
    private final String[] genders = {"女","男"};
    private void addStudent(String name) {
        int age = random.nextInt(20)+10;
        String gender = genders[random.nextInt(2)];
        Student student = new Student(id++,name,age,gender);

        ObservableList<Student> items = tableView.getItems();
        items.add(student);
        System.out.println(student);
    }

    @FXML
    public void onClick(MouseEvent mouseEvent) {
        System.out.println("按钮被点击");


        //从输入框中获取文本信息
        String res = inputField.getText();
        addStudent(res);
        System.out.println(res);

        /*try {
            int i = Integer.valueOf(inputField.getText().trim());

            if (thread != null) {
                thread.interrupt();
            }
            thread = new Thread(() -> {
                long r = fib(i);

                System.out.printf("fib(%d) = %d\n",i,r);
                if (Thread.interrupted()) {
                    //thread = null;
                    return;
                }

                //涉及UI修改的，最好回到主线程中
                //多个线程修改可能产生线程安全问题
                Platform.runLater(() -> {
                    label.setText(String.valueOf(r));
                    //能保证thread就是当前线程吗？
                    //thread = null;  //不行，所以有BUG
                });

            });

            thread.start();
        }catch (NumberFormatException e){
            label.setText("请输入合法数字");
        }*/

    }

    private long fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }

    @FXML
    public void 选择文件夹(MouseEvent mouseEvent) {
        DirectoryChooser chooser = new DirectoryChooser();
        Window window = rootGridPane.getScene().getWindow();
        File root = chooser.showDialog(window);
        System.out.println(root);

        if (root == null) {
            return;
        }

        scanThread = new Thread(() -> Main.traversalDepth(root));
        //Daemon --- 精灵
        scanThread.setDaemon(true);//设置为后台线程
        scanThread.start();
        //JVM退出条件是所有前台线程都退掉
        //Main.traversalDepth(root); //应在子线程中运行
    }
}
