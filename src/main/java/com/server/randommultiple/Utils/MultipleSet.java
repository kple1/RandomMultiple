package com.server.randommultiple.Utils;

import com.server.randommultiple.Main;
import com.server.randommultiple.UserData.Datas;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class MultipleSet {

    public void multipleSet(Player player) {
        YamlConfiguration config = Datas.getPlayerConfig(player);
        Random random = new Random();

        List<Double> probabilities = Arrays.asList(
                0.6, 0.5, 0.5, 0.5, 0.25, 0.15, 0.2, 0.3, 0.05, 0.7
        );

        List<String> values = Arrays.asList(
                "X0", "X1", "+1", "-1", "X2", "X3", "+2", "-3", "X10", "X50"
        );

        List<String> selectedValues = new ArrayList<>();

        // 확률에 따라 값을 선택
        for (int i = 0; i < 45; i++) {
            int index = getRandomIndexWithProbabilities(random, probabilities);
            String selectedValue = values.get(index);
            selectedValues.add(selectedValue);
        }

        for (int i = 0; i < selectedValues.size(); i++) {
            config.set("배수." + i, selectedValues.get(i));
        }
        Main.getPlugin().saveYamlConfiguration();
    }

    private int getRandomIndexWithProbabilities(Random random, List<Double> probabilities) {
        double totalProbability = 0;
        for (double probability : probabilities) {
            totalProbability += probability;
        }

        double randomNumber = random.nextDouble() * totalProbability;
        double cumulativeProbability = 0;

        for (int i = 0; i < probabilities.size(); i++) {
            cumulativeProbability += probabilities.get(i);
            if (randomNumber < cumulativeProbability) {
                return i;
            }
        }

        return probabilities.size() - 1;
    }
}
