package com.server.randommultiple.Listener;

import com.server.randommultiple.UserData.Datas;
import com.server.randommultiple.Utils.Calculator;
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

        int getLimit = config.getInt("선택한 배율 개수");
        if (!(getLimit >= 3)) return;

        Calculator calculator = new Calculator();
        calculator.calculator(player);
    }
}
