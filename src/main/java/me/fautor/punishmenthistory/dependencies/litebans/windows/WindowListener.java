package me.fautor.punishmenthistory.dependencies.litebans.windows;

import me.fautor.punishmenthistory.Main;
import me.fautor.punishmenthistory.dependencies.managers.MenuManager;
import me.fautor.punishmenthistory.utils.Pages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * WindowListener.class was created by David(Fautor)
 * Please read History's LICENSE.md file to know your rights
 *
 * @author Fautor
 **/

public class WindowListener implements Listener {

    private Main main;

    public WindowListener(Main main) {
        this.main = main;
    }

    @EventHandler()
    public void onClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player)) return;

        Player player = (Player) e.getWhoClicked();

        if (!Pages.users.containsKey(player)) return;

        Pages user = Pages.users.get(player);

        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;

        if (MenuManager.storage.containsKey(player)) {

            e.setCancelled(true);
            if (e.getSlot() == 25) {
                e.setCancelled(true);
                if (user.current_page >= user.getPages().size() - 1) {
                    return;
                } else {
                    user.current_page += 1;
                    Pages.page.put(player, Pages.page.get(player) + 1);
                    player.openInventory(user.getPages().get(user.current_page));
                }
            }
            if (e.getSlot() == 19) {
                e.setCancelled(true);
                if (user.current_page > 0) {
                    user.current_page -= 1;
                    Pages.page.put(player, Pages.page.get(player) - 1);
                    player.openInventory(user.getPages().get(user.current_page));
                }
            }
        }
    }

}
