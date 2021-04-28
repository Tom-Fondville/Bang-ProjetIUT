package fr.umontpellier.iut.bang.cards;

import fr.umontpellier.iut.bang.Player;
import fr.umontpellier.iut.bang.Role;

import java.util.stream.Collectors;

public class Jail extends BlueCard {
    public Jail(int value, CardSuit suit) {
        super("Jail", value, suit);
    }

    @Override
    public void playedBy(Player player) {
        Player p = player.choosePlayer("Choisissez un joueur à mettre en prison (vous ne pouvez pas mettre le Sheriff en prison)",
                player.getOtherPlayers().stream().filter(c -> !c.getRole().equals(Role.SHERIFF)).collect(Collectors.toList()),
                false);

        if (p != null) {
            p.addToInPlay(this);
        }
    }
}
