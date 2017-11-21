package po;

public class AccountPO {
    private int ID;
    private String name;
    private double banlance;

    public AccountPO(int ID, String name, double banlance) {
        this.ID = ID;
        this.name = name;
        this.banlance = banlance;
    }



    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBanlance() {
        return banlance;
    }

    public void setBanlance(double banlance) {
        this.banlance = banlance;
    }


}
