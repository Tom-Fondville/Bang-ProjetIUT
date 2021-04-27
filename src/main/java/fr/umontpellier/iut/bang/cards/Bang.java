package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bang extends OrangeCard {

    public Bang(int value, CardSuit suit) {
        super("Bang!", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        super.playedBy(player);

        List<Player> rangePlayers = new ArrayList<>();
        for (Player otherPlayer : player.getOtherPlayers()) {
            if (player.distanceTo(otherPlayer) <= player.getWeaponRange()) {
                rangePlayers.add(otherPlayer);
            }
        }

        if (rangePlayers.size() > 1) {
            Player p = player.choosePlayer(
                    "Choisissez un joueur à attaquer",
                    rangePlayers,
                    false);

            Card c = p.chooseCard(
                    "Choisissez une carte Missed!",
                    p.getHand().stream().filter(m -> m.getName().equals("Missed!")).collect(Collectors.toList()),
                    false,
                    true);

            if (c == null || !c.getName().equals("Missed!")) {
                p.decrementHealth(1, player);
            } else {
                p.discardFromHand(c);
            }
        }
    }
}
