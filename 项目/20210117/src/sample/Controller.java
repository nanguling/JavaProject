package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {
    @FXML
    public TextField inputField;

    @FXML
    public Label label;

    private Thread thread = null;

    @FXML
    public void onClick(MouseEvent mouseEvent) {
        System.out.println("按钮被点击");

        //从输入框中获取文本信息
        System.out.println(inputField.getText());

        try {
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
        }

    }

    private long fib(int n) {
        if (n < 2) {
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }
}
