package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

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

        List<Card> cInHand = p.getHand();
        cInHand.addAll(player.getInPlay());

        //Card c = p.chooseCard("Choisissez une carte à défausser", p.getHand(), false, false);
        Card blueCard = p.chooseCard("Choisissez une carte à défausser", cInHand, false, false);

        if (player.getHand().stream().anyMatch(c -> c.getName().equals(blueCard)))
        p.discardFromHand(blueCard);
        else (player.getInPlay().contains((BlueCard) blueCard)) {
            p.removeFromInPlay((BlueCard) blueCard);
            p.discard(blueCard);
        }
    }
}
