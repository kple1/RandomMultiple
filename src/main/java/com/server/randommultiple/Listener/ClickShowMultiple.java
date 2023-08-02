package com.server.randommultiple.Listener;

import com.server.randommultiple.Main;
import com.server.randommultiple.UserData.Datas;
import com.server.randommultiple.Utils.Color;
import com.server.randommultiple.Utils.ItemData;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClickShowMultiple implements Listener {

    String title = (Color.chat("&f[ &a랜덤배율 &f] "));

    @EventHandler
    public void invClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        YamlConfiguration config = Datas.getPlayerConfig(player);
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

        for (int i = 0; i < 3; i++) {
            int getSlot = config.getInt("클릭한 슬롯" + i);
            if (event.getSlot() == getSlot) {
                player.sendMessage(title + "이미 선택한 슬롯입니다!");
                return;
            }
        }

        int getLimit = config.getInt("선택한 배율 개수");

        switch (getLimit) {
            case 0 -> inv.setItem(45, ItemData.air);
            case 1 -> inv.setItem(46, ItemData.air);
            case 2 -> inv.setItem(47, ItemData.air);
        }

        if (event.getSlot() == 52) {
            player.closeInventory();
        }

        //모두 클릭 했으면 확률 공개
        for (int i = 0; i < 45; i++) {
            if (getLimit == 3) {
                if (event.getSlot() == 53) {
                    ItemStack itemStack = new ItemStack(Material.DIAMOND);
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setDisplayName(config.getString("배수." + i));
                    itemStack.setItemMeta(itemMeta);
                    inv.setItem(i, itemStack);
                }
            }
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

        switch (getLimit) {
            case 0 -> {
                config.set("당첨된 배율1", name);
                config.set("클릭한 슬롯1", event.getSlot());
            }
            case 1 -> {
                config.set("당첨된 배율2", name);
                config.set("클릭한 슬롯2", event.getSlot());
            }
            case 2 -> {
                config.set("당첨된 배율3", name);
                config.set("클릭한 슬롯3", event.getSlot());
            }
        }
        Main.getPlugin().saveYamlConfiguration();
    }
}
