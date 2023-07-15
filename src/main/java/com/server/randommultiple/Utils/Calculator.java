package com.server.randommultiple.Utils;

import com.server.randommultiple.UserData.Datas;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Calculator {

    private Economy economy;

    public void calculator(Player player) {
        // 총 상금 지불
        YamlConfiguration config = Datas.getPlayerConfig(player);
        int getLimit = config.getInt("선택한 배율 개수");
        int getMoney = config.getInt("배팅금액");
        String getMultiple1 = config.getString("당첨된 배율.1");
        String getMultiple2 = config.getString("당첨된 배율.2");
        String getMultiple3 = config.getString("당첨된 배율.3");

        double totalPrize = getMoney; // 초기 상금은 배팅금액으로 설정

        // 배율에 따라 계산
        if (getLimit >= 1) {
            totalPrize += calculate(getMultiple1, getMoney);
        }
        if (getLimit >= 2) {
            totalPrize += calculate(getMultiple2, getMoney);
        }
        if (getLimit >= 3) {
            totalPrize += calculate(getMultiple3, getMoney);
        }


        // 상금 지불 처리
        economy.depositPlayer(player, totalPrize);
    }

    private double calculate(String multiple, double getMoney) {
        double prize = 0;

        // 숫자, 연산자 분리
        String[] parts = multiple.split(" ");
        double number = Double.parseDouble(parts[0]);
        char operator = parts[1].charAt(0);

        // 연산자에 따라 계산
        switch (operator) {
            case '+' -> prize += number;
            case '-' -> prize -= number;
            case '*' -> prize *= number;
        }

        // 배팅금액과 함께 계산
        prize *= getMoney;

        return prize;
    }
}
