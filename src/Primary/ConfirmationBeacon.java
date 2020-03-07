package Primary;

/**
 * @author Hector Castillo Martinez
 */
public enum ConfirmationBeacon {
    NORTH (0, false, false, 0),
    EAST (1, false, false, 0),
    SOUTH (2, false, false, 0),
    WEST (3, false, false, 0);

    private final int ID;
    private boolean inPath;
    private boolean isIlluminated;
    private int mode;

    ConfirmationBeacon (int ID, boolean inPath, boolean isIlluminated,
                        int mode){
        this.ID = ID;
        this.inPath = inPath;
        this.isIlluminated = isIlluminated;
        this.mode = mode;
    }

    public boolean isIlluminated() {
        return isIlluminated;
    }

    public boolean isInPath() {
        return inPath;
    }

    public int getID() {
        return ID;
    }

    public int getMode() {
        return mode;
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
