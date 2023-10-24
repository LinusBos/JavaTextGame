package org.example;

public class GameObject {
    private int value;
    private String statName;
    private int durability = 10;
    private boolean broken = false;
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }


    public GameObject (String statName, int value){
        this.statName = statName;
        this.value = value;
    }
    public void decreaseDurability(int damage) {
        if(damage < durability){
            durability = durability - damage;
        }
        else {
            durability = 0;
            broken = true;
        }

    }

}
