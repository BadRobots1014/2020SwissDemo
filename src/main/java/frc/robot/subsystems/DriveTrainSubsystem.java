/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveTrainSubsystem extends SubsystemBase {

  // The motors on the left side of the drive.
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(
      new PWMTalonSRX(DriveConstants.kLeftMotor1Port), new PWMTalonSRX(DriveConstants.kLeftMotor2Port));

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(
      new PWMTalonSRX(DriveConstants.kRightMotor1Port), new PWMTalonSRX(DriveConstants.kRightMotor2Port));

  // The robot's drive
  private final DifferentialDrive m_drive;
  
  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrainSubsystem() {
    m_leftMotors.setInverted(true);
    m_rightMotors.setInverted(true);
    m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  }

  public void tankDrive(double leftSpeed, double rightSpeed)
  {
    m_drive.tankDrive(leftSpeed, rightSpeed);    
  }

  public void arcadeDrive(double speed, double angle) {
    m_drive.arcadeDrive(speed, angle, false);
  }

  public void stop() {
    m_drive.stopMotor();
  }

    /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
