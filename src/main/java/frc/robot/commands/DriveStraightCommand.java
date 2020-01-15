/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.util.GyroProvider;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveStraightCommand extends PIDCommand {
  
  //private final DoubleSupplier m_headingProvider;
  /**
   * Creates a new PIDDriveTrain.
   */
  public DriveStraightCommand(DoubleSupplier speed, GyroProvider gyroProvider, DriveTrainSubsystem driveTrain) {    
    
    super(
        // The controller that the command will use
        new PIDController(DriveConstants.kStabilizationP, DriveConstants.kStabilizationI,
        DriveConstants.kStabilizationD),
        // This should return the measurement
        gyroProvider::getTurnRate,
        0,
        // This should return the setpoint (can also be a constant)
        // This uses the output
        outputTurnRate -> driveTrain.directDrive(speed.getAsDouble(), outputTurnRate),
        driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
    //m_headingProvider = headingProvider;
    //System.out.println("Drive Straight init");
    //addRequirements(driveTrain);
    // Configure additional PID options by calling `getController` here.
    SmartDashboard.putData(getController());
  }

  @Override
  public void initialize() {
    //System.out.println("Initialize called:" + m_headingProvider.getAsDouble());
    //getController().setSetpoint(m_headingProvider.getAsDouble());
    
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
   }
}
