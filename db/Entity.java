package db;

public abstract class Entity implements Validator {
    public static int id;

    public abstract Entity copy();
    public abstract int getEntityCode();
}

