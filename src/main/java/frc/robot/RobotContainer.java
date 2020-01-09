/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.DriveForTimeCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_driveTrain = new DriveTrainSubsystem();

  private final TankDriveCommand m_tankdrivecommand = new TankDriveCommand(m_driveTrain);

  private final DriveForTimeCommand m_autoDriveCommand = new DriveForTimeCommand(m_driveTrain, 5.0, 0.5);

  private final XboxController m_driverController = new XboxController(Constants.PrimaryDriverController);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    configureButtonBindings();
    // Configure the button bindings
    configureDriveTrain();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {    
    m_tankdrivecommand.setControllerSupplier(() -> m_driverController.getY(Hand.kLeft), () -> m_driverController.getY(Hand.kRight));

    // Stabilize robot to drive straight with gyro when left bumper is held
    new JoystickButton(m_driverController, Button.kBumperLeft.value).whenHeld(new DriveStraightCommand(0.5, m_driveTrain.getHeading(), m_driveTrain));
  }

  private void configureDriveTrain() {
    m_driveTrain.setDefaultCommand(m_tankdrivecommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoDriveCommand;
  }
}
