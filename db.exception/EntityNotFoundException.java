package db.exception;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException() {
        System.out.println("Cannot find entity");
    }

    public EntityNotFoundException(String message) {
        System.out.println(message);
    }

    public EntityNotFoundException(int id) {
        System.out.println("Cannot find entity with id = {" + id + "}.");
    }
}
