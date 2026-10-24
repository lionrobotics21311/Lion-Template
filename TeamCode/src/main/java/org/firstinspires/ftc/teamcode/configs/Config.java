package org.firstinspires.ftc.teamcode.configs;

public class Config {
    // Drivetrain
    public static int FrontLeftDrive = 0;
    public static int FrontRightDrive = 1;
    public static int BackLeftDrive = 2;
    public static int BackRightDrive = 3;

    // Your pinpoint should NEVER be plugged into port 0
    // Port 0 is used by the internal IMU and cannot be used by an external IMU
    // ports on the expansion hub are treated as extensions of the port list (4,5,6,7) 4ex=0con
    public static int PinpointIMU = 1;
    public static int Intake = 0;
    public static int Arm = 1;
    public static int flyWheel=3;
    public static int intake_right;
    public static int intake_left;
}
