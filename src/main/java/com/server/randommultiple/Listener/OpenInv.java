package com.server.randommultiple.Listener;

import com.server.randommultiple.Utils.MultipleSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class OpenInv implements Listener {

    @EventHandler
    public void openInv(InventoryOpenEvent event) {
        if (!event.getView().getTitle().equals("[ 랜덤배율 ]")) {
            return;
        }

        Player player = (Player) event.getPlayer();

        MultipleSet multipleSet = new MultipleSet();
        multipleSet.multipleSet(player);
    }
}
