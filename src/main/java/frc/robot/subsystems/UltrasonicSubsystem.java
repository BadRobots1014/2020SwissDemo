package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogInput;


public class UltrasonicSubsystem extends SubsystemBase {

    private static final int kUltrasonicPort = 0;
    //conversion coefficient
    private static final double toInches = 0.04139;

    private final AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);


    public UltrasonicSubsystem(){
        //constructor
    }

    public void updateDashboard(){
        SmartDashboard.putNumber("Ultrasonic Value", m_ultrasonic.getValue());
        SmartDashboard.putNumber("Distance", m_ultrasonic.getValue()*toInches);
    }

    public double getUltrasonicValue() 
    {
        return m_ultrasonic.getValue();
    }

    public double getDist_IN() 
    {
        return m_ultrasonic.getValue()*toInches;
    }

    @Override
    public void periodic() {
    // This method will be called once per scheduler run
        updateDashboard();
    }

}
