package util;

public class Consts {
	public static final boolean PASSES = true;
	public static final int CENTER = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int LOCK_L = 3;
	public static final int LOCK_R = 4;
	// scan angle  0 center, 1 left, 2 right, 3 lockl, 4 lockr
	public static final int SCAN_ANGLE[] = {0,-90,90,-55, 55};
	public static final int LOCK_ANGLE = 90;
	
	public static final int THRES_DIS = 16;
	
	public static final int ROTATION_DIS = 180;
	
	public static final int THRES_ROT_DIS = 15;
	public static final float DIV_CONST = 0.6f;
	public static final float MUL_CONST = 1.5f;
	public static final int TURN_POWER = 22;
	public static final int DRIVE_POWER = 25;
	
	
	public static final int MIN_DISTANCE = 1;
	public static final int MAX_DISTANCE = 255;
	
	public static final int FILTER_AMOUNT = 8;
	
	public static final int LOW_SPEED = 80;
	public static final int MEDIUM_SPEED = 150;
	public static final int HIGH_SPEED =250;
	public static final int BAND_CENTER = 18;
	public static final int BANDWIDTH = 4;
	public static final int DEFAULT_SPEED = 200;
	public static final int GAP_TIMEOUT = 8;
	public static final int TIMEOUT = 10;
}
