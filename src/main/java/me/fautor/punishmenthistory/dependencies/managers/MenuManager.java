package me.fautor.punishmenthistory.dependencies.managers;

import me.fautor.punishmenthistory.Main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * MenuManager.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class MenuManager {

    private Main main;

    public MenuManager(Main main) {
        this.main = main;
    }

    /**
     * Storage stores all players that are checking a player's history
     */
    public static HashMap<Player, Player> storage = new HashMap<>();

    /**
     * Items store all ItemStacks of a player's history
     */
    private HashMap<Player, ArrayList<ItemStack>> items = new HashMap<>();
    public HashMap<Player, ArrayList<ItemStack>> getItems() { return items; }

    public static ArrayList<Player> checking_warnings = new ArrayList<>();
    public static ArrayList<Player> checking_mutes = new ArrayList<>();
    public static ArrayList<Player> checking_bans = new ArrayList<>();

}
