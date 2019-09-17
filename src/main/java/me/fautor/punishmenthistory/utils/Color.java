package me.fautor.punishmenthistory.utils;

import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.enums.Placeholders;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Color.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class Color {

    private Main main;

    private static Placeholder placeholder;

    public Color(Main main) {
        this.main = main;
        placeholder = new Placeholder(main);
    }

    public static void message(Player player, String string, Object o) {
        string = color(string, o);
        player.sendMessage(string);
    }

    public static void message(Player player, String string) {
        string = color(string);
        player.sendMessage(string);
    }

    public static void system(String string, Object o) {
        string = color(prefix + string, o);
        System.out.println(string);
    }

    public static void system(String string) {
        string = color(prefix + string);
        System.out.println(string);
    }

    private static String prefix = color("&6[P. History] &r", null);

    public static String color(String string, Object o) {
        for (Placeholders placeholders : Placeholders.values()) {
            if (string.contains(placeholders.getPlaceholder()))
                return ChatColor.translateAlternateColorCodes('&', placeholder.convert(string, o));
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
