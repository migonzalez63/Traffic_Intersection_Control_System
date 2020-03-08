package Primary;

import Primary.Lanes;

import java.util.Arrays;
import java.util.LinkedList;

import static Primary.Lanes.*;

public enum Phases {

    NS_LEFT_GREEN(new LinkedList<Lanes>(Arrays.asList(N1, S1)), null, new LinkedList<Lanes>(Arrays.asList(N2, N3, E1, E2, E3, W1, W2, W3,  S2, S3)), 7, false,  false),
    NS_LEFT_YELLOW (null, new LinkedList<Lanes>(Arrays.asList(N1, S1)), new LinkedList<Lanes>(Arrays.asList(N2, N3, E1, E2, E3, W1, W2, W3,  S2, S3)), 7, false, false),
    EW_LEFT_GREEN(new LinkedList<Lanes>(Arrays.asList(E1, W1)), null, new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, E2, E3, W2, W3, S1, S2,S3)),  7, false, false),
    EW_LEFT_YELLOW(null, new LinkedList<Lanes>(Arrays.asList(E1, W1)), new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, E2, E3, W2, W3, S1, S2,S3)),  7, false, false),
    NS_GREEN(new LinkedList<Lanes>(Arrays.asList(N2, N3, S2, S3)),null,  new LinkedList<Lanes>(Arrays.asList(N1, S1, E1, E2, E3, W1, W2, W3)),7, true, false),
    NS_YELLOW(null,new LinkedList<Lanes>(Arrays.asList(N2, N3, S2, S3)),  new LinkedList<Lanes>(Arrays.asList(N1, S1, E1, E2, E3, W1, W2, W3)),7, true, false),
    EW_GREEN(new LinkedList<Lanes>(Arrays.asList(E2, E3, W2, W3)), null, new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, S1, S2, S3, E1, W1)),  7, false, true),
    EW_YELLOW(null, new LinkedList<Lanes>(Arrays.asList(E2, E3, W2, W3)), new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, S1, S2, S3, E1, W1)), 7, false, true),
    ALL_RED(new LinkedList<Lanes>(Arrays.asList(N1, N2, N3, E1, E2, E3, W1, W2, W3, S1, S2, S3)), null, null, 7, false, false);

    int phaseTime;
    LinkedList<Lanes> redLanes;
    LinkedList<Lanes> greenLanes;
    LinkedList<Lanes> yellowLanes;
    boolean nspedestrians;
    boolean ewpedestrians;

    Phases (LinkedList<Lanes> greenLanes,LinkedList<Lanes> yellowLanes, LinkedList<Lanes> redLanes, int phaseTime, boolean nsPedestrians, boolean ewPedestrians) {
        this.phaseTime = phaseTime;
        this.redLanes = redLanes;
        this.greenLanes = greenLanes;
        this.yellowLanes = yellowLanes;
        this.nspedestrians=nsPedestrians;
        this.ewpedestrians=ewPedestrians;
    }
}
