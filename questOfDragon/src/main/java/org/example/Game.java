package org.example;

import java.util.Scanner;
import java.util.Random;
public class Game {
    private Scanner sc = new Scanner(System.in);
    private Random random = new Random();
    private Player player;
    private Dragon dragon;
    private Shield shield;
    private Sword sword;
    private Armor armor;

    private String[] options; // Possible inputs from the user/player
    private String answer; // The input from the user that exists in options[].

    private boolean quit = false;

    public Game(Player player, Dragon dragon, Shield shield, Sword sword, Armor armor) {
        this.player = player;
        this.dragon = dragon;
        this.shield = shield;
        this.sword = sword;
        this.armor = armor;

    }

    // The general game-loop that covers all actions that can be repeated.
    private void gameLoop() {

        while(!quit && player.isAlive() && dragon.isAlive() && !dragon.isPrincessRescued()) {

            System.out.println("Where would you like to go next, to the Forge, the Stables, the Watchtower or to the Dragon?");
            options = new String[]{"forge", "stables", "watchtower", "dragon"};
            answer = inputDecision(options);
            if(answer.equals("forge")){
                forge();
            } else if (answer.equals("stables")) {
                stables();
            } else if (answer.equals("watchtower")) {
                watchTower();
            } else {
                dragonsLair();
            }
        }
        if (dragon.isPrincessRescued()) {
            System.out.println("The princess has now been rescued and delivered safe back in the castle.");
            System.out.println("You have won the game!");
            gameFinished();
        }else {
            System.out.println("You didn't make it.");
            System.out.println("GAME OVER");
            gameFinished();
        }
    }
    // Loop until the combat is finished.
    private void combatLoop() {
        // The options are to either attack or dodge.
        options = new String[]{"dodge","attack"};
        // Calculate the damage for player and dragon based on inventory.
        int playerDamage = player.getAttackPower();
        int dragonDamage = dragon.getAttackPower();
        for (GameObject gameObject : player.getInventory()) {
            if (gameObject.getStatName().equals("Attack")) {
                playerDamage = playerDamage + gameObject.getValue();
            } else if (gameObject.getStatName().equals("Defence")) {
                dragonDamage = dragonDamage - gameObject.getValue();
            }
        }


        while (player.isAlive() && dragon.isAlive()) {
            System.out.println("The dragon gets angry and is ready to attack");
            System.out.println("Do you try to Dodge or Attack?");
            answer = inputDecision(options);
            if (answer.equals("dodge")) {
                if (diceRoll() > 2) {
                    System.out.println("You succesfully dodged the dragons breath!");
                } else {
                    System.out.println("You didn't manage to dodge the attack.");
                    System.out.println("You lost " + dragonDamage + " hp and now have " + (player.getHealthPoints()-dragonDamage) + " left!");
                    player.decreaseHealth(dragonDamage);
                }
            } else {
                if (diceRoll() >= 5) {
                    System.out.println("You managed to dodge the incoming attack and strike the dragon.");
                    System.out.println(dragon.getName() + " roars and loses " + playerDamage + " hp and now have " + (dragon.getHealthPoints()-playerDamage) + " hp left!");
                    dragon.decreaseHealth(playerDamage);
                } else {
                    System.out.println("You didn't manage to hit the dragon and got hit instead!");
                    System.out.println("The dragons flame hit you with " + dragonDamage + " damage and you now have " + (player.getHealthPoints()-dragonDamage) + " hp left!");
                    player.decreaseHealth(dragonDamage);
                }
            }
            if(player.isAlive() && dragon.isAlive()){
                System.out.println("The dragon seems tired from the attack.");
                System.out.println("Do you attack or dodge?");
                answer = inputDecision(options);
                if (answer.equals("attack") && diceRoll() > 1) {

                    System.out.println("You managed to hit the dragon!");
                    System.out.println(dragon.getName() + " loses " + playerDamage + " hp and now have " + (dragon.getHealthPoints()-playerDamage) + " hp left!");
                    dragon.decreaseHealth(playerDamage);
                } else if (answer.equals("attack") && diceRoll() == 1) {
                    System.out.println("You swing but miss!");
                }

                else {
                    System.out.println("You hide safely behind a rock.");
                }
            }

        }

    }
    // The start of the game that doesn't loop.
    public void gameStart() {
        System.out.println("You wake up and find a scroll with the kings sigil on it.");
        System.out.println("\"Hello " + player.getName() + " the kingdom needs your help.\nThe princess have been captured by the dragon and you need to save her.\"");
        System.out.println("Do you accept the mission? (yes/no)");
        options = new String[]{"yes", "no"};
        answer = inputDecision(options);
        if(answer.equals("no")) {
            quit = true;
            gameFinished();
        }else {
            gameLoop();
        }

    }
    private void gameFinished() {
        System.out.println("Thanks for playing!");
    }



