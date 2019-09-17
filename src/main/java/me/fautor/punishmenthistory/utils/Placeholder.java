package me.fautor.punishmenthistory.utils;

import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.enums.Placeholders;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Placeholder.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class Placeholder {

    private Main main;

    public Placeholder(Main main) {
        this.main = main;
    }

    /**
     * This string method will help convert
     * a placeholder to its value.
     *
     * @param placeholder is the string that will be looked for when trying to convert.
     * @param o is an object value that can be determined when it is called.
     * @return the change of the placeholder into its real value.
     */
    public String convert(String placeholder, Object o) {

        if (isSpotted(placeholder, Placeholders.PLAYER_NAME.getPlaceholder()))
            return placeholder.replaceAll(Placeholders.PLAYER_NAME.getPlaceholder(), ((Player) o).getName());

        if (isSpotted(placeholder, Placeholders.LINE.getPlaceholder()))
            return placeholder.replaceAll(Placeholders.LINE.getPlaceholder(), "&m--------------------------");

        return placeholder;
    }

    /**
     * This boolean method will look to see if a
     * string contains an existing placeholder.
     *
     * @param givenPlaceholder The placeholder that is being looked for to convert.
     * @param placeholder The placeholder that already exists in the plugin.
     * @return if the placeholder that is being looked for does exist.
     */
    private boolean isSpotted(String givenPlaceholder, String placeholder) {
        return givenPlaceholder.contains(placeholder);
    }

}
