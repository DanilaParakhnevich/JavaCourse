package by.parakhnevich.entity;

public class RegularTriangle extends Triangle{
    public RegularTriangle(double side){
        super(side,side,side);
    }

    public double getSide(){
        return this.getFirst();
    }

    @Override
    public String toString() {
        return "RegularTriangle{" +
                "side=" + this.getFirst() +
                '}';
    }
}
