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

public class RightDrive extends Subsystem implements PIDSource, PIDOutput {

    public VictorSP right = new VictorSP(RobotMap.rightDrivePWM);
    public Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB, false);
    public PIDController pid;
    
    public RightDrive(){
    	pid = new PIDController(Constants.Rkp, Constants.Rki, Constants.Rkd, Constants.Rkf, this, this);
    	pid.setAbsoluteTolerance(1.0);
    	pid.setOutputRange(-0.5, 0.5);
    	rightEncoder.setDistancePerPulse(0.0062831); // distance per pulse = pi/500
    	//^This will mean that if we put in 10 for the setpoint, it will try to go 10 feet.
    }
    
    public void initDefaultCommand() {
    	//Left blank intentionally
    }

	@Override
	public void pidWrite(double output) {
		right.set(output);		
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
		return rightEncoder.getDistance();
	}
	
	public void Dashboard() {
		SmartDashboard.putData("Right PID Values", pid);
		SmartDashboard.putNumber("Right PID Output", pidGet());
	}
}

