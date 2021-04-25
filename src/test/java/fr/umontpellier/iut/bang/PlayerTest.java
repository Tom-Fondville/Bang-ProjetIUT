package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.cards.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import static fr.umontpellier.iut.bang.cards.CardSuit.CLUB;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Game minimalGame;
    private Player p1, p2, p3, p4;

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {

            }
        }));

    }

    @BeforeEach
    void setUp() {
        List<Player> players = Game.makePlayers(new String[]{"Toto", "Titi", "Tutu", "Tata"});
        minimalGame = new Game(players);

        p1 = minimalGame.getPlayers().get(0);
        p2 = minimalGame.getPlayers().get(1);
        p3 = minimalGame.getPlayers().get(2);
        p4 = minimalGame.getPlayers().get(3);
    }


    @Test
    void testSetWeapon() {
        Volcanic v1 = new Volcanic(1, CLUB);
        p2.setWeapon(v1);
        assertEquals(p2.getWeapon(), v1);
        Remington r1 = new Remington(1, CLUB);
        p2.setWeapon(r1);
        assertEquals(p2.getWeapon(), r1);
    }

}