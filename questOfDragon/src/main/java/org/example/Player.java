package org.example;
import java.util.ArrayList;
public class Player extends Character{

    private ArrayList<GameObject> inventory;


    public Player(String name, int hp, int attackPower) {
        super(name, hp, attackPower);
        inventory = new ArrayList<>();
    }

    @Override
    public void lastWords() {
        System.out.println(getName() + " failed and died.");
    }

    /**
     *
     * @return an arraylist containing gameObjects.
     */
    public ArrayList<GameObject> getInventory() {
        return inventory;
    }

    /**
     *
     * @param gameObject to add to the arraylist.
     */
    public void addToInventory(GameObject gameObject) {
        inventory.add(gameObject);
    }

}
