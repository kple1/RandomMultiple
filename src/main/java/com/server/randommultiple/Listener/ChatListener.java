package com.server.randommultiple.Listener;

import com.server.randommultiple.Main;
import com.server.randommultiple.UserData.Datas;
import com.server.randommultiple.Utils.Color;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.HashMap;

public class ChatListener implements Listener {

    private final Player player;
    private Inventory inv;
    private Economy economy;
    String title = (Color.chat("&f[ &a랜덤배율 &f] "));

    public ChatListener(Player player) {
        this.player = player;
    }

    public void checkVault(Player player) {
        // Vault 플러그인을 이용하여 Economy 객체 가져오기
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        } else {
            player.sendMessage("Vault 플러그인이 필요합니다.");
            return;
        }
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player getMessagePlayer = event.getPlayer();
        String message = event.getMessage();
        if (!message.isEmpty()) {

            checkVault(getMessagePlayer);
            double balance = economy.getBalance(getMessagePlayer);
            if (!(Double.parseDouble(message) > balance) && Double.parseDouble(message) != 0) {

                economy.withdrawPlayer(getMessagePlayer, Double.parseDouble(message));

                YamlConfiguration config = Datas.getPlayerConfig(getMessagePlayer);
                config.set("배팅금액", Integer.parseInt(message));
                Main.getPlugin().saveYamlConfiguration();

                /* 인벤오픈 */
                ItemStack blackGlassPane = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemStack goldBlock = new ItemStack(Material.GOLD_BLOCK);
                ItemStack emerald = new ItemStack(Material.EMERALD);
                ItemStack redStoneBlock = new ItemStack(Material.REDSTONE_BLOCK);

                ItemMeta getMetaGoldBlock = goldBlock.getItemMeta();
                getMetaGoldBlock.setDisplayName(Color.chat("&f남은 횟수"));
                goldBlock.setItemMeta(getMetaGoldBlock);

                ItemMeta getMetaRedStoneBlock = redStoneBlock.getItemMeta();
                getMetaRedStoneBlock.setDisplayName(Color.chat("&c게임 종료"));
                redStoneBlock.setItemMeta(getMetaRedStoneBlock);

                this.inv = Bukkit.createInventory(null, 54, "[ 랜덤배율 ]");
                for (int i = 0; i < 45; i++) {
                    inv.setItem(i, blackGlassPane);
                }
                for (int i = 45; i < 48; i++) {
                    inv.setItem(i, goldBlock);
                }
                for (int i = 48; i < 53; i++) {
                    inv.setItem(i, emerald);
                }
                inv.setItem(53, redStoneBlock);
                player.openInventory(inv);

            } else {
                getMessagePlayer.sendMessage(title + "금액이 부족해서 배팅할 수 없습니다!");
                HandlerList.unregisterAll(this);
                event.setCancelled(true);
            }

            // 이벤트 핸들러를 제거합니다.
            HandlerList.unregisterAll(this);
            event.setCancelled(true);
        }
    }
}
