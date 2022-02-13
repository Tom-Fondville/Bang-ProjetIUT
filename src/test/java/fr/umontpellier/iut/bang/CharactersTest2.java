package fr.umontpellier.iut.bang;

import fr.umontpellier.iut.bang.cards.*;
import fr.umontpellier.iut.bang.characters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharactersTest2 {
    Deque<Card> discardPile, drawPile;
    Player p1, p2, p3, p4;
    IOGame game;

    private void makeGameWithCharacter(BangCharacter character) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("p1", character, Role.OUTLAW));
        players.add(new Player("p2", new Nobody(), Role.RENEGADE));
        players.add(new Player("p3", new Nobody(), Role.DEPUTY));
        players.add(new Player("p4", new Nobody(), Role.SHERIFF));
        game = new IOGame(players);
        p1 = game.getPlayers().get(0);
        p2 = game.getPlayers().get(1);
        p3 = game.getPlayers().get(2);
        p4 = game.getPlayers().get(3);
        for (Player p : game.getPlayers()) {
            p.getHand().clear();
        }
        discardPile = TestUtils.getDiscardPile(game);
        drawPile = TestUtils.getDrawPile(game);
    }

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {
            }
        }));
    }

    @Test
    void testCalamityJanetJoueMissedCommeUnBang2() {
        makeGameWithCharacter(new CalamityJanet());
        assertEquals(4, p1.getHealthPointsMax());
        game.setInput("Missed!", "p2", "");
        Card missed = new Missed(1, CardSuit.HEART);

        p1.getHand().add(missed);
        p1.playTurn();
        assertEquals(3, p2.getHealthPoints());
        assertTrue(discardPile.contains(missed));
        assertEquals(1, discardPile.size());
    }
}