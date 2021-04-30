package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.ArrayList;
import java.util.List;

public class Bang extends OrangeCard {

    public Bang(int value, CardSuit suit) {
        super("Bang!", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        if (!player.getBangCharacter().getName().equals("Calamity Janet"))
            super.playedBy(player);

        List<Player> rangePlayers = new ArrayList<>();
        for (Player otherPlayer : player.getOtherPlayers()) {
            if (player.distanceTo(otherPlayer) <= player.getWeaponRange()) {
                rangePlayers.add(otherPlayer);
            }
        }

        if (!rangePlayers.isEmpty()) {
            Player p = player.choosePlayer(
                    "Choisissez un joueur à attaquer",
                    rangePlayers,
                    false);

            player.shootAPlayer(p);
        }
    }
}
