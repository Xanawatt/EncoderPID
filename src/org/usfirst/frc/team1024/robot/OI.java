package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.RunX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	public RunX autoDriveX = new RunX();
	
	public Joystick driver = new Joystick(0);
	
	public JoystickButton autoDrive = new JoystickButton(driver, 1);
	public JoystickButton stopDrive = new JoystickButton(driver, 2);
	
	public OI() {
		
	}
	
	public void getOI(){
		autoDrive.whenPressed(autoDriveX);
		
		stopDrive.cancelWhenPressed(autoDriveX);
	}
}

