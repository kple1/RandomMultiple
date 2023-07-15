package com.server.randommultiple.Utils;

import com.server.randommultiple.UserData.Datas;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Calculator {

    private Economy economy;
    String title = (Color.chat("&f[ &a랜덤배율 &f] "));

    public void calculator(Player player) {
        // 총 상금 지불
        YamlConfiguration config = Datas.getPlayerConfig(player);
        int getLimit = config.getInt("선택한 배율 개수");
        int getMoney = config.getInt("배팅금액");
        String getMultiple1 = config.getString("당첨된 배율1");
        String getMultiple2 = config.getString("당첨된 배율2");
        String getMultiple3 = config.getString("당첨된 배율3");

        if (!(getLimit == 3)) {
            return;
        }

        double result = 0.0;

        // getMultiple1 계산
        char operator1 = getMultiple1.charAt(0); // 부호
        double value1 = Double.parseDouble(getMultiple1.substring(1)); // 숫자
        switch (operator1) {
            case '+' -> result += value1;
            case '-' -> result -= value1;
            case '*' -> result *= value1;
        }

        // getMultiple2 계산
        char operator2 = getMultiple2.charAt(0); // 부호
        double value2 = Double.parseDouble(getMultiple2.substring(1)); // 숫자
        switch (operator2) {
            case '+' -> result += value2;
            case '-' -> result -= value2;
            case '*' -> result *= value2;
        }

        // getMultiple3 계산
        char operator3 = getMultiple3.charAt(0); // 부호
        double value3 = Double.parseDouble(getMultiple3.substring(1)); // 숫자
        switch (operator3) {
            case '+' -> result += value3;
            case '-' -> result -= value3;
            case '*' -> result *= value3;
        }

        double totalPrize = result * getMoney;

        checkVault(player);
        economy.depositPlayer(player, totalPrize);
        player.sendMessage(title + totalPrize + "원에 당첨되셨습니다!");
    }



    public void checkVault(Player player) {
        // Vault 플러그인을 이용하여 Economy 객체 가져오기
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp != null) {
            economy = rsp.getProvider();
        } else {
            // Vault 플러그인이 설치되어 있지 않거나 Economy 서비스를 제공하지 않는 경우
            player.sendMessage("Vault 플러그인이 필요합니다.");
            return;
        }
    }
}
