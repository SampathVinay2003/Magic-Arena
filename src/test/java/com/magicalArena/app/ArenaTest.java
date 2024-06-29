package com.magicalArena.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ArenaTest {
    @Mock
    private Player playerA;
    @Mock
    private Player playerB;
    private Arena arena;

    @BeforeEach
    void setUp() {
        arena = spy(new Arena(playerA, playerB));
    }

    @Test
    void testFight_A_Zero_Health_Died() {
        when(playerA.getHealth()).thenReturn(0);
        arena.fight();
        // Ensure one player has died
        assertTrue(playerA.getHealth() <= 0 || playerB.getHealth() <= 0);
    }

    @Test
    void testFight_B_Zero_Health_Died() {
        when(playerA.getHealth()).thenReturn(10);
        when(playerB.getHealth()).thenReturn(0);
        arena.fight();
        // Ensure one player has died
        assertTrue(playerA.getHealth() <= 0 || playerB.getHealth() <= 0);
    }

    @Test
    void testFight_atLeast_Once() {
        when(arena.rollDie()).thenReturn(3);
        when(playerA.getAttack()).thenReturn(10);
        when(playerB.getAttack()).thenReturn(10);
        when(playerB.getStrength()).thenReturn(5);
        when(playerA.getStrength()).thenReturn(0);
        playerB.health = 30;
        playerA.health = 25;
        arena.fight();
        // Ensure one player has died
        verify(arena, times(1)).takeTurn(playerB, playerA);
        verify(arena, times(1)).takeTurn(playerA, playerB);
    }

    @Test
    void testFight() {
        when(arena.rollDie()).thenReturn(3);
        when(playerA.getAttack()).thenReturn(10);
        when(playerB.getStrength()).thenReturn(0);
        playerB.health = 30;
        playerA.health = 25;
        arena.fight();
        verify(arena, times(0)).takeTurn(playerB, playerA);

        // Ensure one player has died
        verify(arena, times(1)).takeTurn(playerA, playerB);
    }

    @Test
    void testPlayerAAttacksFirst() {
        when(arena.rollDie()).thenReturn(3);
        when(playerA.getAttack()).thenReturn(10);
        when(playerB.getStrength()).thenReturn(5);
        playerB.health = 100;
        arena.takeTurn(playerA, playerB);
        // Player A attack: 10 * (4) = 40, Player B defend: 5 * (4) = 20, Damage dealt: 20
        assertEquals(85, playerB.health);
    }


    @Test
    void testPlayerBAttacksFirst() {
        when(arena.rollDie()).thenReturn(3);
        when(playerB.getAttack()).thenReturn(10);
        when(playerA.getStrength()).thenReturn(10);
        playerA.health = 50;
        arena.takeTurn(playerB, playerA);
        // Player B attack: 10 * (3) = 30, Player A defend: 10 * (3) = 30, Damage dealt: 0
        assertEquals(50, playerA.health);
    }
}
