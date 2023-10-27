package org.example;

public class GameObject {
    private int value;
    private String statName;
    public int getValue() {
        return value;
    }

    public String getStatName() {
        return statName;
    }

    public GameObject (String statName, int value){
        this.statName = statName;
        this.value = value;
    }


}
