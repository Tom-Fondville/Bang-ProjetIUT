package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CatBalou extends OrangeCard {

    public CatBalou(int value, CardSuit suit) {
        super("Cat Balou", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        List<Player> otherPlayers = player.getOtherPlayers().stream().filter(p -> !p.getHand().isEmpty() || !p.getInPlay().isEmpty()).collect(Collectors.toList());
        Player p = player.choosePlayer("Choisissez un joueur", otherPlayers, false);

        List<Card> cInHand = new ArrayList<>(p.getHand());
        cInHand.addAll(p.getInPlay());

        Card card = p.chooseCard("Choisissez une carte à défausser", cInHand, false, false);

        if (p.getHand().contains(card)) {
            p.discardFromHand(card);
        } else if (p.getInPlay().contains((BlueCard) card)) {
            p.removeFromInPlay((BlueCard) card);
            p.discard(card);
        }
    }
}
