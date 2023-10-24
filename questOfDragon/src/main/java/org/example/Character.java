package org.example;

public abstract class Character {
    private int healthPoints;
    private String name;
    private int attackPower;
    private boolean isAlive;

    public Character(String name, int healthPoints, int attackPower){
        this.healthPoints = healthPoints;
        this.name = name;
        this.attackPower = attackPower;
        isAlive = true;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public String getName() {
        return name;
    }
    public int getAttackPower() {
        return attackPower;
    }
    public boolean isAlive() {
        return isAlive;
    }

    /**
     *
     * @param damage will decrease the characters healthpoints.
     */
    public void decreaseHealth(int damage){
        if(damage <= healthPoints){
            healthPoints = healthPoints - damage;
        }
        else{
            healthPoints = 0;
            isAlive = false;
            lastWords();
        }
    }

    public abstract void lastWords();


}
