
package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.ResetEncoders;
import org.usfirst.frc.team1024.robot.subsystems.LeftDrive;
import org.usfirst.frc.team1024.robot.subsystems.RightDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Command resetEncoders;
	public static OI driverControls;
	public static LeftDrive leftDriveAuto = new LeftDrive();
	public static RightDrive rightDriveAuto = new RightDrive();
	
    public void robotInit() {
    	driverControls = new OI();
    	resetEncoders = new ResetEncoders();
    }
    
    public void disabledInit(){
    	leftDriveAuto.leftEncoder.reset();
    	rightDriveAuto.rightEncoder.reset();
    }
	
	public void disabledPeriodic(){
		Scheduler.getInstance().run();
        leftDriveAuto.Dashboard();
        rightDriveAuto.Dashboard();
	}
	
    public void autonomousInit() {
    	leftDriveAuto.leftEncoder.reset();
    	rightDriveAuto.rightEncoder.reset();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        driverControls.getOI();
        leftDriveAuto.Dashboard();
        rightDriveAuto.Dashboard();
    }

    public void teleopInit() {
    	leftDriveAuto.leftEncoder.reset();
    	rightDriveAuto.rightEncoder.reset();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        leftDriveAuto.left.set(driverControls.driver.getRawAxis(1));
        rightDriveAuto.right.set(driverControls.driver.getRawAxis(3));
        
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
