package com.server.randommultiple.Listener;

import com.server.randommultiple.Main;
import com.server.randommultiple.UserData.Datas;
import com.server.randommultiple.Utils.Color;
import com.server.randommultiple.Utils.ItemData;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.server.randommultiple.Main.plugin;

public class Citizen implements Listener {

    String title = (Color.chat("&f[ &a랜덤배율 &f] "));
    private Inventory inv;

    @EventHandler
    public void npcOpenEvent(NPCRightClickEvent event) {
        Player player = event.getClicker();
        NPC npc = event.getNPC();
        String getNPC = plugin.getConfig().getString("NPC 설정");

        if (!npc.getName().equals(getNPC)) {
            return;
        }

        /* 배율입력 && 인벤오픈 */
        ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
        if (itemMeta == null || !itemMeta.hasLore() || !itemMeta.getLore().contains(Color.chat("§fRandomMultipleCoin"))) {
            player.sendMessage(title + "코인을 들고 NPC를 클릭해주세요.");
            return;
        }

        int num = 0;
        String displayName = itemMeta.getDisplayName();

         String pattern = "\\d+";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(displayName);

        if (matcher.find()) {
            String numericPart = matcher.group();
            num = Integer.parseInt(numericPart);
        }

        YamlConfiguration config = Datas.getPlayerConfig(player);
        config.set("배팅금액", num);
        Main.getPlugin().saveYamlConfiguration();

        this.inv = Bukkit.createInventory(null, 54, "[ 랜덤배율 ]");
        for (int i = 0; i < 45; i++) {
            inv.setItem(i, ItemData.blackGlassPane);
        }
        for (int i = 45; i < 48; i++) {
            inv.setItem(i, ItemData.goldBlock);
        }
        for (int i = 48; i < 52; i++) {
            inv.setItem(i, ItemData.emerald);
        }
        inv.setItem(52, ItemData.redStoneBlock);
        inv.setItem(53, ItemData.lapis);
        player.openInventory(inv);
        player.getInventory().removeItem(player.getInventory().getItemInMainHand());
    }
}
