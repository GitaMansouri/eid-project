package example;
import db.Entity;
import db.Validator;
import db.exception.InvalidEntityException;

import static example.Human.*;

public class HumanValidator implements Validator {
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (!(entity instanceof Human)) {
            throw new IllegalArgumentException("Input type must be Human");
        }
       if (age < 0 || name == null) {
           throw new InvalidEntityException("Age can not be negative");
       }
       if (name == null){
           throw new InvalidEntityException("Name can not be empty");
       }
    }
}