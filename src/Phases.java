import Primary.Lanes;

import java.util.Arrays;
import java.util.LinkedList;

import static Primary.Lanes.*;

public enum Phases {
    NS_LEFT_GREEN(),
    NS_LEFT_YELLOW(),
    NS_LEFT_RED(),
    // only E1 and W1 green
    EW_LEFT_GREEN(
        new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, E2, E3, W2, W3, S1, S2,S3)),
        new LinkedList<Lanes>(Arrays.asList(E1, W1)), 
        null,
        3,
        false,
        false),
    EW_LEFT_YELLOW(
        new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, E2, E3, W2, W3, S1, S2, S3)), 
        null,
        new LinkedList<Lanes>(Arrays.asList(E1, W1)),
        3,
        false,
        false),
    NS_GREEN(),
    EW_GREEN(),
    ALL_RED(new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, E1, E2, E3, W1, W2, W3, S1, S2, S3)), null, 3, false, false);

    private int phaseTime;
    LinkedList<Lanes> redLanes = new LinkedList<Lanes>();
    LinkedList<Lanes> greenLanes = new LinkedList<Lanes>();
    LinkedList<Lanes> yellowLanes = new LinkedList<Lanes>();

    Phases (LinkedList<Lanes> redLanes,LinkedList<Lanes> greenLanes, LinkedList<Lanes> yellowLanes, int phaseTime, boolean nsPedestrians, boolean ewPedestrians) {
        this.phaseTime = phaseTime;
        this.redLanes = redLanes;
        this.greenLanes = greenLanes;
        this.yellowLanes = yellowLanes;
    }
}
