package com.Number_Guessing.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GameConfig {
    @Value("${game.min-number}")
    private int minNumber;

    @Value("${game.max-number}")
    private int maxNumber;

    public int getMinNumber() {
        return minNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}