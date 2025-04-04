package db;
import java.util.*;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int lastId = 0;

    private Database(){}

    private static void add(Entity e) {
        e.id = lastId++;
        entities.add(e);
    }

    private static Entity get(int id) {
        for (Entity e : entities) {
            if (e.id == id) {
                return e;
            }
        }
        throw new EntityNotFoundException("Entity with id " + id + " not found!");
    }

    private static void delete(int id) {
        for (Entity e : entities) {
            if (e.id == id) {
                entities.remove(e.id);
                return;
            }
        }
        throw new EntityNotFoundException("Entity with id " + id + " not found!");
    }

    public static void update(Entity e) {
        for (int i = 0 ; i < entities.size() ; i++) {
            if (entities.get(i).id == e.id) {
                entities.set(i, e);
                return;
            }
        }
        throw new EntityNotFoundException("Entity with id " + e.id + " not found!");
    }

}