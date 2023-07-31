package com.server.randommultiple.Utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ItemData {

    private static ItemStack result_ShortType(Material type, int amount) {
        ItemStack stack = new ItemStack(type, amount);
        return stack;
    }

    public static ItemStack result_NameType(Material type, int amount, String displayName) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        stack.setItemMeta(meta);
        return stack;
    }

    private static ItemStack result_LongType(Material type, int amount, String displayName, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
        return stack;
    }

    public static final ItemStack redStoneBlock = result_NameType(Material.REDSTONE_BLOCK, 1, (Color.chat("&c게임 종료")));
    public static final ItemStack emerald = result_NameType(Material.EMERALD, 1, (Color.chat("")));
    public static final ItemStack goldBlock = result_NameType(Material.GOLD_BLOCK, 1, (Color.chat("&f남은 횟수")));
    public static final ItemStack blackGlassPane = result_NameType(Material.BLACK_STAINED_GLASS_PANE, 1, (Color.chat("클릭!")));
    public static final ItemStack air = result_ShortType(Material.AIR, 1);
    public static final ItemStack lapis = result_NameType(Material.LAPIS_LAZULI, 1, (Color.chat("&f확률 공개")));

    public static ItemStack createCoin(String money, Player player, int amount) {
        return result_LongType(player.getInventory().getItemInMainHand().getType(), amount, Color.chat("&f[&a배율게임&f] " + money + "원"), Color.chat("&fRandomMultipleCoin"));
    }
}
