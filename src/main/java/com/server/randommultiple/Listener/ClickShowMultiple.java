package com.server.randommultiple.Listener;

import com.server.randommultiple.Utils.MultipleSet;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.server.randommultiple.Main.plugin;

public class ClickShowMultiple implements Listener {

    @EventHandler
    public void InvClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!event.getView().getTitle().equals("[ 랜덤배율 ]")) {
            return;
        }

        MultipleSet multipleSet = new MultipleSet();
        multipleSet.multipleSet(player);

        if (event.getCurrentItem() == null) {
            return;
        }

        if (event.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        if (event.getClickedInventory() == null) {
            return;
        }

        if (event.getClickedInventory().equals(player.getInventory())) {
            return;
        }

        if (event.getRawSlot() >= event.getInventory().getSize()) {
            return;
        }

        /*if (event.getSlot() == plugin.getConfig().getInt(i)) {

        }*/
    }
}
