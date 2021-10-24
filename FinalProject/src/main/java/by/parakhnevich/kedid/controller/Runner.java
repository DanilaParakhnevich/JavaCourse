package by.parakhnevich.kedid.controller;


import by.parakhnevich.kedid.service.DateCreator;

public class Runner {
    public static void main(String[] args) {
        System.out.println(new DateCreator().create());
    }
}
