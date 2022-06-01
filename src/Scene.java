public class Scene {
    public SceneSpecification sceneSpecification;
    public Event event;

    public Scene(SceneSpecification sceneSpecification, Event event) {
        this.sceneSpecification = sceneSpecification;
        this.event = event;
    }

    public SceneSpecification getSceneSpecification() {
        return sceneSpecification;
    }

    public void setSceneSpecification(SceneSpecification sceneSpecification) {
        this.sceneSpecification = sceneSpecification;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Scene ~ " + sceneSpecification + " " + sceneSpecification.getId() + "#" + event;
    }
}
