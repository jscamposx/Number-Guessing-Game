package com.Number_Guessing.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GameState {

    public enum Difficulty {
        EASY(10), MEDIUM(5), HARD(3);
        private final int attempts;

        Difficulty(int attempts) {
            this.attempts = attempts;
        }

        public int getAttempts() {
            return attempts;
        }
    }

    public enum Status {
        IN_PROGRESS, WON, LOST
    }

    public void incrementAttempts() {
        this.attemptsUsed++;
    }

    private final int secretNumber;
    private final Difficulty difficulty;
    private final int maxAttempts;
    private int attemptsUsed = 0;
    private Status status = Status.IN_PROGRESS;


    public GameState(int secretNumber, Difficulty difficulty) {
        this.secretNumber = secretNumber;
        this.difficulty = difficulty;
        this.maxAttempts = difficulty.getAttempts();
    }

    public boolean hasAttemptsLeft() {
        return attemptsUsed < maxAttempts;
    }
}