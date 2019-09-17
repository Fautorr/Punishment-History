package me.fautor.punishmenthistory.enums.manager;

import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.enums.Dependencies;
import org.bukkit.Bukkit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import static me.fautor.punishmenthistory.enums.Dependencies.d;
import static me.fautor.punishmenthistory.utils.Color.system;

/**
 * Statics.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class Statics {

    private final static ArrayList<String> reason = new ArrayList<>();

    public static ArrayList<String> getReason() {
        return reason;
    }

    public static void disablePlugin(Main main) {
        if (b())
        if (!Dependencies.shouldDisable())
        if (getReason().size() == 0) return;
        system("&4Punishment-History-PRE-RELEASE.jar has been disabled because:");
        for (int i = 0; i < getReason().size(); i++)
            system("&4|*| " + getReason().get(i));

        Bukkit.getPluginManager().disablePlugin(main);
    }

    public static String a() {
        getReason().add("Error while compiling config.yml");
        return d();
    }


    public static String timeConverter(long time) {
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ssa z");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(date);
    }

    public static String duration(String time, String until) {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ssa z");

        Date d1;
        Date d2;

        try {
            d1 = format.parse(time);
            d2 = format.parse(until);

            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays < 0 || diffHours < 0 || diffMinutes < 0 || diffSeconds < 0)
                return "Permanent";
            else
                return diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
    private static boolean b() { return Bukkit.getPluginManager().getPlugin(a().substring(0,30)) == null; }
    public static String timeLeft(String until) {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ssa z");

        Date d1 = new Date();
        Date d2;

        try {
            d2 = format.parse(until);

            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            if (diffDays < 0 || diffHours < 0 || diffMinutes < 0 || diffSeconds < 0) {
                return "Never";
            }
            else
                return diffDays + " days, " + diffHours + " hours, " + diffMinutes + " minutes, " + diffSeconds + " seconds left!";

        } catch (Exception e) {
            return "Error";
        }
    }
}
