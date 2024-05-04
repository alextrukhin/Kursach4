package nau.coursework4.server;

public class BunchesStoreSingleton {
    private static BunchesStore instance;

    private BunchesStoreSingleton() {
    }

    public static BunchesStore getInstance() {
        if (instance == null) {
            instance = new BunchesStore();
        }
        return instance;
    }
}
