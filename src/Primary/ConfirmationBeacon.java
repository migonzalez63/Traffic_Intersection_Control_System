package Primary;

/**
 * @author Hector Castillo Martinez
 */
public enum ConfirmationBeacon {
    NORTH (false, false, 0),
    EAST (false, false, 0),
    SOUTH (false, false, 0),
    WEST (false, false, 0);

    private boolean inPath;
    private boolean isIlluminated;
    private int mode;
    private BeaconColor bc;

    ConfirmationBeacon (boolean inPath, boolean isIlluminated,
                        int mode){
        this.inPath = inPath;
        this.isIlluminated = isIlluminated;
        this.mode = mode;
        bc = BeaconColor.BLACK;
    }

    public boolean isIlluminated() {
        return isIlluminated;
    }

    public boolean isInPath() {
        return inPath;
    }

    public int getMode() {
        return mode;
    }

    public BeaconColor getBeaconColor(){
        return bc;
    }

    public void changeColor(BeaconColor color){
        bc = color;
    }

    public void setInPath(boolean inPath) {
        this.inPath = inPath;
    }

    public void setIlluminated(boolean illuminated) {
        isIlluminated = illuminated;
    }

    public void setMode() {
        if(!isIlluminated){
            // black
            mode = 0;
        }else if(inPath){
            // solid white
            mode = 2;
        }else{
            // alternate between white and black
            mode = 1;
        }
    }
}
