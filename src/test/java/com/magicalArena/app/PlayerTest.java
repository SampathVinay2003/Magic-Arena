package com.magicalArena.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class PlayerTest {
    private Player player;
    @BeforeEach
    void setUp() {
        Arena.resetInstance(); // Reset the Singleton instance
        player = spy(new Player(50, 5, 10));
    }
    @Test
    void playerInitializationTest() {
        assertEquals(50, player.getHealth());
        assertEquals(5, player.getStrength());
        assertEquals(10, player.getAttack());
    }

    @Test
    void healthSetterTest() {
        player.setHealth(30);
        assertEquals(30, player.getHealth());
    }

    @Test
    void strengthSetterTest() {
        player.setStrength(30);
        assertEquals(30, player.getStrength());
    }

    @Test
    void healthGetterTest() {
        player.setHealth(player.getHealth() - 20);
        assertEquals(30, player.getHealth());
    }
}
