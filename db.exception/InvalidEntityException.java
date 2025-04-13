package db.exception;

public class InvalidEntityException extends Exception{
   public InvalidEntityException(String message) {
       System.out.println(message);
   }
}
