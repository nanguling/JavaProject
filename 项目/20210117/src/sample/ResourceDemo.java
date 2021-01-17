package sample;

import java.net.URL;

public class ResourceDemo {
    public static void main(String[] args) {
        URL r1 = ResourceDemo.class.getResource("sample.fxml");
        URL r2 = ResourceDemo.class.getClassLoader().getResource("root.fxml");
        System.out.println(r1);
        System.out.println(r2);
    }
}
