package net.rohitdhiman.prisonersdilemma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private int userScore = 0;
    private int computerScore = 0;
    private int turn = 0;
    private List<Move> userMoves = new ArrayList<>();
    private List<Move> computerMoves = new ArrayList<>();
    private boolean gameOver = false;
    private String winner;

    public Game(String id) {
        this.id = id;
    }

    public Game() {
        // No-arg constructor for Jackson deserialization
    }

    public String getId() {
        return id;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void setComputerScore(int computerScore) {
        this.computerScore = computerScore;
    }

    public int getTurn() {
        return turn;
    }

    public void incrementTurn() {
        this.turn++;
    }

    public List<Move> getUserMoves() {
        return userMoves;
    }

    public List<Move> getComputerMoves() {
        return computerMoves;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}

