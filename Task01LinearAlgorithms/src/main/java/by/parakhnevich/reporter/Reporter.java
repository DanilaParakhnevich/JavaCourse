package by.parakhnevich.reporter;

/*
* This Reporter has method output that accept message and object
* and return object's method toString() + message
 */

public class Reporter {

    public Reporter(){}

    public void output(String msg, Object obj){
        System.out.println(obj.toString() + '\n' + msg);
    }
}
