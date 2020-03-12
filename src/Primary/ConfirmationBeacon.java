package Primary;

/**
 * @author Hector Castillo Martinez
 */
public enum ConfirmationBeacon {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    private BeaconColor bc;

    ConfirmationBeacon (){
        bc = BeaconColor.BLACK;
    }

    /**
     * Get the BeaconColor of the confirmation beacon.
     * @return
     */
    public BeaconColor getBeaconColor(){
        return bc;
    }

    /**
     * Set the color of the confirmation beacon.
     * @param color BeaconColor to set.
     */
    public void changeColor(BeaconColor color){
        bc = color;
    }

}
