package db;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import example.Human;
import example.HumanValidator;

import java.util.*;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int lastId = 0;
    private static HashMap<Integer, Validator> validators = new HashMap<>();

    private Database(){}

    public static void add(Entity e) throws InvalidEntityException {
        e.id = ++lastId;
        validate(e);

        if (e instanceof Trackable) {
            Date cd = new Date();

            ((Trackable) e).setCreationDate(cd);
            ((Trackable) e).setLastModificationDate(cd);
        }

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

    public static void update(Entity e) throws EntityNotFoundException, InvalidEntityException {
        for (int i = 0 ; i < entities.size() ; i++) {
            if (entities.get(i).id == e.id) {
                entities.set(i, e.copy());
                registerValidator(Human.HUMAN_ENTITY_CODE, new HumanValidator());
                if (validators.containsKey(e)){
                    Validator valadator = validators.get(e);
                    valadator.validate(e);
                }
                return;
            }
        }
        throw new EntityNotFoundException("Entity with id " + e.id + " not found!");
    }
    public static void registerValidator(int entityCode, Validator validat) {
        if (validators.containsKey(entityCode)) {
            throw new IllegalArgumentException("Validator with entityCode '" + entityCode + "' has existed");
        }
        validators.put(entityCode, validat);
    }
    private static void validate(Entity e) throws InvalidEntityException {
        if (!validators.containsKey(e.getEntityCode())){
            return;
        }

        Validator validator = validators.get(e.getEntityCode());
        validator.validate(e);
    }

}