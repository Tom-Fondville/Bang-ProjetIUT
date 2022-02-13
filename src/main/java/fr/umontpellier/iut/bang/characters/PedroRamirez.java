package fr.umontpellier.iut.bang.characters;

import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.cards.Card;

import java.util.ArrayList;

public class PedroRamirez extends BangCharacter {

    public PedroRamirez() {
        super("Pedro Ramirez", 4);
    }

    @Override
    public void onStartTurn(Player player) {
        Card topOfDiscardPile = player.getGame().getTopOfDiscardPile();
        if (topOfDiscardPile != null) {
            Card card = player.chooseCard(
                    "Vous pouvez piocher une carte dans la défausse",
                    new ArrayList<>() {{
                        add(topOfDiscardPile);
                    }},
                    false,
                    true);
            if (card != null) {
                player.getGame().removeFromDiscard(card);
                player.addToHand(card);
                player.drawToHand();
            } else super.onStartTurn(player);
        } else super.onStartTurn(player);
    }
}
