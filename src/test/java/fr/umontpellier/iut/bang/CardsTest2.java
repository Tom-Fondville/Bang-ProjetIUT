package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.cards.Bang;
import fr.umontpellier.iut.bang.cards.Card;
import fr.umontpellier.iut.bang.cards.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardsTest2 {
    IOGame simpleGame;
    Deque<Card> discardPile, drawPile;
    Player p1, p2, p3, p4, p5;

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
        List<Player> players = new ArrayList<>();
        players.add(new Player("p1", new Nobody(), Role.OUTLAW));
        players.add(new Player("p2", new Nobody(), Role.RENEGADE));
        players.add(new Player("p3", new Nobody(), Role.OUTLAW));
        players.add(new Player("p4", new Nobody(), Role.DEPUTY));
        players.add(new Player("p5", new Nobody(), Role.SHERIFF));
        simpleGame = new IOGame(players);
        for (Player p : simpleGame.getPlayers()) {
            p.getHand().clear();
        }
        discardPile = TestUtils.getDiscardPile(simpleGame);
        drawPile = TestUtils.getDrawPile(simpleGame);
        p1 = simpleGame.getPlayers().get(0);
        p2 = simpleGame.getPlayers().get(1);
        p3 = simpleGame.getPlayers().get(2);
        p4 = simpleGame.getPlayers().get(3);
        p5 = simpleGame.getPlayers().get(4);
    }

    @Test
    void testNoVolcanic() {
        simpleGame.setInput("Bang!", "p2", "Bang!", "p2", "Bang!", "p2", "");
        Card bang1 = new Bang(1, CardSuit.SPADE);
        Card bang2 = new Bang(1, CardSuit.SPADE);
        Card bang3 = new Bang(1, CardSuit.SPADE);

        p1.getHand().add(bang1);
        p1.getHand().add(bang2);
        p1.getHand().add(bang3);
        assertEquals(1, p1.getWeaponRange());
        p1.playTurn();
        assertEquals(3, p2.getHealthPoints());
        assertTrue(discardPile.contains(bang1));
        assertFalse(discardPile.contains(bang2));
        assertFalse(discardPile.contains(bang3));
    }
}
