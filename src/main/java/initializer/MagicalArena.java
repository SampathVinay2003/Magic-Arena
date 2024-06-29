package initiliazer;

import com.magicalArena.app.Arena;
import com.magicalArena.app.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MagicalArena {

    public static void main(String[] args) {
        SpringApplication.run(MagicalArena.class, args);

        // Initiate the game
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);

        Arena arena = Arena.getInstance(playerA, playerB);
        arena.fight();
    }
}
