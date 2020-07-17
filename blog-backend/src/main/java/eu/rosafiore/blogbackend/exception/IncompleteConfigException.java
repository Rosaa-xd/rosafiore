package eu.rosafiore.blogbackend.exception;

public class IncompleteConfigException extends RuntimeException {

    public IncompleteConfigException() {
        super(String.format("Config is incomplete. Check if properties file contains all config items."));
    }
}
