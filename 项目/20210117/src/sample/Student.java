package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Student {
    public int id;
    public String name;
    public int age;
    public String gender;

    public ObservableValue<String> getIdValue() {
        return new SimpleStringProperty(String.valueOf(id));
    }
    public String getId() {
        return String.valueOf(id);
    }

    public ObservableValue<String> getNameValue() {
        return new SimpleStringProperty(name);
    }
    public String getName() {
        return name;
    }

    public ObservableValue<String> getAgeValue() {
        return new SimpleStringProperty(String.valueOf(age));
    }
    public String getAge() {
        return String.valueOf(age);
    }

    public ObservableValue<String> getGenderValue() {
        return new SimpleStringProperty(gender);
    }
    public String getGender() {
        return gender;
    }

    public Student(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
