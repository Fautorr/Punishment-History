package me.fautor.punishmenthistory.configuration;

import me.fautor.punishmenthistory.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static me.fautor.punishmenthistory.enums.manager.Statics.getReason;
import static me.fautor.punishmenthistory.utils.Color.system;

/**
 * ConfigFile.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class ConfigFile {

    private static File file;
    private static FileConfiguration config;

    public void load(Main main) {

        file = new File(main.getDataFolder(), "config.yml");

        if (!file.exists()) {

            system("&aThe configuration file could not be found, creating!");
            file.getParentFile().mkdirs();
            main.saveResource("config.yml", false);
            system("&aSuccessfully created and loaded the configuration file.");
        }

        config = new YamlConfiguration();

        try {

            config.load(file);

        } catch (Exception e) {

            e.printStackTrace();

            getReason().add("Error while loading config.yml!");
            getReason().add("Check your file to see if you have made a mistake.");

        }
    }

    public FileConfiguration get() {
        return config;
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() throws IOException {
        config.save(file);
    }

}
