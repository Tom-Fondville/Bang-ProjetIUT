package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class CatBalou extends OrangeCard {

    public CatBalou(int value, CardSuit suit) {
        super("Cat Balou", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        List<Player> otherPlayers = player.getOtherPlayers();
        Player p = player.choosePlayer(
                "Choisissez un joueur",
                otherPlayers,
                false);

        List<Card> cInHand = new ArrayList<>(p.getInPlay());

        Card card = player.chooseCard(
                "Choisissez une carte à défausser",
                cInHand,
                false,
                true);
        if (card == null) {
            card = p.removeRandomCardFromHand();
            p.getGame().addToDiscard(card);
        } else {
            p.removeFromInPlay((BlueCard) card);
            p.discard(card);
        }
    }
}
