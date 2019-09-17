package me.fautor.punishmenthistory.dependencies.advancedban;

import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.dependencies.advancedban.command.History;
import me.fautor.punishmenthistory.dependencies.advancedban.windows.bans.BWindow;
import me.fautor.punishmenthistory.dependencies.managers.MenuManager;
import me.fautor.punishmenthistory.utils.Pages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static me.fautor.punishmenthistory.utils.Color.color;
import static me.fautor.punishmenthistory.utils.Color.message;

/**
 * Menu.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class Menu implements Listener {

    private Main main;

    public Menu(Main main) {
        this.main = main;
    }

    public Inventory menu(Player target) {

        Inventory inventory = Bukkit.createInventory(null, 27, color("&e" + target.getName() +"'s Punishments"));

        ItemStack yellowGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
        ItemMeta yellowGlassMeta = yellowGlass.getItemMeta();
        yellowGlassMeta.setDisplayName(color("&r"));
        yellowGlass.setItemMeta(yellowGlassMeta);
        for (int i = 0; i < 10; i++) {
            inventory.setItem(i, yellowGlass);
        }

        for (int i = 17; i < 27; i++) {
            inventory.setItem(i, yellowGlass);
        }

        ItemStack warnGlass = new ItemStack(Material.STAINED_GLASS, 1, (short)1);
        ItemMeta warnGlassMeta = warnGlass.getItemMeta();
        warnGlassMeta.setDisplayName(color("&6Warning(s)"));
        warnGlass.setItemMeta(warnGlassMeta);
        inventory.setItem(11, warnGlass);

        ItemStack muteGlass = new ItemStack(Material.STAINED_GLASS, 1, (short)5);
        ItemMeta muteGlassMeta = muteGlass.getItemMeta();
        muteGlassMeta.setDisplayName(color("&aMute(s)"));
        muteGlass.setItemMeta(muteGlassMeta);
        inventory.setItem(13, muteGlass);

        ItemStack banGlass = new ItemStack(Material.STAINED_GLASS, 1, (short)11);
        ItemMeta banGlassMeta = banGlass.getItemMeta();
        banGlassMeta.setDisplayName(color("&cBan(s)"));
        banGlass.setItemMeta(banGlassMeta);
        inventory.setItem(15, banGlass);

        ItemStack grayGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
        ItemMeta grayGlassMeta = grayGlass.getItemMeta();
        grayGlassMeta.setDisplayName(color("&r"));
        grayGlass.setItemMeta(grayGlassMeta);
        for (int i = 10; i <= 16; i+=2) {
            inventory.setItem(i, grayGlass);
        }

        return inventory;

    }

    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (!MenuManager.storage.containsKey(player)) return;

        if (MenuManager.storage.containsKey(player)) e.setCancelled(true);

        if (e.getSlot() == 11) {

            if (MenuManager.checking_warnings.contains(player)) return;

            message(player, "&6&l⚠ &7Loading " + MenuManager.storage.get(player).getName() + "'s Warnings. May take a second.");

            if (Pages.users.containsKey(player)) {
                Pages p = Pages.users.get(player);
                p.getPages().clear();
                Pages.users.remove(player);
                main.menuManager.getItems().get(player).clear();
            }

            MenuManager.checking_warnings.add(player);
            Pages.page.put(player, 1);
//            new WWindow(main, player);
            e.setCancelled(true);

        }

        if (e.getSlot() == 13) {

            if (MenuManager.checking_mutes.contains(player)) return;

            message(player, "&6&l⚠ &7Loading " + MenuManager.storage.get(player).getName() + "'s Mutes. May take a second.");

            if (Pages.users.containsKey(player)) {
                Pages p = Pages.users.get(player);
                p.getPages().clear();
                Pages.users.remove(player);
                main.menuManager.getItems().clear();
            }

            MenuManager.checking_mutes.add(player);
            Pages.page.put(player, 1);
//            new MWindow(main, player);
            e.setCancelled(true);

        }

        if (e.getSlot() == 15) {

            if (MenuManager.checking_bans.contains(player)) return;

            message(player, "&6&l⚠ &7Loading " + MenuManager.storage.get(player).getName() + "'s Bans. May take a second.");

            if (Pages.users.containsKey(player)) {
                Pages p = Pages.users.get(player);
                p.getPages().clear();
                Pages.users.remove(player);
                main.menuManager.getItems().clear();
            }

            MenuManager.checking_bans.add(player);
            Pages.page.put(player, 1);
            new BWindow(main, player);
            e.setCancelled(true);

        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        HumanEntity entity = e.getPlayer();

        if (!(entity instanceof Player)) return;

        final Player player = (Player) entity;

        if (MenuManager.storage.containsKey(player))
            History.lastUsage.put(player.getName(), System.currentTimeMillis());

        //If main menu is closed
        main.getServer().getScheduler().runTaskLater(main, () -> {
            if (MenuManager.storage.containsKey(player) && !Pages.users.containsKey(player)) {
                MenuManager.storage.remove(player);
            }
        }, 20L);

        //If one of the warning pages is closed
        if (MenuManager.storage.containsKey(player) && Pages.users.containsKey(player)) {
            main.getServer().getScheduler().runTaskLater(main, () -> {
                if (MenuManager.checking_warnings.contains(player) && !player.getOpenInventory().getTitle().contains("Warning(s)")) {
                    MenuManager.checking_warnings.remove(player);
                    Pages.users.remove(player);
                    MenuManager.storage.remove(player);
                }
            }, 20L);
        }

        //If one of the mute pages is closed
        if (MenuManager.storage.containsKey(player) && Pages.users.containsKey(player)) {
            main.getServer().getScheduler().runTaskLater(main, () -> {
                if (MenuManager.checking_mutes.contains(player) && !player.getOpenInventory().getTitle().contains("Mute(s)")) {
                    MenuManager.checking_mutes.remove(player);
                    Pages.users.remove(player);
                    MenuManager.storage.remove(player);
                }
            }, 20L);
        }

        //If one of the ban pages is closed
        if (MenuManager.storage.containsKey(player) && Pages.users.containsKey(player)) {
            main.getServer().getScheduler().runTaskLater(main, () -> {
                if (MenuManager.checking_bans.contains(player) && !player.getOpenInventory().getTitle().contains("Ban(s)")) {
                    MenuManager.checking_bans.remove(player);
                    Pages.users.remove(player);
                    MenuManager.storage.remove(player);
                }
            }, 20L);
        }
    }

}
