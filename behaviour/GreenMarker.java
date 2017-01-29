package behaviour;

import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;

public class GreenMarker implements Behavior {
	private boolean suppress = false;
	private static StandardRobot r;
	private int colorID;

	public GreenMarker(StandardRobot r1) {
		r = r1;
	}
	
	public boolean takeControl() {
		colorID = r.cs.getColorID();
		System.out.println(colorID);
		return colorID == 3 ? true : false ;	
	}
	
	public void suppress() {
		suppress = true;
	}
	
	public void action() {
		r.state ="green marker";
	}
}