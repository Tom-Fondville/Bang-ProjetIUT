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

            if (player.getBangCharacter().getName().equals("Slab the Killer")) {
                boolean jourdonaisWin, barrelWin, haveMissedCard1, haveMissedCard2;
                jourdonaisWin = (p.getBangCharacter().getName().equals("Jourdonnais") && p.randomDraw().getSuit().equals(CardSuit.HEART));
                barrelWin = p.barrelDraw();
                if (jourdonaisWin && barrelWin) return;
                haveMissedCard1 = p.askMissed();
                if ((jourdonaisWin || barrelWin) && haveMissedCard1) return;
                haveMissedCard2 = p.askMissed();
                if ((jourdonaisWin || barrelWin || haveMissedCard1) && haveMissedCard2) return;
                p.decrementHealth(1, player);
            } else if (!(p.getBangCharacter().getName().equals("Jourdonnais") && p.randomDraw().getSuit().equals(CardSuit.HEART)) && !p.barrelDraw() && !p.askMissed())
                p.decrementHealth(1, player);
        }
    }
}
