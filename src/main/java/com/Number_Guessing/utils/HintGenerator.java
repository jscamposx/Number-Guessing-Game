package com.Number_Guessing.utils;

import com.Number_Guessing.config.GameConfig;
import com.Number_Guessing.model.GameState;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.List;

import java.util.function.Function;

@Component
public class HintGenerator {

    private final Random random = new Random();
    private final GameConfig gameConfig;
    private static final int RANGE_HINT_MARGIN = 10;
    private static final int DIVISIBILITY_HINT_FIVE = 5;
    private static final int DIVISIBILITY_HINT_THREE = 3;
    private static final int DIVISIBILITY_HINT_TWO = 2;

    private final List<Function<Integer, String>> hintStrategies;

    public HintGenerator(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.hintStrategies = List.of(
                this::rangeHint,
                this::parityHint,
                this::divisibilityHint
        );
    }

    public String generateHint(GameState gameState) {
        int secret = gameState.getSecretNumber();
        Function<Integer, String> hintFunction = hintStrategies.get(random.nextInt(hintStrategies.size()));
        return hintFunction.apply(secret);
    }

    private String rangeHint(int number) {
        int min = gameConfig.getMinNumber();
        int max = gameConfig.getMaxNumber();
        int lower = Math.max(min, number - RANGE_HINT_MARGIN);
        int upper = Math.min(max, number + RANGE_HINT_MARGIN);
        return "🔍 El número está entre " + lower + " y " + upper + ".";
    }

    private String parityHint(int number) {
        return "🔢 El número es " + (number % 2 == 0 ? "par." : "impar.");
    }

    private String divisibilityHint(int number) {
        if (number % DIVISIBILITY_HINT_FIVE == 0) return "➗ El número es divisible por 5.";
        if (number % DIVISIBILITY_HINT_THREE == 0) return "➗ El número es divisible por 3.";
        if (number % DIVISIBILITY_HINT_TWO == 0) return "➗ El número es divisible por 2.";
        return "🔎 El número no es divisible por 2, 3 ni 5.";
    }
}