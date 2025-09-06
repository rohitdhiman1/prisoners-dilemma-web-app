package net.rohitdhiman.prisonersdilemma.repository;

import net.rohitdhiman.prisonersdilemma.model.Game;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class GameRepository {

    private final Map<String, Game> gameStore = new ConcurrentHashMap<>();

    public void save(Game game) {
        gameStore.put(game.getId(), game);
    }

    public Game findById(String id) {
        return gameStore.get(id);
    }
}
