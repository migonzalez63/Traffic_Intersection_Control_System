import Primary.Lanes;

import java.util.Arrays;
import java.util.LinkedList;

import static Primary.Lanes.*;

public enum Phases {

    NS_LEFT_GREEN(new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, E1, E2, E3, W1, W2, W3, S1, S2, S3)), null, 3, false, false),
    NS_LEFT_YELLOW (new LinkedList<Lanes>(Arrays.asList(N1, S1)), new LinkedList<Lanes>(Arrays.asList(N2, N3, E1, E2, E3, W1, W2, W3,  S2, S3)), 3, false, false),
    NS_LEFT_RED(new LinkedList<Lanes>(Arrays.asList(N1, S1)), new LinkedList<Lanes>(Arrays.asList(N2, N3, E1, E2, E3, W1, W2, W3,  S2, S3)), 3, false, false);
    EW_LEFT_GREEN(),
    EW_LEFT_YELLOW(),
    EW_LEFT_RED(),
    NS_GREEN(),
    EW_GREEN(),
    ALL_RED(new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, E1, E2, E3, W1, W2, W3, S1, S2, S3)), null, 3, false, false);


    private int phaseTime;
    LinkedList<Lanes> redLanes = new LinkedList<Lanes>();
    LinkedList<Lanes> greenLanes = new LinkedList<Lanes>();

    Phases (LinkedList<Lanes> redLanes,LinkedList<Lanes> greenLanes, int phaseTime, boolean nsPedestrians, boolean ewPedestrians) {
        this.phaseTime = phaseTime;
        this.redLanes = redLanes;
        this.greenLanes = greenLanes;
    }
}
