package behaviour;

import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;
import util.Consts;
import util.LineUtil;

public class InLine implements Behavior {
	private boolean suppress = false;
	private static StandardRobot r;
	int color;

	
	public InLine(StandardRobot r1){
		r= r1;
		
	}
	
	
	public int getCorrection(int power){
		color = LineUtil.getAvgColorValue();
		return 4 * power * (r.threshold_line_color-color)/(r.whiteMean -r. blackMean);
	}
	public boolean takeControl(){
		
		return !r.backontrack && LineUtil.getAvgColorValue() >= r.blackMean && LineUtil.getAvgColorValue() <= r.whiteMean;
	}
	public void suppress(){
		suppress = true;
	}
	public void action(){
		suppress = false;
		r.ma.suspendRegulation();
		r.mb.suspendRegulation();
		while (!suppress && LineUtil.getAvgColorValue() >= r.blackMean && LineUtil.getAvgColorValue() <= r.whiteMean) {
			int error = getCorrection(Consts.DRIVE_POWER);
			LCD.clear();
			
			r.leftMotor.setPower(Math.abs(Consts.DRIVE_POWER - error));
			r.leftMotor.forward();
			
			r.rightMotor.setPower(Math.abs(Consts.DRIVE_POWER + error));
			r.rightMotor.forward();
			
			Thread.yield();
		}
		suppress = false;
	}
}
