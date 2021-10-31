package by.parakhnevich.keddit.controller;


import by.parakhnevich.keddit.service.Bcrypt;
import by.parakhnevich.keddit.service.PhotoNameGenerator;

public class Runner {
    public static void main(String[] args) {
        System.out.println(new PhotoNameGenerator().generate("Keddit_Official", ".png"));
        System.out.println(new Bcrypt().encrypt("roman"));
    }
}
