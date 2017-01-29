package behaviour;

import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;

public class YellowMarker implements Behavior {
	private boolean suppress = false;
	private static StandardRobot r;
	private int colorID;
	
	public YellowMarker(StandardRobot r1){
		r1 = r;
	}
	
	public boolean takeControl() {
		colorID = r.cs.getColorID();
		System.out.println(colorID);
		return colorID == 1 ? true : false ;
	}
	
	public void suppress() {
		suppress = true;
	}
	
	public void action() {
		r.state ="yellow marker";
	}
}
