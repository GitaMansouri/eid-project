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
    private Date creationDate = new Date();
    private Date lastModificationDate = new Date();

    private Database(){}

    public static void add(Entity e) throws InvalidEntityException, EntityNotFoundException {
        e.id = ++lastId;
        entities.add(e.copy());
        registerValidator(Human.HUMAN_ENTITY_CODE, new HumanValidator());
        if (validators.containsKey(e)) {
            Validator valadator = validators.get(e);
            valadator.validate(e);
        }
        if (e instanceof Trackable) {
            Trackable trackable = (Trackable) e;
            trackable.setLastModificationDate(new Date());
            trackable.setCreationDate(new Date());
        }
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
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            if (entity.id == id) {
                iterator.remove();
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
                if (validators.containsKey(e)) {
                    Validator valadator = validators.get(e);
                    valadator.validate(e);
                }
                if (e instanceof Trackable) {
                    Trackable trackable = (Trackable) e;
                    trackable.setLastModificationDate(new Date());
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

    public static ArrayList<Entity> getAll(int entityCode) {
        ArrayList<Entity> result = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getEntityCode() == entityCode) {
                Entity copy = entity.copy();
                copy.id = entity.id;
                result.add(copy);
            }
        }
        return result;
    }

    public static void updateEntityList(ArrayList<Entity> updatedEntites){
        entities.clear();
        entities = updatedEntites;
    }
    public static ArrayList<Entity> getEntities() {
        return entities;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public static HashMap<Integer, Validator> getValidators() {
        return validators;
    }

    public static int getLastId() {
        return lastId;
    }

}