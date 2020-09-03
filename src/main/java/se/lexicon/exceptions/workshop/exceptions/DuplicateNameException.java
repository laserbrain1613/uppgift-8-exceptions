package se.lexicon.exceptions.workshop.exceptions;

public class DuplicateNameException extends Exception {

    private String msg;

    public DuplicateNameException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
