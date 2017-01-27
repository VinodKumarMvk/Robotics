package behaviour;
import lejos.robotics.subsumption.*;
import robotcontroller.StandardRobot;
import util.Consts;
import lejos.nxt.*;

public class ObjectDetector implements Behavior {
	public static boolean suppressed;
	private static StandardRobot r;

	public ObjectDetector(StandardRobot r1) {
		r = r1;
	}

	public boolean takeControl() {
		return ( (r.us.getRange() < Consts.THRES_DIS) && !r.scan );
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		
		r.ma.stop();
		r.mb.stop();
		r.scan = true;
	}
} // end class