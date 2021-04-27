package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;

public class Saloon extends OrangeCard {

    public Saloon(int value, CardSuit suit) {
        super("Saloon", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        for (Player p:player.getGame().getPlayers()) {
            p.incrementHealth(1);
        }
    }

}
