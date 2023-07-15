package com.server.randommultiple.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemData {

    private static ItemStack result_ShortType(Material type, int amount) {
        ItemStack stack = new ItemStack(type, amount);
        return stack;
    }

    public static ItemStack result_NameType(Material type, int amount, String displayName) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        return stack;
    }

    public static final ItemStack redStoneBlock = result_NameType(Material.REDSTONE_BLOCK, 1, (Color.chat("&c게임 종료")));
    public static final ItemStack emerald = result_NameType(Material.EMERALD, 1, (Color.chat("")));
    public static final ItemStack goldBlock = result_NameType(Material.GOLD_BLOCK, 1, (Color.chat("&f남은 횟수")));
    public static final ItemStack blackGlassPane = result_NameType(Material.BLACK_STAINED_GLASS_PANE, 1, (Color.chat("클릭!")));
    public static final ItemStack air = result_ShortType(Material.AIR, 1);
}
