package behaviour;


import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;

public class ForwardBehavior implements Behavior {
	public static boolean suppressed;
	private static StandardRobot r;

	public ForwardBehavior(StandardRobot r1) {
		r = r1;
	}

	public boolean takeControl() {
		return !r.scan;
	}

	public void suppress() {
		suppressed = true;
	}

	public void action() {
		suppressed = false;
		r.ma.forward();
		r.mb.forward();
		r.ma.setSpeed(100);
		r.mb.setSpeed(100);
		while (!suppressed) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				;
			}
		}
	}
} // end class