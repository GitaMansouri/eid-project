package db;
import db.exception.EntityNotFoundException;

import java.util.*;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int lastId = 0;

    private Database(){}

    public static void add(Entity e) {
        e.id = ++lastId;
        entities.add(e.copy());
    }

    public static Entity get(int id) throws EntityNotFoundException {
        for (Entity e : entities) {
            if (e.id == id) {
                return e.copy();
            }
        }
        throw new EntityNotFoundException("Entity with id " + id + " not found!");
    }

    public static void delete(int id) throws EntityNotFoundException {
        for (Entity e : entities) {
            if (e.id == id) {
                entities.remove(e.id);
                return;
            }
        }
        throw new EntityNotFoundException("Entity with id " + id + " not found!");
    }

    public static void update(Entity e) throws EntityNotFoundException {
        for (int i = 0 ; i < entities.size() ; i++) {
            if (entities.get(i).id == e.id) {
                entities.set(i, e.copy());
                return;
            }
        }
        throw new EntityNotFoundException("Entity with id " + e.id + " not found!");
    }

}