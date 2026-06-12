package istad.co.product_api_simple_demo.advisor;


//exception used when resource already exists
public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String message){
        super(message);
    }


}
