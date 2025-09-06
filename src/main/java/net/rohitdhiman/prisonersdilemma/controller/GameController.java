package net.rohitdhiman.prisonersdilemma.controller;

import net.rohitdhiman.prisonersdilemma.model.Game;
import net.rohitdhiman.prisonersdilemma.model.Move;
import net.rohitdhiman.prisonersdilemma.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public Game startGame() {
        return gameService.createGame();
    }

    @PostMapping("/{gameId}/play")
    public Game playGame(@PathVariable String gameId, @RequestBody MoveRequest moveRequest) {
        return gameService.play(gameId, moveRequest.move());
    }

    @GetMapping("/{gameId}")
    public Game getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }

    public record MoveRequest(Move move) {}
}

