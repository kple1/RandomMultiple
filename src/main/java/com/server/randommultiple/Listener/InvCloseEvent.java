package com.server.randommultiple.Listener;

import com.server.randommultiple.Main;
import com.server.randommultiple.UserData.Datas;
import com.server.randommultiple.Utils.Calculator;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvCloseEvent implements Listener {

    @EventHandler
    public void invCloseEvent(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals("[ 랜덤배율 ]")) return;

        Player player = (Player) event.getPlayer();
        YamlConfiguration config = Datas.getPlayerConfig(player);

        //총 상금 지불 함수 입력 Calculator
        Calculator calculator = new Calculator();
        calculator.calculator(player);

        config.set("선택한 배율 개수", 0);
        config.set("배팅금액", 0);
        for (int i = 0; i < 54; i++) {
            config.set("배수." + i, 0);
        }
        Main.getPlugin().saveYamlConfiguration();
    }
}
