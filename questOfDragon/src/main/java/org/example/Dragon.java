package org.example;

public class Dragon extends Character {

    private boolean princessRescued;

    public Dragon(String name, int healthPoints, int attackPower) {
        super(name, healthPoints, attackPower);
        princessRescued = false;
    }

    @Override
    public void lastWords() {
        System.out.println(getName() + " roars and falls down and taking it's last breath.");
        setPrincessRescued(true);
    }

    public boolean isPrincessRescued() {
        return princessRescued;
    }

    public void setPrincessRescued(boolean princessRescued) {
        this.princessRescued = princessRescued;
    }

}

