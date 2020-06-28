# TrafficSimulator

## Most credit for this simulator goes to Andrew Morin, Jacob Hurst, Beau Kujath, and Alex Schmidt-Gonzales, as they set up the code for the GUI and lanes

### Introduction

The Traffic Intersection Control System, or TICS for short, will simulate a simple 4-way traffic intersection, in which the East-West bound lanes are the primary streets and the North-South lanes will be a side or auxiliary road. The TICS will provide seamless control over traffic in order to produce high troughput of vehicles while keeping both passangers and pedestrians safe from each other.

### Sensors

There are sensors on each incoming lane to detect cars waiting and "button" sensors for each crosswalk to detect pedestrians waiting. The sensors for each will set a boolean value to true for each lane/crosswalk as soon as the ped/car comes to a stop waiting at the intersection/corner. As soon as the crosswalk or lane signal is set to green the boolean value will be reset to false.

### Phases

The Traffic Intersection Control System will have a set number of phases that will be used in order to indicate a valid state of the intersection. With this, we will be able to detect any illegal states that would cause a collision with a vehicle or a pedestrian.

### Emergency Vehicles

Whenever an ambulance is detected, priority will be given to th emergency vehicles in a First Come, First Serve basis. If the lane in which the emergency vehicle spawns dies not intefere with another lane, it will allow passage of other lanes as well, in order to increase the throughput of traffic.


### Emergency Mode

If a situation would arrise in which there would be a fault in the traffic intersection, the intersection would move into an emergency mode, which is indicated by flashing red lights, and will remain as such until the intersection is reset.
