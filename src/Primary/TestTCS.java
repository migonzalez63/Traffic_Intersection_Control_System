package Primary;

import java.util.LinkedList;

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

    private int count = 0;

    private Boolean running = true;

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

        /*
        RED (Color.RED),
        YELLOW (Color.YELLOW),
        GREEN (Color.GREEN),
        BLACK (Color.BLACK);

        SignalColor is an enum holding possible signal colors.
         */
       // SignalColor north_south_color, east_west_color;

        /*
        This is a useful way of grouping lights by direction.
        Here we are grouping parallel directions north with south, and east with west.
         */
        //LinkedList<Lanes> north_south = new LinkedList<>();
        //LinkedList<Lanes> east_west = new LinkedList<>();
        Phases currentPhase= Phases.ALL_RED1;
        TICSModes currentMode = TICSModes.DayMode;
        int endPhaseTime=0;
//        for(Lanes l: Lanes.values())
//        {
//            if(l.toString().contains("N") || l.toString().contains("S")) north_south.add(l);
//            else east_west.add(l);
//        }


        while(running){

            /*Emergency Mode code goes below: */

            if (currentMode==TICSModes.EmergencyMode){
                //doSomething
                //Remember that at any point, whenever an emergency car is needed,
                //all that needs to be done is set currentMode to TICSModes.EmergencyMode.
                //In the next second, the program will do whatever you put in doSomething
            }
            /*Malfunction Mode code goes here: */
            else if (currentMode==TICSModes.MalfunctionMode){
                //doSomething
                //Remember that at any point, whenever a malfunction happens,
                //all that needs to be done is set currentMode to TICSModes.EmergencyMode.
                //In the next second, the program will do whatever you put in doSomething
            }
            else if (currentMode==TICSModes.DayMode){
                changeToDayTimes();
            }
            else if (currentMode==TICSModes.NightMode){
                changeToNightTimes();
            }
            /*For day mode, int endPhaseTime and Phase currentPhase were added.
            endPhaseTime is an integer that, when phases are changed, it adds the current
            counter time to however many seconds a phase is supposed to last
            (Example: if counter is 4 and ALL_RED phase is 5, endPhaseTime is 9). It later
            checks to see if counter is 9, which means the phase is ended and the next phase
            should be run.*/
            if (endPhaseTime==count) {
            /*
            Day Mode:
            TICSModes is a simple class that keeps track of the current mode to figure out
            whether timings need to be changed or not (night times have shorter times than
            day times).
            * */
                if (currentMode == TICSModes.DayMode) {
                    currentPhase = findNextPhase(currentPhase);
                    endPhaseTime = count + currentPhase.phaseTime;
                    displayCurrentPhase(currentPhase);
                }

//        if (true) {
//            if (count % 6 == 0) {
//                north_south_color = SignalColor.GREEN;
//                east_west_color = SignalColor.RED;
//            } else if (count % 6 == 1) {
//                north_south_color = SignalColor.YELLOW;
//                east_west_color = SignalColor.RED;
//            } else if (count % 6 == 2) {
//                north_south_color = SignalColor.RED;
//                east_west_color = SignalColor.RED;
//            } else if (count % 6 == 3) {
//                north_south_color = SignalColor.RED;
//                east_west_color = SignalColor.GREEN;
//            } else if (count % 6 == 4) {
//                north_south_color = SignalColor.RED;
//                east_west_color = SignalColor.YELLOW;
//            } else if (count % 6 == 4) {
//                north_south_color = SignalColor.RED;
//                east_west_color = SignalColor.YELLOW;
//            } else {
//                north_south_color = SignalColor.RED;
//                east_west_color = SignalColor.RED;
//            }
//
//        }

                //night mode code goes here
                else if (currentMode == TICSModes.NightMode) {
                    currentPhase = findNextPhase(currentPhase);
                    endPhaseTime = count + currentPhase.phaseTime;
                    displayCurrentPhase(currentPhase);
                }
            }
            System.out.println("CURRENT PHASE: "+currentPhase.toString());


            /*
            This changes our grouping of lanes to the colors specified above.
             */
//            for(Lanes l: north_south)
//            {
//                l.setColor(north_south_color);
//            }
//            for(Lanes l: east_west)
//            {
//                l.setColor(east_west_color);
//            }
//            Lights.WEST.setColor(SignalColor.GREEN);
            count ++;


           // testSensors();

            try {
                sleep(3000);
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
        for (Lanes l : Lanes.values()) {
            System.out.println(l.toString() + " has car waiting: " + l.isCarOnLane());
        }

        for (Lights l : Lights.values()){
            System.out.println(l.toString() + " has ped waiting: " + l.isPedestrianAt());
        }
    }

    private void displayCurrentPhase(Phases currentPhase) {
        if (currentPhase.greenLanes!=null) {
            for (Lanes greenLane : currentPhase.greenLanes) {
                greenLane.setColor(SignalColor.GREEN);
            }
        }
        if(currentPhase.yellowLanes!=null) {
            for (Lanes yellowLane : currentPhase.yellowLanes) {
                yellowLane.setColor(SignalColor.YELLOW);
            }
        }
        if (currentPhase.redLanes!=null){
            for(Lanes redLane: currentPhase.redLanes){
                redLane.setColor(SignalColor.RED);
            }
        }
    }

    public Phases findNextPhase(Phases currentPhase){
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
                return Phases.EW_LEFT_GREEN;
            case EW_LEFT_GREEN:
                return Phases.EW_LEFT_YELLOW;
            case EW_LEFT_YELLOW:
                return Phases.ALL_RED3;
            case ALL_RED3:
               return Phases.EW_GREEN;
            case EW_GREEN:
                return Phases.EW_YELLOW;
            case EW_YELLOW:
                return Phases.ALL_RED4;
            case ALL_RED4:
                return Phases.NS_LEFT_GREEN;
            default:
                System.out.println("There's no following case for the current phase in the switch!");
        }
        return null;
    }


    /**
     * changeToNightTimes
     * In an effort to save duplicate classes such as having dayPhases and nightPhases enum,
     * changeToNightTimes and changeToDayTimes were written.
     * They simply go through each phase and change the hardcoded times.
     * I did not add a paramter as phases might last longer than others
     */
    public void changeToNightTimes(){
        Phases.NS_LEFT_YELLOW.changePhaseTime(3);
        Phases.NS_LEFT_GREEN.changePhaseTime(3);
        Phases.EW_LEFT_GREEN.changePhaseTime(3);
        Phases.EW_LEFT_YELLOW.changePhaseTime(3);
        Phases.NS_GREEN.changePhaseTime(3);
        Phases.NS_YELLOW.changePhaseTime(3);
        Phases.EW_GREEN.changePhaseTime(3);
        Phases.EW_YELLOW.changePhaseTime(3);
        Phases.ALL_RED1.changePhaseTime(3);
        Phases.ALL_RED2.changePhaseTime(3);
        Phases.ALL_RED3.changePhaseTime(3);
        Phases.ALL_RED4.changePhaseTime(3);
    }

    /**
     * This method simply goes through each phase and changes to hardcoded times.
     * I did not add a paramter as phases might last longer than others
     * */
    public void changeToDayTimes(){
        Phases.NS_LEFT_YELLOW.changePhaseTime(2);
        Phases.NS_LEFT_GREEN.changePhaseTime(2);
        Phases.EW_LEFT_GREEN.changePhaseTime(2);
        Phases.EW_LEFT_YELLOW.changePhaseTime(2);
        Phases.NS_GREEN.changePhaseTime(2);
        Phases.NS_YELLOW.changePhaseTime(2);
        Phases.EW_GREEN.changePhaseTime(2);
        Phases.EW_YELLOW.changePhaseTime(2);
        Phases.ALL_RED1.changePhaseTime(2);
        Phases.ALL_RED2.changePhaseTime(2);
        Phases.ALL_RED3.changePhaseTime(2);
        Phases.ALL_RED4.changePhaseTime(2);
    }
}
