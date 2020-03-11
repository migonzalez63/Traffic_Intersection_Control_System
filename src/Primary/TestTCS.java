package Primary;

import Graphics.Grounds.LaneDisplay;

/**
 * TestTCS is the access point for Traffic Control System (TCS) interaction with the testbed.
 * There exist a total of 5 method calls for interacting with the testbed properly.
 * These 5 methods are detailed and demo'd below. Additional tips are included for
 * interacting with the testbed.
 *
 *   Methods of interest.
 *       Class: Lanes
 *           public boolean getCarOnLane()
 *           public boolean getEmergencyOnLane()
 *           public void setColor(SignalColor color)
 *
 *       Class: Lights
 *           public void setColor(SignalColor c)
 *           public boolean isPedestrianAt()
 *
 */
class TestTCS extends Thread {
    private Boolean running = true;
    private TICSModes currentMode = TICSModes.MalfunctionMode;
    private Lanes firstLane = null;
    private long fastestArrival = 0;

    /**
     * TestTCS.begin() is the communication point between the testbed and the
     * TCS being tested on. Interactions between the testbed and TCS should all
     * be laid out in this method.
     */
    public void begin() {

        //TimedModeTest.run();
        //ResponsiveTest.run();
        //EmergencyModeTest.run();

    }

    /*
     * This is the old begin method. I kept it here for reference
     */
    public void testBegin() {

        Phases currentPhase= Phases.ALL_RED1;
        //int endPhaseTime=0;

        while(running){
            changeLightTimes();

            switch (currentMode) {
                case NightMode:
                case DayMode:
                    currentPhase = findNextPhase(currentPhase);
                    displayCurrentPhase(currentPhase);
                    break;
                case EmergencyMode:
                    // EmergencyMode code goes here
                    //Remember that at any point, whenever an emergency car is needed,
                    //all that needs to be done is set currentMode to TICSModes.EmergencyMode.
                    //In the next second, the program will do whatever you put in doSomething
                    break;
                case MalfunctionMode:
                    currentPhase = findNextPhase(currentPhase);
                    displayCurrentPhase(currentPhase);

                    /*
                        Resets the current first lane in order to not reconsider that lane
                        to allow passage
                     */
                    if(currentPhase == Phases.ALL_RED1 && firstLane != null) {
                        fastestArrival = 0;
                        firstLane.setArriveTime(0);
                        firstLane = null;
                    }
                    break;
            }

            //System.out.println("CURRENT PHASE: " + currentPhase.toString());
            //count ++;
            //testSensors();

            try {
                sleep(currentPhase.getPhaseTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Test ended..");
    }

    public void end(){
        running = false;
    }

    /*
    Loops over the ped crosswalks and car lanes to
    print if each has traffic waiting on it
    */
    private void testSensors() {
        long fastestArrival = 0;
        Lanes firstLaneArrival = null;

        for (Lanes l : Lanes.values()) {
           // System.out.println(l.toString() + " has car waiting: " + l.isCarOnLane());
            //System.out.println("Car arrived on " + l.toString() + " at " + l.getArriveTime());
        }

        for (Lights l : Lights.values()){
            System.out.println(l.toString() + " has ped waiting: " + l.isPedestrianAt());
        }
    }

    private void displayCurrentPhase(Phases currentPhase) {
        if (currentPhase.getGreenLanes() != null) {
            for (Lanes greenLane : currentPhase.getGreenLanes()) {
                greenLane.setColor(SignalColor.GREEN);
            }
        }
        if(currentPhase.getYellowLanes() != null) {
            for (Lanes yellowLane : currentPhase.getYellowLanes()) {
                yellowLane.setColor(SignalColor.YELLOW);
            }
        }
        if (currentPhase.getRedLanes() != null){
            for(Lanes redLane: currentPhase.getRedLanes()){
                redLane.setColor(SignalColor.RED);
            }
        }
    }

    private Phases findNextPhase(Phases currentPhase){
        /*
          Finds the next possible phase when in Malfunction Mode.
          The intersection will turn into a four way stop and will
          allow passage only to the vehicle that gets into the intersection
          first.
         */
        if(currentMode == TICSModes.MalfunctionMode) {

            // All phases will loop back to red immediately, to give it the illusion
            // that only one set of vehicles is allowed to pass
            switch (currentPhase) {
                case NS_GREEN:
                case EW_GREEN:
                case FOURWAY_N_GREEN:
                case FOURWAY_S_GREEN:
                case FOURWAY_E_GREEN:
                case FOURWAY_W_GREEN:
                    return Phases.ALL_RED1;
            }

            /*
              Iterates through the available lanes and checks to see which one was occupies
              by a vehicle first.
             */
            for(Lanes l : Lanes.values()) {
                if(l.getArriveTime() != 0) {
                    if ((fastestArrival == 0 || l.getArriveTime() < fastestArrival)) {
                        fastestArrival = l.getArriveTime();
                        firstLane = l;
                    }
                }
            }

            // When no vehicles are present in the intersection, we will act as if the
            // intersection was all red, to allow pedestrians to cross
            if(firstLane == null) {
                return Phases.ALL_RED1;
            }

            /*
              Once we find what lane was the first one to have a vehicle in, we will determine
              what to do depending on a lane.

              If the vehicle is in any of the left turn lanes, i.e. any lane with "1", we will
              allow passage of all three vehicles in the road, but only to that road.

              If the vehicle is in any other lane than the turning lanes, we can allow passage to
              the vehicles in the non left-turn lanes for the current road and the road parallel
              to it, heading in the opposite direction.
             */
            switch (firstLane) {
                case N1:
                    return Phases.FOURWAY_N_GREEN;
                case S1:
                    return Phases.FOURWAY_S_GREEN;
                case N2:
                case N3:
                case S2:
                case S3:
                    return Phases.NS_GREEN;
                case E1:
                    return Phases.FOURWAY_E_GREEN;
                case W1:
                    return Phases.FOURWAY_W_GREEN;
                case E2:
                case E3:
                case W2:
                case W3:
                    return Phases.EW_GREEN;
                default:
                    return Phases.ALL_RED1;
            }
        }

        /*
          This encompases all the phases possible during Day and Night Mode operations. Due to this,
          both Modes have been implemented in the switch statement, with a few conditionals to help
          determine when things should happen in each mode.
         */
        switch (currentPhase) {
            case NS_LEFT_GREEN:
                return Phases.NS_LEFT_YELLOW;
            case NS_LEFT_YELLOW:
                return Phases.ALL_RED1;
            case ALL_RED1:
                return Phases.NS_GREEN;
            case NS_GREEN:
                return Phases.NS_YELLOW;
            case NS_YELLOW:
                return Phases.ALL_RED2;
            case ALL_RED2:
                // Want to allow passage to vehicles in left turn only if they are present in that lane
                if(currentMode == TICSModes.NightMode) {
                    return (Lanes.E1.isCarOnLane() || Lanes.W1.isCarOnLane()) ? Phases.EW_LEFT_GREEN : Phases.EW_GREEN;
                }
                return Phases.EW_LEFT_GREEN;
            case EW_LEFT_GREEN:
                return Phases.EW_LEFT_YELLOW;
            case EW_LEFT_YELLOW:
                return Phases.ALL_RED3;
            case ALL_RED3:
                return Phases.EW_GREEN;
            case EW_GREEN:
                // If no vehicles are present in the less busy street, in our case, the North and South roads, then
                // we don't want to change the lights of the main road, in order to allow more vehicles to move through
                if(currentMode == TICSModes.NightMode) {
                    return (Lanes.S1.isCarOnLane() || Lanes.N1.isCarOnLane()
                            || Lanes.S2.isCarOnLane() || Lanes.N2.isCarOnLane()) ? Phases.EW_YELLOW : Phases.EW_GREEN;
                }
                return Phases.EW_YELLOW;
            case EW_YELLOW:
                return Phases.ALL_RED4;
            case ALL_RED4:
                // Want to allow passage to vehicles in left turn only if they are present in that lane
                if(currentMode == TICSModes.NightMode) {
                    return (Lanes.N1.isCarOnLane() || Lanes.S1.isCarOnLane()) ? Phases.NS_LEFT_GREEN : Phases.NS_GREEN;
                }
                return Phases.NS_LEFT_GREEN;
            default:
                System.out.println("There's no following case for the current phase in the switch!");
        }

        return Phases.ALL_RED4;
    }

    /**
     * Provides a way to change the timings of the phases according to the mode in which the TICS is
     * operating on.
     *
     * Note: Day Mode and Night Mode have the same timings, but behave differently. Malfunction mode
     * has timings that allow us to make the intersection act like a four way stop.
     */
    public void changeLightTimes() {
        switch (currentMode){
            case NightMode:
            case DayMode:
                Phases.NS_LEFT_YELLOW.setPhaseTime(4000);
                Phases.NS_LEFT_GREEN.setPhaseTime(4000);
                Phases.EW_LEFT_GREEN.setPhaseTime(4000);
                Phases.EW_LEFT_YELLOW.setPhaseTime(3000);
                Phases.NS_GREEN.setPhaseTime(6000);
                Phases.NS_YELLOW.setPhaseTime(2000);
                Phases.EW_GREEN.setPhaseTime(6000);
                Phases.EW_YELLOW.setPhaseTime(2000);
                Phases.ALL_RED1.setPhaseTime(3000);
                Phases.ALL_RED2.setPhaseTime(3000);
                Phases.ALL_RED3.setPhaseTime(3000);
                Phases.ALL_RED4.setPhaseTime(3000);
                break;
            case MalfunctionMode:
                Phases.EW_GREEN.setPhaseTime(300);
                Phases.NS_GREEN.setPhaseTime(300);
                Phases.FOURWAY_N_GREEN.setPhaseTime(300);
                Phases.FOURWAY_S_GREEN.setPhaseTime(300);
                Phases.FOURWAY_E_GREEN.setPhaseTime(300);
                Phases.FOURWAY_W_GREEN.setPhaseTime(300);
                Phases.ALL_RED1.setPhaseTime(3500);
        }
    }
}
