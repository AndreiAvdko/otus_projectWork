package data;

public enum Template {
    RUSSIA("Россия");

    private final String name;
    Template(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
