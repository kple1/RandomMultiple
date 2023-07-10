package com.server.randommultiple.Utils;

import com.server.randommultiple.UserData.Datas;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Calculator {

    private Economy economy;

    public void calculator(Player player) {
        //총 상금 지불
        YamlConfiguration config = Datas.getPlayerConfig(player);
        int getLimit = config.getInt("선택한 배율 개수");
        int getMoney = config.getInt("배팅금액");

        //긴 코드 예상 AI에게 부탁할거
        if (getLimit == 3) {
            //economy.withdrawPlayer(player, );
        }
    }
}
