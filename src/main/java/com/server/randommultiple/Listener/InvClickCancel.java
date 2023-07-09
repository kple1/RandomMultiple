package com.server.randommultiple.Listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvClickCancel implements Listener {

    @EventHandler
    public void CancelEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;
        if (ChatColor.stripColor(event.getView().getTitle()).equalsIgnoreCase("[ 랜덤배율 ]")) {
            event.setCancelled(true);
        }
    }
}
