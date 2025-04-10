package com.Number_Guessing.service;

import com.Number_Guessing.model.GameState;
import com.Number_Guessing.model.GuessResult;
import com.Number_Guessing.model.GuessResult.ResultType;

public class GameService {

    private final GameState gameState;

    public GameService(GameState gameState) {
        this.gameState = gameState;
    }

    public GuessResult processGuess(int guess) {
        gameState.incrementAttempts();

        if (guess == gameState.getSecretNumber()) {
            gameState.setStatus(GameState.Status.WON);
            return new GuessResult(ResultType.CORRECT, guess, gameState.getAttemptsUsed());
        }

        if (!gameState.hasAttemptsLeft()) {
            gameState.setStatus(GameState.Status.LOST);
            return new GuessResult(ResultType.GAME_OVER, guess, gameState.getAttemptsUsed());
        }

        ResultType type = guess < gameState.getSecretNumber() ? ResultType.TOO_LOW : ResultType.TOO_HIGH;
        return new GuessResult(type, guess, gameState.getAttemptsUsed());
    }

    public boolean isGameOver() {
        return gameState.getStatus() != GameState.Status.IN_PROGRESS;
    }


}
