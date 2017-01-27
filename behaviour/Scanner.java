package behaviour;

import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;
import util.Consts;
import util.LineUtil;

public class Scanner implements Behavior {
	public static boolean suppressed;
	private static StandardRobot r;
	

	
	public Scanner(StandardRobot r1) {
		r = r1;
	}
	
	public boolean takeControl() {
	
		return r.scan && !r.avoid;
	}

	public void suppress() {
		suppressed = true;
	}
	
	
	
	
	
	public int getDirection(float l, float c, float r){
		
		if(l>c && l>r)
			return Consts.LEFT;
		if(r>c && r>l)
		    return Consts.RIGHT;
		return Consts.CENTER;
	}
		
	public void action() {
		
		float leftDis, rightDis, centerDis;
		
		suppressed = false;
		r.mc.setSpeed(100);
		r.moveHead(Consts.LEFT, false);
		leftDis = r.us.getRange();
		r.moveHead(Consts.RIGHT, false);
		rightDis = r.us.getRange();
		r.moveHead(Consts.CENTER, false);
		centerDis = r.us.getRange();
		
		if(getDirection(leftDis, centerDis, rightDis) == Consts.LEFT) {
			r.moveHead(Consts.LOCK_R, false);
			
			r.ma.setSpeed(Consts.ROTATION_DIS);
			r.ma.backward();
			r.mb.setSpeed(Consts.ROTATION_DIS);
			r.mb.forward();
			r.avoidDir = Consts.LEFT;
		} else if(getDirection(leftDis, centerDis, rightDis) == Consts.RIGHT) {
			r.moveHead(Consts.LOCK_L, false);
			r.mb.setSpeed(Consts.ROTATION_DIS);
			r.mb.backward();
			r.ma.setSpeed(Consts.ROTATION_DIS);
			r.ma.forward();
			r.avoidDir = Consts.RIGHT;
		}
		
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			;
		}
		/*while(!(LineUtil.getAvgColorValue() >= r.whiteMean-2
				&& LineUtil.getAvgColorValue() <= r.whiteMean+1) ){
			r.ma.setSpeed(Consts.LOW_SPEED);
			r.mb.setSpeed(Consts.LOW_SPEED);
			r.mb.forward();
			r.ma.forward();
		}*/
		if(!r.mc.isMoving()){
			r.avoid = true;
		}
		
	}
}
