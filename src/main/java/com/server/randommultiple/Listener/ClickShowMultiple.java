package com.server.randommultiple.Listener;

import com.server.randommultiple.UserData.Datas;
import com.server.randommultiple.Utils.MultipleSet;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.server.randommultiple.Main.plugin;

public class ClickShowMultiple implements Listener {

    @EventHandler
    public void InvClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!event.getView().getTitle().equals("[ 랜덤배율 ]")) {
            return;
        }

        YamlConfiguration config = Datas.getPlayerConfig(player);
        int getLimit = config.getInt("선택한 배율 개수");
        if (!(getLimit >= 1)) {
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

        config.set("선택한 배율 개수", getLimit + 1);

        for (int i = 0; i < 54; i++) {
            if (event.getSlot() == plugin.getConfig().getInt(String.valueOf(i))) {
                ItemStack itemStack = new ItemStack(Material.PAPER);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(String.valueOf(plugin.getConfig().getInt(String.valueOf(event.getSlot()))));
                Inventory inv = event.getInventory();
                inv.setItem(event.getSlot(), itemStack);
            }
        }
    }
}
