package Graphics.Grounds;

import Graphics.Direction;
import Primary.BeaconColor;
import Primary.ConfirmationBeacon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * @author Hector Castillo Martinez
 */
public class BeaconDisplay extends Ground{
    private GraphicsContext gc;
    private Direction side;

    //ref to comp controlled by the TC
    private ConfirmationBeacon confirmationBeacon;


    public BeaconDisplay(GraphicsContext gc, Direction side){
        this.side = side;
        this.gc = gc;
        setBeacons();
    }

    /**
     * Draws the Beacon and is used in Simulation to update the GUI.
     */
    public void drawBeacon(){
        Paint color = Paint.valueOf("#000000");
        BeaconColor bc = confirmationBeacon.getBeaconColor();
        double laneWidth = (double)100/7; // denom is number of lanes in a
        // single road
        double laneLength = (this.gc.getCanvas().getWidth() - 100) / 2;

        if(bc.equals(BeaconColor.WHITE)){
            color = Paint.valueOf("#ffffff");
        } else if(bc.equals(BeaconColor.BLACK)){
            color = Paint.valueOf("#000000");
        } else if(bc.equals(BeaconColor.BLUE)){
            color = Paint.valueOf("#0324fc");
        }

        gc.setFill(color);

        if (side == Direction.NORTH ) {
            gc.fillRect(250, 100 + laneLength,
                    laneWidth, 4);
        } else if (side == Direction.SOUTH){
            gc.fillRect(285, 225, laneWidth, 4);
        } else if (side == Direction.EAST){
            gc.fillRect(225, 250, 4, laneWidth);
        } else if (side == Direction.WEST){
            gc.fillRect(100 + laneLength, 285, 4,
                    laneWidth);
        }

    }

    /**
     * Set the graphical components to be referenced to the Enum used by the
     * controller.
     */
    private void setBeacons(){
        if (side == Direction.NORTH ) {
            confirmationBeacon = ConfirmationBeacon.NORTH;
        } else if (side == Direction.SOUTH){
            confirmationBeacon = ConfirmationBeacon.SOUTH;
        } else if (side == Direction.EAST){
            confirmationBeacon = ConfirmationBeacon.EAST;
        } else if (side == Direction.WEST){
            confirmationBeacon = ConfirmationBeacon.WEST;
        }
    }

    /**
     * Gets the x of the GC
     * @return double
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y of the GC
     * @return double
     */
    public double getY() {
        return y;
    }
}
