package me.fautor.punishmenthistory.enums;

import me.fautor.punishmenthistory.configuration.ConfigFile;
import org.bukkit.Bukkit;

import java.util.ArrayList;

import static me.fautor.punishmenthistory.enums.manager.Statics.getReason;

/**
 * Dependencies.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public enum Dependencies {

    LITEBANS("LiteBans", 1, false),
    ADVANCEDBAN("AdvancedBan", 2, false);

    private String name;
    private int id;
    private boolean enabled;

    Dependencies(String name, int id, boolean enabled) {
        this.name = name;
        this.id = id;
        ConfigFile configFile = new ConfigFile();
        enabled = configFile.get().getBoolean("Dependencies." + name);
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public int getId() { return id; }

    public boolean isEnabled() {
        return enabled;
    }

    public static void getBooleans() {
        Bukkit.broadcastMessage(ADVANCEDBAN.getName());
        ArrayList<String> arrays = new ArrayList<>();
        for (Dependencies dependencies : Dependencies.values()) {
            arrays.add("N: " + dependencies.getName() + " B:" + dependencies.isEnabled() + "\n");
        }
        Bukkit.broadcastMessage(arrays.toString());
    }

    private static boolean getBoolean() {
        ConfigFile configFile = new ConfigFile();
        for (Dependencies dependencies : Dependencies.values())
            return configFile.get().getBoolean("Dependencies." + dependencies.getName());
        return false;
    }

    public static boolean shouldDisable() {
        int i = 0;
        for (Dependencies dependencies : Dependencies.values()) {
            if (i <= 1) {
                if (dependencies.isEnabled()) {
                    i++;
                }
            }
            if (i > 1) {
                getReason().add("You have over 1 punishment systems enabled.");
                getReason().add("Disable the extra plugins via config.yml {PATH: Depencendies}");
                return true;
            }
            if (dependencies.isEnabled() && Bukkit.getPluginManager().getPlugin(dependencies.getName()) == null) {
                getReason().add(dependencies.getName() + ".jar is enabled but not added on the server.");
                return true;
            }
        }
        return false;
    }

    public static String d() {
        return "Punishment-History-PRE-RELEASE.jar by Fautor";
    }

}

