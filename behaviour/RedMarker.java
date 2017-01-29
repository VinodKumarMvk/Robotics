package behaviour;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;

public class RedMarker implements Behavior {
	private boolean suppress;
	private static StandardRobot r;
	private int colorID;
	private float angle;
	private float magneticLocation;
	
	public RedMarker(StandardRobot r1){
		r = r1;
	}
	
	public boolean takeControl() {
		colorID = r.cs.getColorID();
		System.out.println(colorID);
		//return colorID == 0 ? true : false ;
		return colorID == 4 ? true : false ;
	}
	
	public void suppress(){
		suppress = true;
	}
	
	public void action(){
		r.state ="red marker";
		magneticLocation = r.chs.getDegrees();
		if(magneticLocation >= 90.0 && magneticLocation <= 270.0) {
			r.mb.backward();
			while(true){
				angle = r.chs.getDegrees();
				if(angle <= 275.0 && angle >=265.0){
					r.mb.stop();
					break;
				}
				
			}
			
		} else {
			r.mb.forward();
			while(true){
				angle = r.chs.getDegrees();
				if(angle <= 275.0 && angle >=265.0){
					r.mb.stop();
					break;
				}
				
			}
			
		}
	}
	
}
