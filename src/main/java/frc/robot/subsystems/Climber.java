/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  //private final Solenoid m_solenoid = new Solenoid(0);
  //private final DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(1, 2);
  /**
   * Creates a new Climber.
   */
  public Climber() {

  }

  public void setSingleSolenoid(boolean climb) {
    //m_solenoid.set(climb);
  }

  /*public void setDoubleSolenoid(boolean climb) {
    if (climb) {
      m_doubleSolenoid.set(Value.kForward);
    } else {
      m_doubleSolenoid.set(Value.kReverse);
    }
  }*/

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
