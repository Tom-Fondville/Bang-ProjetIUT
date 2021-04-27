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
        List<Card> drawnCards = new ArrayList<>();
        drawnCards.add(player.drawCard());
        drawnCards.add(player.drawCard());
        return player.chooseCard("Choisissez la carte à dégainer",drawnCards,false,false);
    }
}
