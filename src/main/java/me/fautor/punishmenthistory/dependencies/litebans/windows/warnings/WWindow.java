package me.fautor.punishmenthistory.dependencies.litebans.windows.warnings;

import litebans.api.Database;
import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.dependencies.managers.MenuManager;
import me.fautor.punishmenthistory.enums.manager.Statics;
import me.fautor.punishmenthistory.utils.Pages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static me.fautor.punishmenthistory.utils.Color.color;
import static me.fautor.punishmenthistory.utils.Color.message;

/**
 * WWindow.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class WWindow {

    private Main main;

    public WWindow(Main main, Player player) {
        this.main = main;

        ArrayList<ItemStack> item = new ArrayList<>();

        new Thread(() -> {

            String uuid = MenuManager.storage.get(player).getUniqueId().toString();
            final String query = "SELECT * FROM {warnings} WHERE uuid=?";

            try (PreparedStatement st = Database.get().prepareStatement(query)) {
                st.setString(1, uuid);
                try (ResultSet rs = st.executeQuery()) {
                    while (rs.next()) {

                        String reason = rs.getString("reason");

                        String warned_by_name = rs.getString("banned_by_name");
                        String removed_warn_by_name = rs.getString("removed_by_name");

                        long time = rs.getLong("time");
                        long until = rs.getLong("until");

                        boolean active = rs.getBoolean("active");
                        boolean silent = rs.getBoolean("silent");

                        List<String> list = new ArrayList<>();
                        list.add(color("&6Staff:&r " + (player.hasPermission("phistory.view.punisher") ? warned_by_name : "&n&mCENSORED")));
                        list.add(color("&e&m--------------------------"));
                        list.add(player.hasPermission("phistory.view.reason") ? color("&6Reason:&r " + reason) : color("&6Reason:&r &n&mCENSORED"));
                        list.add(color("&6Date:&r " + Statics.timeConverter(time)));
                        list.add(color("&6Silent:&r " + (silent ? "&eYes" : "&7No")));
                        list.add(color("&6Duration:&r " + Statics.duration(Statics.timeConverter(time), Statics.timeConverter(until))));
                        list.add(color("&6Expires in:&r " + (active ? Statics.timeLeft(Statics.timeConverter(until)) : "Already expired.")));
                        list.add(color("&e&m--------------------------"));

                        ItemStack warnGlass = new ItemStack(Material.STAINED_GLASS_PANE, 1, active ? (short) 13 : (short) 14);
                        ItemMeta warnGlassMeta = warnGlass.getItemMeta();
                        warnGlassMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        warnGlassMeta.setDisplayName(list.get(0));
                        list.remove(0);
                        if (active)
                        warnGlassMeta.setLore(list);
                        else {
                            list.add(color("&7✂ &6Removed by: &r"
                                    + (player.hasPermission("phistory.view.punisher") ? removed_warn_by_name : "&n&mCENSORED")));
                            list.add(color("&e&m--------------------------"));
                            warnGlassMeta.setLore(list);
                        }
                        warnGlass.setItemMeta(warnGlassMeta);
                        item.add(warnGlass);
                    }
                }
            } catch (SQLException exc) {
                exc.printStackTrace();
            }

        }).start();

        new BukkitRunnable() {
            @Override
            public void run() {
                main.menuManager.getItems().put(player, item);
                main.pages = new Pages(main, main.menuManager.getItems(), color("&6Warning(s)"), player);
            }
        }.runTaskLater(main, 20);
    }
}
