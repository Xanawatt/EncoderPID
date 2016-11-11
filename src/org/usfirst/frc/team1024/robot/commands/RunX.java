package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunX extends Command {

	public boolean atSetpoint;
	public double timeToReachSetpoint;
	
    public RunX() {
    	requires(Robot.leftDriveAuto);
    	requires(Robot.rightDriveAuto);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	atSetpoint = false;
    	timeToReachSetpoint = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*Robot.leftDriveAuto.pid.setSetpoint(10.0);
    	Robot.leftDriveAuto.pid.enable(); */
    	Robot.rightDriveAuto.pid.setSetpoint(0.0);
    	Robot.rightDriveAuto.pid.enable();
    	
    	if (Robot.leftDriveAuto.pid.onTarget() && timeToReachSetpoint <= 0.0 && Robot.rightDriveAuto.pid.onTarget()) {
    		atSetpoint = true;
    		timeToReachSetpoint = timeSinceInitialized();
    		SmartDashboard.putNumber("PID Timer", timeToReachSetpoint);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.leftDriveAuto.pidWrite(0.0);
    	Robot.leftDriveAuto.pid.disable();
    	
    	Robot.rightDriveAuto.pidWrite(0.0);
    	Robot.rightDriveAuto.pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
