package fr.umontpellier.iut.bang.characters;

import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.cards.Card;

public class JesseJones extends BangCharacter {

    public JesseJones() {
        super("Jesse Jones", 4);
    }

    @Override
    public void onStartTurn(Player player) {
        Player p = player.choosePlayer(
                "Choisissez un joueur afin de piocher une carte dans sa main au hasard (passer pour piocher dans la pile)",
                player.getOtherPlayers(),
                true);
        if (p != null) {
            Card card = p.removeRandomCardFromHand();
            p.discard(card);
            player.addToHand(card);
        } else {
            player.drawToHand();
        }
        player.drawToHand();
    }
}
