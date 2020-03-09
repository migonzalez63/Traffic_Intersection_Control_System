package Primary;
import static Primary.Lanes.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Determines all possible phases that can occur within our TICS. Each phase has a list of lanes that either have their lights
 * green, yellow, or red, the timing that the phase will operate by, and if pedestrian crossing in a certain direction
 * is possible.
 */
public enum Phases {

    NS_LEFT_GREEN(new LinkedList<>(Arrays.asList(N1, S1)), null, new LinkedList<>(Arrays.asList(N2, N3, E1, E2, E3, W1, W2, W3, S2, S3)), 4000, false,  false),
    NS_LEFT_YELLOW (null, new LinkedList<>(Arrays.asList(N1, S1)), new LinkedList<>(Arrays.asList(N2, N3, E1, E2, E3, W1, W2, W3, S2, S3)), 3000, false, false),
    EW_LEFT_GREEN(new LinkedList<>(Arrays.asList(E1, W1)), null, new LinkedList<>(Arrays.asList(N1, N2, N3, E2, E3, W2, W3, S1, S2, S3)), 4000, false, false),
    EW_LEFT_YELLOW(null, new LinkedList<>(Arrays.asList(E1, W1)), new LinkedList<>(Arrays.asList(N1, N2, N3, E2, E3, W2, W3, S1, S2, S3)), 3000, false, false),
    NS_GREEN(new LinkedList<>(Arrays.asList(N2, N3, S2, S3)),null, new LinkedList<>(Arrays.asList(N1, S1, E1, E2, E3, W1, W2, W3)),6000, true, false),
    NS_YELLOW(null, new LinkedList<>(Arrays.asList(N2, N3, S2, S3)), new LinkedList<>(Arrays.asList(N1, S1, E1, E2, E3, W1, W2, W3)),3000, true, false),
    EW_GREEN(new LinkedList<>(Arrays.asList(E2, E3, W2, W3)), null, new LinkedList<>(Arrays.asList(N1, N2, N3, S1, S2, S3, E1, W1)), 6000, false, true),
    EW_YELLOW(null, new LinkedList<>(Arrays.asList(E2, E3, W2, W3)), new LinkedList<>(Arrays.asList(N1, N2, N3, S1, S2, S3, E1, W1)),3000, false, true),
    ALL_RED1(null, null, new LinkedList<>(Arrays.asList(N1, N2, N3, E1, E2, E3, W1, W2, W3, S1, S2, S3)), 3000, false, false),
    ALL_RED2(null, null, new LinkedList<>(Arrays.asList(N1, N2, N3, E1, E2, E3, W1, W2, W3, S1, S2, S3)), 3000, false, false),
    ALL_RED3(null, null, new LinkedList<>(Arrays.asList(N1, N2, N3, E1, E2, E3, W1, W2, W3, S1, S2, S3)), 3000, false, false),
    ALL_RED4(null, null, new LinkedList<>(Arrays.asList(N1, N2, N3, E1, E2, E3, W1, W2, W3, S1, S2, S3)), 3000, false, false),
    FOURWAY_N_GREEN(new LinkedList<>(Arrays.asList(N1, N2, N3)), null, new LinkedList<>(Arrays.asList(E1, E2, E3, S1, S2, S3, W1, W2, W3)), 1000, false, false),
    FOURWAY_E_GREEN(new LinkedList<>(Arrays.asList(E1, E2, E3)), null, new LinkedList<>(Arrays.asList(N1, N2, N3, S1, S2, S3, W1, W2, W3)), 1000, false, false),
    FOURWAY_S_GREEN(new LinkedList<>(Arrays.asList(S1, S2, S3)), null, new LinkedList<>(Arrays.asList(E1, E2, E3, N1, N2, N3, W1, W2, W3)), 1000, false, false),
    FOURWAY_W_GREEN(new LinkedList<>(Arrays.asList(W1, W2, W3)), null, new LinkedList<>(Arrays.asList(E1, E2, E3, S1, S2, S3, N1, N2, N3)), 1000, false, false);
    private int phaseTime;
    private LinkedList<Lanes> redLanes;
    private LinkedList<Lanes> greenLanes;
    private LinkedList<Lanes> yellowLanes;
    private boolean nspedestrians;
    private boolean ewpedestrians;

    Phases(LinkedList<Lanes> greenLanes, LinkedList<Lanes> yellowLanes, LinkedList<Lanes> redLanes, int phaseTime, boolean nsPedestrians, boolean ewPedestrians) {
        this.phaseTime = phaseTime;
        this.redLanes = redLanes;
        this.greenLanes = greenLanes;
        this.yellowLanes = yellowLanes;
        this.nspedestrians=nsPedestrians;
        this.ewpedestrians=ewPedestrians;
    }

    public void setPhaseTime(int newTime){
        this.phaseTime=newTime;
    }

    public int getPhaseTime() {
        return phaseTime;
    }

    public LinkedList<Lanes> getRedLanes() {
        return redLanes;
    }

    public LinkedList<Lanes> getGreenLanes() {
        return greenLanes;
    }

    public LinkedList<Lanes> getYellowLanes() {
        return yellowLanes;
    }
}

