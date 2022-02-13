package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;

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

        Card card = player.chooseCard(
                "Vous pouvez piocher une carte dans la défausse",
                new ArrayList<>(p.getInPlay()),
                false,
                true);

        if (card != null) {
            p.removeFromInPlay((BlueCard) card);
            player.addToHand(card);
        } else {
            Card randomCard = p.removeRandomCardFromHand();
            if (randomCard != null) player.addToHand(randomCard);
        }
    }
}

