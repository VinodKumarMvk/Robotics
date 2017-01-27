package behaviour;

import lejos.robotics.subsumption.Behavior;
import robotcontroller.StandardRobot;
import util.Consts;
import util.LineUtil;

public class OnTrack implements Behavior {
	private boolean suppress = false;
	private static StandardRobot r;
	
	public OnTrack(StandardRobot r1){
		r= r1;
	}
	
	public boolean takeControl(){
		
		return r.backontrack && LineUtil.getAvgColorValue() >= r.blackMean-2 && LineUtil.getAvgColorValue() <= r.blackMean+1  ;
	}
	public void suppress(){
		suppress = true;
	}
	public void action(){
		r.avoid = false;
		r.scan = false;
		suppress = false;
		r.ma.stop();
		r.mb.stop();
		r.mc.setSpeed(100);
		r.moveHead(Consts.CENTER, false);
		r.ma.rotate(-45);
		r.mb.rotate(-45);
		//r.ma.backward();
		//r.mb.backward();
		
		r.backontrack = false;
		
	}
}
