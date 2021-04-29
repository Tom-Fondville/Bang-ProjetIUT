package fr.umontpellier.iut.bang.characters;

import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class LuckyDuke extends BangCharacter {

    public LuckyDuke() {
        super("Lucky Duke", 4);
    }

    @Override
    public Card randomDraw(Player player) {
        Card card1 = player.getGame().drawCard();
        player.discard(card1);
        Card card2 = player.getGame().drawCard();
        player.discard(card2);
        List<String> drawnCardsPoker = new ArrayList<>();
        drawnCardsPoker.add(card1.getPokerString());
        drawnCardsPoker.add(card2.getPokerString());
        if (card1.getPokerString().equals(player.choose("Choisissez la valeur poker qui vous convient",drawnCardsPoker,true,false))) return card1;
        return card2;
    }
}
