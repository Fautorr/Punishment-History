package me.fautor.punishmenthistory.dependencies.advancedban.windows.bans;

import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.dependencies.managers.MenuManager;
import me.fautor.punishmenthistory.enums.manager.Statics;
import me.fautor.punishmenthistory.utils.Pages;
import me.leoko.advancedban.manager.PunishmentManager;
import me.leoko.advancedban.manager.UUIDManager;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static me.fautor.punishmenthistory.utils.Color.color;
import static me.fautor.punishmenthistory.utils.Color.message;

/**
 * BWindow.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class BWindow {

    private Main main;

    private ArrayList<ItemStack> item = new ArrayList<>();

    public BWindow(Main main, Player player) {
        this.main = main;

        String uuid = MenuManager.storage.get(player).getUniqueId().toString();

        List<Punishment> manager =
                PunishmentManager.get().getPunishments(UUIDManager.get().getUUID(MenuManager.storage.get(player).getName()), PunishmentType.BAN, false);

//        new Thread(() -> {
            for (Punishment m : manager) {
                message(player, m.getReason());
                String reason = m.getReason();

                String muted_by_name = m.getOperator();
                String removed_mute_by_name = "Your mom";

                long time = m.getStart();
                long until = m.getEnd();

                boolean active = m.isExpired();
//            boolean silent = ;
                List<String> list = new ArrayList<>();
                list.add(color("&6Staff:&r " + (player.hasPermission("phistory.view.punisher") ? muted_by_name : "&n&mCENSORED")));
                list.add(color("&e&m--------------------------"));
                list.add(player.hasPermission("phistory.view.reason") ? color("&6Reason:&r " + reason) : color("&6Reason:&r &n&mCENSORED"));
                list.add(color("&6Date:&r " + Statics.timeConverter(time)));
//            list.add(color("&6Silent:&r " + (silent ? "&eYes" : "&7No")));
                list.add(color("&6Duration:&r " + Statics.duration(Statics.timeConverter(time), Statics.timeConverter(until))));
                list.add(color("&6Expires in:&r " + (active ? Statics.timeLeft(Statics.timeConverter(until)) : "Already expired.")));
                list.add(color("&e&m--------------------------"));

                ItemStack banGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, active ? (short) 13 : (short) 14);
                ItemMeta banGlassMeta = banGlass.getItemMeta();
                banGlassMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                banGlassMeta.setDisplayName(list.get(0));
                list.remove(0);
                if (active)
                    banGlassMeta.setLore(list);
                else {
                    list.add(color("&7✂ &6Removed by: &r"
                            + (player.hasPermission("phistory.view.punisher") ? removed_mute_by_name : "&n&mCENSORED")));
                    list.add(color("&e&m--------------------------"));
                    banGlassMeta.setLore(list);
                }
                banGlass.setItemMeta(banGlassMeta);
                item.add(banGlass);
                message(player, item.size() + " size");
            }

        new BukkitRunnable() {
            @Override
            public void run() {
                main.menuManager.getItems().put(player, item);
                main.pages = new Pages(main, main.menuManager.getItems(), color("&cBan(s)"), player);
            }
        }.runTaskLater(main, 20);
    }
}

