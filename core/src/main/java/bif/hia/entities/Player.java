package bif.hia.entities;

import bif.hia.configs.EntityConfig;

public class Player extends Entity {
    private State state;

    public Player(int entityId) {
        super(
            entityId,
            EntityConfig.PLAYER_HEALTH,
            EntityConfig.PLAYER_MAX_HEALTH,
            EntityConfig.PLAYER_SPEED
        );
        state = EntityConfig.PLAYER_STATE;
    }

    @Override
    public void update(float delta) {

    }

    public enum State {
        IDLE,
        WALKING,
        DEAD
    }
}
