package ir.kit.github.phonebook.exception;

public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super("Your input is not valid!!");
    }
}
