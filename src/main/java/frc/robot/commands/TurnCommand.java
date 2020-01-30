/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.util.GyroProvider;

public class TurnCommand extends CommandBase {
    private final DriveTrainSubsystem m_driveTrain;
    private final GyroProvider m_gyro;
    private double m_startHeading;
  /**
   * Creates a new TurnCommand.
   */
  public TurnCommand(DriveTrainSubsystem driveTrain, GyroProvider gyro) {
    m_driveTrain = driveTrain;
    m_gyro = gyro;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println(m_gyro.getHeading());
    m_startHeading = m_gyro.getHeading();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_gyro.getHeading() < (m_startHeading + 90)) {
        m_driveTrain.tankDrive(1.0, -1.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_gyro.getHeading() >= (m_startHeading + 90)) {
        return true;
    } else {
        return false;
    }
  }
}
