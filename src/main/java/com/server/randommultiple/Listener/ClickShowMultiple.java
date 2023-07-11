package com.server.randommultiple.Listener;

import com.server.randommultiple.Main;
import com.server.randommultiple.UserData.Datas;
import com.server.randommultiple.Utils.Color;
import com.server.randommultiple.Utils.MultipleSet;
import net.milkbowl.vault.economy.Economy;
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

    String title = (Color.chat("&f[ &a랜덤배율 &f] "));

    @EventHandler
    public void InvClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getInventory();

        if (!event.getView().getTitle().equals("[ 랜덤배율 ]")) {
            return;
        }

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

        YamlConfiguration config = Datas.getPlayerConfig(player);
        int getLimit = config.getInt("선택한 배율 개수");

        //모두 클릭 했으면 확률 공개
        boolean r = false;
        for (int i = 0; i < 54; i++) {
            if (getLimit == 3) {
                ItemStack itemStack = new ItemStack(Material.DIAMOND);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(config.getString("배수." + i));
                itemStack.setItemMeta(itemMeta);
                inv.setItem(i, itemStack);
                r = true;
            }
        }

        if (r) {
            player.sendMessage(title + "이미 모두 선택 하셨습니다!");
        }

        if (getLimit == 3) {
            return;
        }

        //클릭한 배율 공개
        ItemStack itemStack = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta = itemStack.getItemMeta();
        String name = config.getString("배수." + event.getSlot());
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        inv.setItem(event.getSlot(), itemStack);

        config.set("선택한 배율 개수", getLimit + 1);
        config.set("당첨된 배율", name);
        Main.getPlugin().saveYamlConfiguration();
    }
}
