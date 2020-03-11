package Primary;

import javafx.scene.paint.Color;

/**
 * Represents the states of the Confirmation Beacon
 * @author Hector Castillo Martinez
 */
public enum BeaconColor {
    WHITE(Color.WHITE),
    BLACK(Color.BLACK);

    private Color color;
    BeaconColor(Color color){
        this.color = color;
    }
}
