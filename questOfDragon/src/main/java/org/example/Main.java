package org.example;

/*
 A game about a knight on a quest to save a princess held captive by a dragon in its lair.
 This can be achieved in different ways, it's up to the player how it will be done.
 Made as an assignment in object-oriented programming course at NBI.
 Made by Linus Boström.
*/

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Art art = new Art();
        art.printDragon();
        System.out.println("Welcome to Quest of Dragon!");
        System.out.println("In a kingdom far, far away a princess is being held captive by a dragon.");
        System.out.println("You are the only one that can rescue her, what is your name knight?");
        String knightName = sc.nextLine();
        Player player = new Player(knightName,50, 5);
        Dragon dragon = new Dragon("Mushu",100, 45);
        Sword sword = new Sword("Attack",30);
        Shield shield = new Shield("Defence", 10);
        Armor armor = new Armor("Defence", 30);
        Game game = new Game(player, dragon, shield, sword, armor);
        game.gameStart();

    }

}
