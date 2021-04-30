package fr.umontpellier.iut.bang.characters;

import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class KitCarlson extends BangCharacter {

    public KitCarlson() {
        super("Kit Carlson", 4);
    }

    @Override
    public void onStartTurn(Player player) {
        List<Card> threeCards = new ArrayList<>();
        Card one = player.drawCard();
        Card two = player.drawCard();
        Card three = player.drawCard();

        threeCards.add(one);
        threeCards.add(two);
        threeCards.add(three);

        Card chosen = player.chooseCard(
                "Choisissez une carte à retirer",
                threeCards,
                true,
                false);
        player.getGame().addToTopOfDrawPile(chosen);
        threeCards.remove(chosen);

        threeCards.forEach(player::addToHand);
    }
}
