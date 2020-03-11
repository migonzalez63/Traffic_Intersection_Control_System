package Primary;

public enum Lights {
    NORTH (0, false, false),
    EAST (1, false, false),
    SOUTH (2, false, false),
    WEST (3, false, false);

    private final int number;
    private boolean pedestrianAt;
    private Boolean isGreen;
    private boolean rung;

    Lights(int i, boolean pedestrianAt, Boolean isGreen) {
        this.number = i;
        this.pedestrianAt = pedestrianAt;
        this.isGreen = isGreen;
        rung = false;
    }

    public void setPedestrianAt(boolean pedestrianAt){
        this.pedestrianAt = pedestrianAt;
    }

    public boolean isRung() {
        return rung;
    }

    public void setRung(boolean rung) {
        this.rung = rung;
    }

    public void setColor(SignalColor c){
        isGreen = c == SignalColor.GREEN;
    }

    public Boolean getGreen(){
        return isGreen;
    }

    public boolean isPedestrianAt() {
        return pedestrianAt;
    }
}
