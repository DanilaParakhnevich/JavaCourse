package by.parakhnevich.task08xmlparsing.bean.deposit;

import java.util.Objects;

public class Depositor {
    private String name;
    private long accountId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depositor depositor = (Depositor) o;
        return Objects.equals(name, depositor.name) && Objects.equals(accountId, depositor.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, accountId);
    }

    @Override
    public String toString() {
        return  name + '\n' + "Account id = " + accountId;
    }
}
