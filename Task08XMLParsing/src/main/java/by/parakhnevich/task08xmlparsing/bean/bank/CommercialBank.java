package by.parakhnevich.task08xmlparsing.bean.bank;

import java.util.Objects;

public class CommercialBank extends Bank {
    private String owner;


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CommercialBank that = (CommercialBank) o;
        return Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), owner);
    }

    @Override
    public String toString() {
        return "Commercial bank" + '\n' + super.toString()
                +"Owner : " + owner + '\n';
    }
}
