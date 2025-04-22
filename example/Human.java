package example;
import db.Entity;
import db.exception.InvalidEntityException;

public class Human extends Entity {
    public static String name;
    public static int age;
    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

     @Override
    public Human copy() {
        Human copyHuman = new Human(name , age);
        copyHuman.id = id;
        return copyHuman;
    }
        public static final int HUMAN_ENTITY_CODE = 14;

        @Override
        public int getEntityCode() {
            return HUMAN_ENTITY_CODE;
    }
    @Override
    public void validate(Entity entity) throws InvalidEntityException {
        if (!(entity instanceof Human)) {
            throw new IllegalArgumentException("Input type must be Human");
        }
        if (age < 0) {
            throw new InvalidEntityException("Age can not be negative");
        }
        if (name == null){
            throw new InvalidEntityException("Name can not be empty");
        }
    }
}
