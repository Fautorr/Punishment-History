package me.fautor.punishmenthistory.utils;

import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.dependencies.managers.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static me.fautor.punishmenthistory.utils.Color.color;
import static me.fautor.punishmenthistory.utils.Color.message;

/**
 * Pages.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class Pages implements Listener {

    private Main main;

    public int current_page = 0;

    public Pages(Main main) {
        this.main = main;
    }

    public Pages(Main main, HashMap<Player, ArrayList<ItemStack>> items, String name, Player player) {
        this.main = main;

        Inventory inventory = blankInventory(name, player);

        for (int i = items.get(player).size() - 1; i >= 0; i--) {
            if (inventory.firstEmpty() == 18) {
                pages.add(inventory);
                inventory = blankInventory(name, player);
                inventory.addItem(items.get(player).get(i));
            } else {
                inventory.addItem(items.get(player).get(i));
            }
        }

        pages.add(inventory);
        player.openInventory(pages.get(current_page));
        users.put(player, this);
    }

    private Inventory blankInventory(String name, Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, name);

        ItemStack back = new ItemStack(Material.CARPET, 1, (byte) 4);
        ItemMeta backm = back.getItemMeta();
        backm.setDisplayName(color("&fBack"));
        backm.setLore(Collections.singletonList(color("&7&l* &eGo to Oldest Grants")));
        backm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        back.setItemMeta(backm);
        inventory.setItem(19, back);

        ItemStack next = new ItemStack(Material.CARPET, 1, (byte) 4);
        ItemMeta nextm = next.getItemMeta();
        nextm.setDisplayName(color("&fNext"));
        nextm.setLore(Collections.singletonList(color("&7&l* &eGo to Latest Grants")));
        nextm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        next.setItemMeta(nextm);
        inventory.setItem(25, next);

        ItemStack page_info = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta page_infoM = (SkullMeta) page_info.getItemMeta();
        page_infoM.setOwner(MenuManager.storage.get(player).getName());
        page_infoM.setDisplayName(color("&e" + MenuManager.storage.get(player).getName() + "'s Punishments"));
        page_infoM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        page_info.setItemMeta(page_infoM);
        inventory.setItem(22, page_info);

        ItemStack blank = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 0);
        ItemMeta blankm = blank.getItemMeta();
        blankm.setDisplayName(color("&r"));
        blankm.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        blank.setItemMeta(blankm);
        inventory.setItem(23, blank);
        inventory.setItem(24, blank);
        inventory.setItem(20, blank);
        inventory.setItem(21, blank);

        return inventory;
    }

    /**
     * Users are who are going to flip through pages
     */
    public static HashMap<Player, Pages> users = new HashMap<>();


    /**
     * Page is where it stores the page's number a player is viewing
     */
    public static HashMap<Player, Integer> page = new HashMap<>();

    /**
     * Pages is all the pages that will be stored
     */
    private ArrayList<Inventory> pages = new ArrayList<>();
    public ArrayList<Inventory> getPages() { return pages; }

}
