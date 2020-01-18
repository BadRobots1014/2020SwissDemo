/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.DriveForTimeCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.DriveStraightCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.util.GyroProvider;
import frc.robot.util.TalonSRXProvider;
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
  private final DriveTrainSubsystem m_driveTrain;
  private final TeleopDriveCommand m_teleopdrivecommand;
  private final DriveForTimeCommand m_autoDriveCommand;
  private final XboxController m_driverController = new XboxController(OIConstants.kPrimaryDriverController);

  private final GyroProvider m_gyroProvider;
  private final TalonSRXProvider m_speedControllerProvider;

  private final Climber m_climber;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    boolean isReal = Robot.isReal();
    m_gyroProvider = new GyroProvider(isReal);
    m_speedControllerProvider = new TalonSRXProvider(isReal);
    
    // Pass in the speed controller provider to abstract the underlying speed controller type so this is more reusable
    m_driveTrain = new DriveTrainSubsystem(m_speedControllerProvider);
    m_climber = new Climber();
    m_teleopdrivecommand = new TeleopDriveCommand(m_driveTrain);
    // This is not currently useful, but does technically work.
    m_autoDriveCommand = new DriveForTimeCommand(m_driveTrain, 1.0, 0.5);

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
    DoubleSupplier leftYJoystick = () -> -m_driverController.getY(Hand.kLeft);
    DoubleSupplier rightJoystick = () -> m_driverController.getX(Hand.kRight);
    m_teleopdrivecommand.setControllerSupplier(leftYJoystick, rightJoystick);

    // Stabilize robot to drive straight with gyro when left bumper is held
    new JoystickButton(m_driverController, Button.kBumperLeft.value).whenHeld(new DriveStraightCommand(leftYJoystick, m_gyroProvider, m_driveTrain));

    new JoystickButton(m_driverController, Button.kBumperRight.value)
    .whenPressed(() -> m_driveTrain.setMaxOutput(0.5))
    .whenReleased(() -> m_driveTrain.setMaxOutput(1));

    new JoystickButton(m_driverController, Button.kB.value)
    .whenPressed(() -> m_climber.setDoubleSolenoid(true))
    .whenReleased(() -> m_climber.setDoubleSolenoid(false));
  }

  private void configureDriveTrain() {
    m_driveTrain.setDefaultCommand(m_teleopdrivecommand);
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
