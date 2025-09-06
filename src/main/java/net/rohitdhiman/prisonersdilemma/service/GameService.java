package net.rohitdhiman.prisonersdilemma.service;

import net.rohitdhiman.prisonersdilemma.model.Game;
import net.rohitdhiman.prisonersdilemma.model.Move;
import net.rohitdhiman.prisonersdilemma.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame() {
        String id = UUID.randomUUID().toString();
        Game game = new Game(id);
        gameRepository.save(game);
        return game;
    }

    public Game play(String gameId, Move userMove) {
        Game game = gameRepository.findById(gameId);
        if (game == null || game.isGameOver()) {
            return null; // Or throw an exception
        }

        Move computerMove = getComputerMove(game);
        game.getUserMoves().add(userMove);
        game.getComputerMoves().add(computerMove);

        updateScores(game, userMove, computerMove);
        checkGameOver(game);

        game.incrementTurn();
        gameRepository.save(game);
        return game;
    }

    private Move getComputerMove(Game game) {
        int turn = game.getTurn();
        if (turn == 0) {
            return Move.COOPERATE; // Always start by cooperating
        }

        Move userLastMove = game.getUserMoves().get(turn - 1);
        double random = Math.random();

        // Analyze user's overall behavior
        long userDefections = game.getUserMoves().stream().filter(m -> m == Move.DEFECT).count();
        long userCooperates = turn - userDefections;

        // If user is generally cooperative, be mostly cooperative but probe for exploitation
        if (userCooperates > userDefections) {
            if (userLastMove == Move.COOPERATE) {
                // 90% chance to cooperate, 10% to probe with a defection
                return random > 0.1 ? Move.COOPERATE : Move.DEFECT;
            } else { // User defected, retaliate (Tit-for-Tat)
                return Move.DEFECT;
            }
        }
        // If user is generally deceptive or balanced, be less forgiving
        else {
            if (userLastMove == Move.COOPERATE) {
                // User is trying to win back trust. 25% chance to forgive.
                return random > 0.75 ? Move.COOPERATE : Move.DEFECT;
            } else { // User defected again, retaliate strongly.
                // 95% chance to defect.
                return random > 0.05 ? Move.DEFECT : Move.COOPERATE;
            }
        }
    }

    private void updateScores(Game game, Move userMove, Move computerMove) {
        if (userMove == Move.COOPERATE && computerMove == Move.COOPERATE) {
            game.setUserScore(game.getUserScore() + 3);
            game.setComputerScore(game.getComputerScore() + 3);
        } else if (userMove == Move.COOPERATE && computerMove == Move.DEFECT) {
            game.setComputerScore(game.getComputerScore() + 5);
        } else if (userMove == Move.DEFECT && computerMove == Move.COOPERATE) {
            game.setUserScore(game.getUserScore() + 5);
        } else { // Both defect
            game.setUserScore(game.getUserScore() + 1);
            game.setComputerScore(game.getComputerScore() + 1);
        }
    }

    private void checkGameOver(Game game) {
        if (game.getUserScore() >= 20 || game.getComputerScore() >= 20 || game.getTurn() >= 9) {
            game.setGameOver(true);
            if (game.getUserScore() > game.getComputerScore()) {
                game.setWinner("User");
            } else if (game.getComputerScore() > game.getUserScore()) {
                game.setWinner("CLS-7");
            } else {
                game.setWinner("Draw");
            }
        }
    }

    public Game getGame(String gameId) {
        return gameRepository.findById(gameId);
    }
}

