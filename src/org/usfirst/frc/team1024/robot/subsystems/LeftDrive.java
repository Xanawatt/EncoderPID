package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LeftDrive extends Subsystem implements PIDSource, PIDOutput {

    public VictorSP left = new VictorSP(RobotMap.leftDrivePWM);
    public Encoder leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, true);
    public PIDController pid;
    
    public LeftDrive(){
    	pid = new PIDController(Constants.Lkp, Constants.Lki, Constants.Lkd, Constants.Lkf, this, this);
    	pid.setAbsoluteTolerance(1.0);
    	pid.setOutputRange(-0.5, 0.5);
    	leftEncoder.setDistancePerPulse(0.0062831); // distance per pulse = pi/500
    	//^This will mean that if we put in 10 for the setpoint, it will try to go 10 feet.
    }
    
    public void initDefaultCommand() {
    	//Left blank intentionally
    }

	@Override
	public void pidWrite(double output) {
		left.set(output);		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		//Left blank intentionally
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return leftEncoder.getDistance();
	}
	
	public void Dashboard() {
		SmartDashboard.putData("Left PID Values", pid);
		SmartDashboard.putNumber("Left PID Output", pidGet());
		
	}
}