    /**
     * @param choice the different possible inputs
     * @return the input
     */
    private String inputDecision(String[] choice) {
        String decision = "";
        boolean correctInput = false;

        while(!correctInput) {
            String input = sc.nextLine();
            input = input.toLowerCase();
            for (String checker : choice) {
                if (input.equals(checker)) {
                    decision = input;
                    correctInput = true;

                }
            }
            if (decision.equals("")) {
                System.out.print("Wrong input, possible inputs are: ");
                for (String s: choice) {
                    System.out.print("\"" + s + "\" ");
                }
                System.out.println("\nTry again!");
            } else{
                break;
            }

        }
        return decision;
    }

    /**
     *
     * @return a random integer n where 1 <= n <= 6
     */
    private int diceRoll() {
        return random.nextInt(1,7);

    }

    // Covers interactions at the area Forge.
    private void forge() {
        int correctAnswer = 2;
        int guess = 0;
        System.out.println("You have reached the forge and see the smith working there.");
        System.out.println("Do you talk to the smith? (yes/no)");
        options = new String[]{"yes","no"};
        answer = inputDecision(options);
        if (answer.equals("yes")) {
            System.out.println("\"Hello " + player.getName() +"!\"");
            System.out.println("\"Can you help me count how many swords I can make from 4 bars of iron if I need 2 bars for one sword?\"");

            boolean correctFormat = false;
            while (!correctFormat) {
                answer = sc.nextLine();
                try {
                    guess = Integer.parseInt(answer);
                    correctFormat = true;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input, should be a number like: \"1\"");
                }
            }
            if (guess == correctAnswer) {
                System.out.println("\"That is correct, thank you!\"");
                if(!player.getInventory().contains(armor)) {
                    System.out.println("\"Take this armor as a thanks.\"");
                    player.addToInventory(armor);
                    System.out.println("You received an armor that greatly increases your defence!");
                } else {
                    System.out.println("\"I do not have anything to give you sadly.\"");
                }

            } else {
                System.out.println("\"Wrong answer but you can come back later and try again!\"");
            }
        }
        System.out.println("You leave the forge and venture back.");
    }

    // Covers interactions at the stables.
    private void stables() {
        System.out.println("You've reached the stables and found a horse.");
        if(!player.getInventory().contains(shield)) {
            System.out.println("Next to the saddle you found a shield!");
            player.addToInventory(shield);
        }else {
            System.out.println("There is nothing you need in the stables.");
        }
        System.out.println("You leave the stables and head back.");


    }
    // Covers interactions at the watchtower.
    private void watchTower() {
        System.out.println("You've reached the watchtower.");
        System.out.println("Next to the door is a barrel, do you search it? (yes/no)");
        options = new String[]{"yes","no"};
        answer = inputDecision(options);
        if (answer.equals("yes") && !player.getInventory().contains(sword)) {
            System.out.println("In the barrel you found a sword!");
            player.addToInventory(sword);
        } else if (answer.equals("yes") && player.getInventory().contains(sword)) {
            System.out.println("The barrel is empty.");
        }
        System.out.println("You left the watchtower.");

    }

    // // Covers interactions at the dragons lair.
    private void dragonsLair() {
        System.out.println("You've reached the dragon's lair!");
        System.out.println("You see the princess and the dragon next to each other.");
        System.out.println("Do you talk with the dragon " + dragon.getName() + " or attack it?");
        options = new String[]{"talk","attack"};
        answer = inputDecision(options);
        if (answer.equals("talk") && diceRoll() <= 3 && player.getInventory().contains(sword)) {
            System.out.println("You greet the dragon and find out it's not aggressive.");
            System.out.println("In exchange for the princess you offer your sword and the dragon accepts!");
            dragon.setPrincessRescued(true);

        } else {
            combatLoop();
        }

    }


}
