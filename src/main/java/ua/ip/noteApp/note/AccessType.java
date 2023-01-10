package ua.ip.noteApp.note;

public enum AccessType {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");

    final String name;


    AccessType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}