package behaviour;
import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;
import util.Consts;
public class ObjectAvoid implements Behavior {
	public static boolean suppressed;
	private static StandardRobot r;
	private int distance;
	// whether a max reading is actually a max, or really a min
	private boolean filterToMax = true;
	private int filterCount = 0;
	private int gapCount = 0;
	
	public ObjectAvoid(StandardRobot r1) {
		r = r1;
	}
	
	public boolean takeControl() {
		return r.avoid;
	}

	public void suppress() {
		suppressed = true;
	}
	

	
	
	private int getExtremeDistance() {
		return filterToMax ? Consts.MAX_DISTANCE : Consts.MIN_DISTANCE;
	}
	
	
	private void saveDistance(int distance) {
		this.filterToMax = distance > 12;
		this.distance = distance;
	}
	
	
	private int filter(int sensorDistance) {
		
		if (sensorDistance >= Consts.MAX_DISTANCE) {

			if (filterCount > Consts.FILTER_AMOUNT) {
				
				filterCount = 0;
				return getExtremeDistance();
			} else {

				filterCount ++;
				return distance;
			}
		}
		
		
		filterCount = 0;
		return sensorDistance;
	}
	
	private void left() {
		
		r.ma.setSpeed(Consts.LOW_SPEED);
		r.mb.setSpeed(Consts.HIGH_SPEED);
	}
	
	private void right() {
		
		r.ma.setSpeed(Consts.HIGH_SPEED);
		r.mb.setSpeed(Consts.LOW_SPEED);
	}
	
	private void straight() {
		
		r.ma.setSpeed(Consts.DEFAULT_SPEED);
		r.mb.setSpeed(Consts.DEFAULT_SPEED);
	}
	
	public void action() {
		suppressed = false;
		//setting for finding track
		r.backontrack = true;
		
			int realDistance = filter(r.us.getDistance());
			saveDistance(realDistance);
			int delta = realDistance - Consts.BAND_CENTER;
			LCD.clear();
			LCD.drawString("distance: " + realDistance, 0, 0);
			
			LCD.drawString("delta: " + delta, 0, 2);

			if(r.lastAngleIndex < 0){
				// too far away
				if (delta > Consts.BANDWIDTH) {
					if (gapCount >= Consts.GAP_TIMEOUT) {
						left();
						
					} else {
						gapCount ++;
						straight();
					}
				// too close
				} else if (delta < -Consts.BANDWIDTH) { 
					gapCount = 0;
					right();
					
				} else { 
					gapCount = 0;
					straight(); 
				}
			} else {
				// too far away
				if (delta > Consts.BANDWIDTH) {
					if (gapCount >= Consts.GAP_TIMEOUT) {
						
						right();
					} else {
						gapCount ++;
						straight();
					}
				// too close
				} else if (delta < -Consts.BANDWIDTH) { 
					gapCount = 0;
					left();
					
				} else { 
					gapCount = 0;
					straight(); 
				}
			}
			r.ma.forward();
			r.mb.forward();
	
		
		//r.avoid = false;
		
	
	}
	
	
}
