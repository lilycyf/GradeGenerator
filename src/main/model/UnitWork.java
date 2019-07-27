package model;

public class UnitWork {
    private int total;
    private int received;

    public UnitWork(int total, int received) {
        this.total = total;
        this.received = received;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getReceived() {
        return received;
    }

    public void setReceived(int received) {
        this.received = received;
    }
}
