package behaviour;

import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;
import util.Consts;
import util.LineUtil;

public class OffLine implements Behavior {
	private boolean suppress = false;
	private static StandardRobot r;
	int leftTurn;
	int rightTurn;
	int color;
	
	public OffLine(StandardRobot r1){
		r = r1;
		
	}
	public boolean takeControl(){
		return LineUtil.getAvgColorValue() > r.whiteMean;
	}
	public void suppress(){
		suppress = true;
	}
	
	public void setColor(int color) {
		this.color = color;
	}

	public void setLeftTurn(int leftTurn) {
		this.leftTurn = leftTurn;
	}

	public void setRightTurn(int rightTurn) {
		this.rightTurn = rightTurn;
	}
	
	public int getColor() {
		color = LineUtil.getAvgColorValue();
		return color;
	}
	
	public int getLeftTurn() {
		return leftTurn;
	}

	public int getRightTurn() {
		return rightTurn;
	}

	
	public int getCorrection(int power){
		color = LineUtil.getAvgColorValue();
		return 4 * power * (r.threshold_line_color-color)/(r.whiteMean -r. blackMean);
	}
	public void action(){

		while (!suppress && LineUtil.getAvgColorValue() > r.whiteMean) {
			int error = getCorrection(Consts.TURN_POWER);
			setLeftTurn(Consts.TURN_POWER - error);
			r.leftMotor.setPower(Math.abs(getLeftTurn()/3));
			if(getLeftTurn() > 0)
				r.leftMotor.forward();
			else
				r.leftMotor.backward();
			
			setRightTurn(Consts.TURN_POWER + error);
			r.rightMotor.setPower(Math.abs(getRightTurn()/2));
			if(getRightTurn() > 0)
				r.rightMotor.forward();
			else
				r.rightMotor.backward();
			Thread.yield();
		}
		suppress = false;
	}
}
