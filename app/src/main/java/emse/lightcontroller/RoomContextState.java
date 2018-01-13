package emse.lightcontroller;

/**
 * Created by User on 27-12-2017.
 */

public class RoomContextState {

    private String room;
    private String status;
    private int light;
    private int noise;

    public RoomContextState(String room, String status, int light, int noise) {
        super();
        this.room = room;
        this.status = status;
        this.light = light;
        this.noise = noise;
    }

    public String getRoom() {
        return this.room;
    }

    public String getStatus() {
        return this.status;
    }

    public int getLight() {
        return this.light;
    }

    public int getNoise() {
        return this.noise;
    }
}
