package home.edu.jaxrs.jersey.calculator;


public enum Operation {

    PLUS("PLUS"),
    MINUS("MINUS"),
    MULTIPLY("MULTIPLY"),
    DIVIDE("DIVIDE");

    Operation(String name) {
        this.name = name;
    }

    public static Operation fromValue(String value) {
        return Operation.valueOf(value);
    }

    String name;
}