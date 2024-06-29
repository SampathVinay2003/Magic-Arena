package com.magicalArena.app;

import java.util.Random;

public class Arena {
    private final Player playerA;
    private final Player playerB;
    private final Random random;

    public Arena(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.random = new Random();
    }

    private static class LazyHolder {
        private static Arena instance;
    }

    // Public method to provide access to the single instance
    public static Arena getInstance(Player playerA, Player playerB) {
        if (LazyHolder.instance == null) {
            LazyHolder.instance = new Arena(playerA, playerB);
        }
        return LazyHolder.instance;
    }

    // Reset instance for testing or other purposes
    public static void resetInstance() {
        LazyHolder.instance = null;
    }

    public void fight() {
        while (playerA.health > 0 && playerB.health > 0) {
            takeTurn(playerA, playerB);
            if (playerB.health > 0) {
                takeTurn(playerB, playerA);
            }
            System.out.println("Player A Health: " + playerA.health + ", Player B Health: " + playerB.health);
        }

        if (playerA.getHealth() <= 0) {
            System.out.println("Player A has died. Player B wins!");
        } else {
            System.out.println("Player B has died. Player A wins!");
        }
    }

    protected int rollDie() {
        return random.nextInt(6) + 1; // 1 to 6 sided die
    }

    protected void takeTurn(Player attacker, Player defender) {
        int attackRoll = rollDie();
        int defenseRoll = rollDie();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defenseStrength = defender.getStrength() * defenseRoll;

        int damageTaken = Math.max(0, attackDamage - defenseStrength);
        defender.health-=damageTaken;

        System.out.println("Attacker rolls " + attackRoll + ", Defender rolls " + defenseRoll);
        System.out.println("Attack damage: " + attackDamage + ", Defense strength: " + defenseStrength);
        System.out.println("Defender health reduced by " + damageTaken + " to " + defender.health + "\n");
    }
}
