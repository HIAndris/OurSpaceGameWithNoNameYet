package bif.hia.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public abstract class Entity {
    protected int id;

    protected Vector3 position;
    protected float orientation;
    protected Vector3 velocity;

    protected int health;
    protected int maxHealth;
    protected float speed;

    protected BoundingBox hitBox;

    public Entity(int id, int health, int maxHealth, float speed) {
        this.id = id;

        position = new Vector3();
        orientation = 0f;
        velocity = new Vector3();

        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;

        hitBox = new BoundingBox();
    }

    public abstract void update(float delta);

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 newPosition) {
        this.position.set(newPosition);
    }

    public void setPosition(float x, float y, float z) {
        this.position.set(x, y, z);
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3 newVelocity) {
        this.velocity.set(newVelocity);
    }

    public void setVelocity(float x, float y, float z) {
        this.velocity.set(x, y, z);
    }

    public float getOrientation() {
        return orientation;
    }

    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public BoundingBox getHitBox() {
        return hitBox;
    }
}
