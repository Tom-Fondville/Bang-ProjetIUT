package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Panic extends OrangeCard {

    public Panic(int value, CardSuit suit) {
        super("Panic!", value, suit);
    }

    @Override
    public boolean canPlayFromHand(Player player) {
        return !player.getPlayersInRange(1).isEmpty();
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);

        Player p = player.choosePlayer(
                "Choisissez un joueur à braquer",
                player.getPlayersInRange(1),
                false);

        p.removeRandomCardFromHand();

        player.addToHand(p.removeRandomCardFromHand());
    }
}
