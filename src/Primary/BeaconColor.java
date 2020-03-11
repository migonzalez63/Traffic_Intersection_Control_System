package Primary;

import javafx.scene.paint.Color;

/**
 * @author Hector Castillo Martinez
 */
public enum BeaconColor {
    WHITE(Color.WHITE),
    BLUE(Color.BLUE),
    BLACK(Color.BLACK);

    private Color color;
    BeaconColor(Color color){
        this.color = color;
    }
}
