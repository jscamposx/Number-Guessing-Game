package com.Number_Guessing.cli;

import com.Number_Guessing.config.GameConfig;
import com.Number_Guessing.model.GameState;
import com.Number_Guessing.model.GameState.Difficulty;
import com.Number_Guessing.model.GuessResult;
import com.Number_Guessing.service.GameService;
import com.Number_Guessing.utils.HintGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Random;

@ShellComponent
public class GameCLI {

    private final Random random = new Random();
    private final GameConfig gameConfig;
    private final HintGenerator hintGenerator;

    private GameState gameState;
    private GameService gameService;

    public GameCLI(GameConfig gameConfig, HintGenerator hintGenerator) {
        this.gameConfig = gameConfig;
        this.hintGenerator = hintGenerator;
    }

    @PostConstruct
    public void init() {
        System.out.println(welcome());
    }

    @ShellMethod("Bienvenida y reglas del juego")
    public String welcome() {
        int min = gameConfig.getMinNumber();
        int max = gameConfig.getMaxNumber();
        return String.format("""
            🎉 ¡Bienvenido a Guess Gaming!
            Este es un juego donde debes adivinar un número entre %d y %d.
            Según la dificultad que elijas, tendrás un número limitado de intentos para adivinar el número correcto.
            
            Para comenzar, usa el comando 'start <dificultad>' con una de las siguientes opciones:
            - easy: 10 intentos
            - medium: 5 intentos
            - hard: 3 intentos
            
            Una vez que el juego haya comenzado, usa el comando 'guess <número>' para intentar adivinar el número.
            Si necesitas ayuda, puedes escribir 'hint' para recibir una pista.
            ¡Buena suerte!
            """, min, max);
    }

    @ShellMethod("Iniciar un nuevo juego")
    public String start(@ShellOption(help = "Dificultad: easy, medium, hard") String difficulty) {
        Difficulty diff = switch (difficulty.toLowerCase()) {
            case "easy" -> Difficulty.EASY;
            case "medium" -> Difficulty.MEDIUM;
            case "hard" -> Difficulty.HARD;
            default -> null;
        };

        if (diff == null) {
            return "❌ Dificultad no válida. Usa: easy, medium o hard.";
        }

        int min = gameConfig.getMinNumber();
        int max = gameConfig.getMaxNumber();
        int secretNumber = random.nextInt(max - min + 1) + min;

        gameState = new GameState(secretNumber, diff);
        gameService = new GameService(gameState);

        return String.format("""
            🎉 Nuevo juego iniciado. 
            Estoy pensando en un número entre %d y %d.
            Dificultad: %s (%d intentos).
            Usa el comando 'guess <número>' para intentar.
            Usa 'hint' si quieres una pista.
            """, min, max, diff.name(), diff.getAttempts());
    }

    @ShellMethod("Hacer un intento para adivinar el número")
    public String guess(@ShellOption int number) {
        if (gameService == null) return "❗ Primero inicia un juego con el comando 'start'.";
        if (gameService.isGameOver()) return "⚠️ El juego ha terminado. Usa 'start' para iniciar otro.";

        GuessResult result = gameService.processGuess(number);

        if (gameService.isGameOver()) {
            return result.getResultType() == GuessResult.ResultType.CORRECT
                    ? "🎉 ¡Has adivinado el número en " + result.getAttemptsUsed() + " intentos!"
                    : "❌ ¡Se acabaron los intentos! El número era: " + gameState.getSecretNumber();
        }

        return switch (result.getResultType()) {
            case CORRECT -> "🎉 ¡Correcto! Adivinaste el número en " + result.getAttemptsUsed() + " intentos.";
            case TOO_LOW -> "🔼 El número es mayor que " + result.getGuess() + ".";
            case TOO_HIGH -> "🔽 El número es menor que " + result.getGuess() + ".";
            case GAME_OVER -> "❌ Te quedaste sin intentos. El número era " + gameState.getSecretNumber() + ".";
        };
    }

    @ShellMethod("Pedir una pista")
    public String hint() {
        if (gameState == null || gameState.getStatus() != GameState.Status.IN_PROGRESS) {
            return "❗ No hay juego activo. Usa 'start' para comenzar.";
        }
        return hintGenerator.generateHint(gameState);
    }

    @ShellMethod("Ver el estado actual del juego")
    public String status() {
        if (gameState == null) return "❗ No hay juego activo.";
        return String.format("Intentos usados: %d / %d",
                gameState.getAttemptsUsed(), gameState.getDifficulty().getAttempts());
    }
}
