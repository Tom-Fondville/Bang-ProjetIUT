package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

import java.util.stream.Collectors;

public class Indians extends OrangeCard {

    public Indians(int value, CardSuit suit) {
        super("Indians!", value, suit);
    }

    @Override
    public void playedBy(Player player) {

        for (Player p : player.getOtherPlayers()) {
            Card b = p.chooseCard(
                    "Défaussez-vous d'une carte Bang!",
                    p.getHand().stream().filter(m -> m.getName().equals("Bang!") || p.getBangCharacter().getName().equals("Calamity Janet") && m.getName().equals("Missed!")).collect(Collectors.toList()),
                    false,
                    true);

            if (b != null) {
                p.removeFromHand(b);
                p.discard(b);
            } else {
                p.decrementHealth(1, null);
            }
        }
    }
}


