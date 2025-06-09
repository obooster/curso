package me.wellington.curso.jdbc.enums;

public enum Field {
    NAME("name"),
    AGE("idade");

    public final String object;

    Field(String object) {
        this.object = object;
    }

}
