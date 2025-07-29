package lk.ijse.gdse.wanderlust.exception;
//custom exception
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}