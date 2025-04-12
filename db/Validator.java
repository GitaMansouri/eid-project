package db;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;

public interface Validator {
    void validate(Entity entity) throws InvalidEntityException, EntityNotFoundException;
}
