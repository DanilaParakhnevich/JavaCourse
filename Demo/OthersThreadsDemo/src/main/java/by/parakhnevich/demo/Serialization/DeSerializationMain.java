package by.parakhnevich.demo.Serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
public class DeSerializationMain {
    public static void main(String[] args) {
        Student.faculty = "GEO";
        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream("o.dat"))) {
            Student student = (Student) input.readObject();
            System.out.println(student);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
