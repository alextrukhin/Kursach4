package nau.coursework4.server;

public class FlowersStoreSingleton {
    private static FlowersStore instance;

    private FlowersStoreSingleton() {
    }

    public static FlowersStore getInstance() {
        if (instance == null) {
            instance = new FlowersStore();
        }
        return instance;
    }
}
