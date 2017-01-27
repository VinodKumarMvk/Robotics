
import behaviour.ForwardBehavior;
import behaviour.InLine;
import behaviour.ObjectAvoid;
import behaviour.ObjectDetector;
import behaviour.OffLine;
import behaviour.OnTrack;
import behaviour.Overshoot;
import behaviour.Scanner;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import robotcontroller.RobotMonitor;
import robotcontroller.StandardRobot;

public class Brainator {
	public static void main(String[] args) throws Exception {
		StandardRobot me = new StandardRobot();
	 	RobotMonitor rm = new RobotMonitor(me,300);
	 	rm.start();
	 	Behavior forward = new ForwardBehavior(me);
	 	Behavior objectDetect = new ObjectDetector(me);
	 	Behavior scanner = new Scanner(me);
	 	Behavior avoid = new ObjectAvoid(me);
	 	Behavior overshoot = new Overshoot(me);
	 	Behavior offLine = new OffLine(me);
	 	Behavior inLine = new InLine(me);
	 	Behavior onTrack = new OnTrack(me);
	 	Behavior [] bArray = {overshoot, offLine, inLine ,objectDetect , scanner, avoid, onTrack};
	 	Arbitrator arb = new Arbitrator(bArray);
	 	arb.start();
	} // end main
} // end ProgFive