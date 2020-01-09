/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI.Port;

public class DriveTrainSubsystem extends SubsystemBase {

  // The motors on the left side of the drive.
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(
      new PWMTalonSRX(0), new PWMTalonSRX(1));

  // The motors on the right side of the drive.
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(
      new PWMTalonSRX(2), new PWMTalonSRX(3));

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  private final Gyro m_gyro = new ADXRS450_Gyro(Port.kMXP);


  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrainSubsystem() {
    // Skyler is setup such that inversion is not necessary
    m_drive.setRightSideInverted(false);
  }

  public double getHeading() {
    return m_gyro.getAngle();
  }

  public void tankDrive(double leftSpeed, double rightSpeed)
  {
    m_drive.tankDrive(leftSpeed, rightSpeed);    
  }

  public void arcadeDrive(double speed, double angle) {
    m_drive.arcadeDrive(speed, angle);
  }

  public void stop() {
    m_drive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
