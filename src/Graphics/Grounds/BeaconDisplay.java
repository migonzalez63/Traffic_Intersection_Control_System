package Graphics.Grounds;

import Graphics.Direction;
import Graphics.Grounds.Ground;
import Primary.BeaconColor;
import Primary.ConfirmationBeacon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * @author Hector Castillo Martinez
 */
public class BeaconDisplay extends Ground{
    private GraphicsContext gc;
    private double x;
    private Direction side;
    private double y;

    private ConfirmationBeacon confirmationBeacon; //ref to comp controlled
    // by TC


    public BeaconDisplay(GraphicsContext gc, Direction side){
        this.side = side;
        this.gc = gc;
        setBeacons();
    }


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
            gc.fillRect(100, 100 + laneLength,
                    laneWidth, 4);
        } else if (side == Direction.SOUTH){
            gc.fillRect(100, 70, laneWidth, 4);
        } else if (side == Direction.EAST){
            gc.fillRect(75, 50, 4, laneWidth);
        } else if (side == Direction.WEST){
            gc.fillRect(75 + laneLength, 50, 4,
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
