/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;

public class DriveForDistCommand extends CommandBase {
  DriveTrainSubsystem m_driveTrain;
  UltrasonicSubsystem m_distSensor;
  double m_distance_IN = 12.0;
  double m_maxSpeed = 0.5;
  // proportional speed constant
  private static final double kP = 0.05;
  /**
   * Creates a new DriveForTimeCommand.
   */
  public DriveForDistCommand(DriveTrainSubsystem driveTrain, UltrasonicSubsystem distSensor, Double dist_IN, Double maxSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_distSensor = distSensor;
    m_distance_IN = dist_IN;
    m_maxSpeed = maxSpeed;
    addRequirements(driveTrain);
    addRequirements(distSensor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // sensor returns a value from 0-4095 that is scaled to inches
    double currentDistance = m_distSensor.getDist_IN();

    // convert distance error to a motor speed
    double currentSpeed = (m_distance_IN - currentDistance) * kP * m_maxSpeed;

    if (currentDistance > m_distance_IN) {
      m_driveTrain.tankDrive(currentSpeed, currentSpeed);      
      return false;
    }
    m_driveTrain.stop();
    return true;
    
  }
}
