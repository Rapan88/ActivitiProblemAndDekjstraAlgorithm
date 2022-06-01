public enum SceneSpecification {
    YELLOW(1), PINK(2), ORANGE(3), BLACK(4), GREEN(5), PURPLE(6);

    private int id;

    SceneSpecification(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
