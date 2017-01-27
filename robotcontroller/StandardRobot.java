package robotcontroller;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.ColorHTSensor;
import util.Consts;
import util.LineHelper;

public class StandardRobot {
	public static ColorHTSensor cs;
	public static LightSensor ls;
	public static UltrasonicSensor us;
	public static NXTRegulatedMotor ma, mb;
	public static NXTRegulatedMotor mc;
	public static NXTMotor leftMotor;
	public static NXTMotor rightMotor;
	public static boolean scan;
	public static boolean backontrack;
	public static boolean avoid;
	public static int avoidDir = Consts.CENTER;
	public static LineHelper line;
	public static int white, whiteMean;
	public static int black, blackMean;
	public static int threshold_line_color = 0;
	public  int lastAngleIndex = 0; 
	//turn:  0 center, 1 left, 2 right, 3 lock
		public int getAngleAdj(int turn){
			if(lastAngleIndex != turn) {
				if(lastAngleIndex == Consts.CENTER){
					return Consts.SCAN_ANGLE[turn];
				} else if( lastAngleIndex == Consts.LEFT && turn == Consts.CENTER) {
					return Consts.SCAN_ANGLE[Consts.RIGHT];
				} else if( lastAngleIndex == Consts.RIGHT && turn == Consts.CENTER) {
					return Consts.SCAN_ANGLE[Consts.LEFT];
				} else if( lastAngleIndex == Consts.LOCK_L && turn == Consts.CENTER) {
					return Consts.SCAN_ANGLE[Consts.LOCK_R];
				} else if( lastAngleIndex == Consts.LOCK_R && turn == Consts.CENTER) {
					return Consts.SCAN_ANGLE[Consts.LOCK_L];
				} else if( lastAngleIndex == Consts.LEFT && turn == Consts.RIGHT) {
					return Consts.SCAN_ANGLE[Consts.RIGHT]+Consts.SCAN_ANGLE[Consts.RIGHT];
				} else if( lastAngleIndex == Consts.RIGHT && turn == Consts.LEFT) {
					return Consts.SCAN_ANGLE[Consts.LEFT]+Consts.SCAN_ANGLE[Consts.LEFT];
				}
			}
			return 0;
		}
		public void moveHead(int movement, boolean flag) {
			int angle = getAngleAdj(movement);
			lastAngleIndex = movement;
			mc.rotate(angle, flag);
		}
	public StandardRobot() {
		// instantiate sensors
		try 
		{
			us = new UltrasonicSensor(SensorPort.S3);
			cs = new ColorHTSensor(SensorPort.S4);
			ls = new LightSensor(SensorPort.S1);
			// instantiate motors
			ma = new NXTRegulatedMotor(MotorPort.A);
			mb = new NXTRegulatedMotor(MotorPort.B);
			mc = new NXTRegulatedMotor(MotorPort.C);
			leftMotor = new NXTMotor(MotorPort.A);
			rightMotor = new NXTMotor(MotorPort.B);
			
			line = new LineHelper();
			
			white = line.lineValue.getWhite();
			black = line.lineValue.getBlack();
			
			threshold_line_color = (white + black) / 2;
			whiteMean = (white + threshold_line_color) / 2;
			blackMean = (black + threshold_line_color) / 2;
			
			
			scan = false;
			backontrack = false;
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}