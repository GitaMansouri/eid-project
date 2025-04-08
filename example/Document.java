package example;

import db.Entity;
import db.Trackable;
import db.exception.InvalidEntityException;

import java.util.Date;

public class Document extends Entity implements Trackable {
    public static String content;
    public Document(String content){
        this.content = content;
    }


    @Override
    public Entity copy() {
        return null;
    }

    @Override
    public int getEntityCode() {
        return 0;
    }

    @Override
    public void setCreationDate(Date date) {

    }

    @Override
    public Date getCreationDate() {
        return null;
    }

    @Override
    public void setLastModificationDate(Date date) {

    }

    @Override
    public Date getLastModificationDate() {
        return null;
    }

    @Override
    public void validate(Entity entity) throws InvalidEntityException {

    }
}
