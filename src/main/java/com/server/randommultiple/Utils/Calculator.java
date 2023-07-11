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
        String getMultiple1 = config.getString("당첨된 배율.1");
        String getMultiple2 = config.getString("당첨된 배율.2");
        String getMultiple3 = config.getString("당첨된 배율.3");

        double result = 0;
        String[] operators = {"+", "-", "X"};
        for (String operator : operators) {
            if (getMultiple1.contains(operator)) {
                String[] parts = getMultiple1.split(operator);
                double num1 = Double.parseDouble(parts[0]);
                double num2 = Double.parseDouble(parts[1]);
                switch (operator) {
                    case "+":
                        result += num1 + num2;
                        break;
                    case "-":
                        result += num1 - num2;
                        break;
                    case "X":
                        result += num1 * num2;
                        break;
                }
            }
        }
    }
}
