package by.parakhnevich.task08xmlparsing.controller;


import by.parakhnevich.task08xmlparsing.view.View;
import org.xml.sax.SAXException;

import java.io.IOException;


public class Runner {
    public static void main(String[] args) throws IOException, SAXException{
        new View().execute();
    }//src/main/resources/files/bankDeposits.xml
}
