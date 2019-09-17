package me.fautor.punishmenthistory.dependencies.managers;

import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.Register;
import me.fautor.punishmenthistory.dependencies.litebans.command.History;
import me.fautor.punishmenthistory.dependencies.litebans.Menu;
import me.fautor.punishmenthistory.dependencies.litebans.windows.WindowListener;
import me.fautor.punishmenthistory.enums.Dependencies;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

/**
 * DependencyManager.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class DependencyManager {

    public Main main;

    public DependencyManager(Main main) {
//        super(main);
    }

//    @Override
//    public void registerCommands() {
//        if (Dependencies.LITEBANS.isEnabled()) {
//            main.getCommand("history").setExecutor(new me.fautor.punishmenthistory.dependencies.litebans.command.History(main));
//        }
//        if (Dependencies.ADVANCEDBAN.isEnabled()) {
//            main.getCommand("history").setExecutor(new me.fautor.punishmenthistory.dependencies.advancedban.command.History(main));
//        }
//    }
//
//    @Override
//    public void registerListeners() {
//        if (Dependencies.LITEBANS.isEnabled()) {
//            PluginManager p = Bukkit.getPluginManager();
//            p.registerEvents(new me.fautor.punishmenthistory.dependencies.litebans.Menu(main), main);
//            p.registerEvents(new me.fautor.punishmenthistory.dependencies.litebans.windows.WindowListener(main), main);
//        }
//        if (Dependencies.ADVANCEDBAN.isEnabled()) {
//            PluginManager p = Bukkit.getPluginManager();
//            p.registerEvents(new me.fautor.punishmenthistory.dependencies.advancedban.Menu(main), main);
//            p.registerEvents(new me.fautor.punishmenthistory.dependencies.advancedban.windows.WindowListener(main), main);
//        }
//    }

}
