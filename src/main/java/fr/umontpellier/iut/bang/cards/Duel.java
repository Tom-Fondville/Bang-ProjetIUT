package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Duel extends OrangeCard {

    public Duel(int value, CardSuit suit) {
        super("Duel", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);
        //choie de l'adversaire
        List<Player> otherPlayer = player.getOtherPlayers();
        Player player2 = player.choosePlayer(
                "Choissiez qui prendre en duel",
                otherPlayer,
                false);


        boolean b = true;
        while (true) {
            if (b) {
                Card cardP2 = player2.chooseCard("Vous pouvez jouer une carte Bang!", player2.getHand().stream().filter(c -> c.getName().equals("Bang!")).collect(Collectors.toList()), false, true);
                if (cardP2 == null) break;
                player2.discardFromHand(cardP2);
                b = false;
            } else {
                Card cardP = player.chooseCard("Vous pouvez jouer une carte Bang!", player.getHand().stream().filter(c -> c.getName().equals("Bang!")).collect(Collectors.toList()), false, true);
                if (cardP == null) break;
                player.discardFromHand(cardP);
                b = true;
            }
        }
        if (b) player2.decrementHealth(1, player);
        else player.decrementHealth(1, player2);
    }

}
