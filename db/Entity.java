package db;

import java.util.Date;

public abstract class Entity implements Validator {
    public static int id;
    Date creationDate;
    Date lastModificationDate;

    public Entity(int id, Date creationDate, Date lastModificationDate){
        this.id = id;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
    }

    //for document constractor
    protected Entity() {
    }

    public abstract Entity copy();
    public abstract int getEntityCode();
}

