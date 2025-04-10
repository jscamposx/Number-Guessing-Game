package com.Number_Guessing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuessResult {
    public enum ResultType {
        CORRECT, TOO_LOW, TOO_HIGH, GAME_OVER
    }

    private final ResultType resultType;
    private final int guess;
    private final int attemptsUsed;
}