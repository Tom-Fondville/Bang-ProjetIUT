package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Gatling extends OrangeCard {

    public Gatling(int value, CardSuit suit) {
        super("Gatling", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);
        for (Player p : player.getOtherPlayers())
            if (!p.askMissed()) p.decrementHealth(1, player);
    }
}
