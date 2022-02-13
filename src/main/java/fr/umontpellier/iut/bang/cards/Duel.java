package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.stream.Collectors;

public class Duel extends OrangeCard {

    public Duel(int value, CardSuit suit) {
        super("Duel", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);

        Player player2 = player.choosePlayer(
                "Choisissez qui prendre en duel",
                player.getOtherPlayers(),
                false);

        Player cur = player;
        Card card;
        do {
            cur = cur == player2 ? player : player2;
            Player finalCur = cur;
            card = cur.chooseCard(
                    "Vous pouvez jouer une carte Bang!",
                    cur.getHand().stream().filter(c -> c.getName().equals("Bang!") || (finalCur.getBangCharacter().getName().equals("Calamity Janet") && c.getName().equals("Missed!"))).collect(Collectors.toList()),
                    false, true);
            cur.discardFromHand(card);
        } while (card != null);

        if (cur == player2) player2.decrementHealth(1, player);
        else player.decrementHealth(1, player2);
    }

}
