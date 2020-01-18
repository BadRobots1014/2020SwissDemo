/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class DriveForTimeCommand extends CommandBase {
  DriveTrainSubsystem m_driveTrain;
  Timer m_timer = new Timer();
  double m_duration = 1.0;
  double m_speed = 0.0;
  /**
   * Creates a new DriveForTimeCommand.
   */
  public DriveForTimeCommand(DriveTrainSubsystem driveTrain, Double duration, Double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    m_duration = duration;
    m_speed = speed;
    m_timer.reset();
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
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
    if (m_timer.get() < m_duration) {
      m_driveTrain.tankDrive(m_speed, m_speed);      
      return false;
    }
    m_driveTrain.stop();
    return true;
    
  }
}
