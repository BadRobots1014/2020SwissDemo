/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Add your docs here.
 */
public class TalonSRXProvider {
    private boolean m_isReal = false;
    private static int simCounter = 0;

    public TalonSRXProvider(boolean isReal) {
        m_isReal = isReal;    
    }
    public SpeedController getSpeedController(int deviceId) {
        if (m_isReal) {
            return new WPI_TalonSRX(deviceId);
        } else {
            // Turn CAN deviceId into a dummy PWM channel
            if (deviceId > 20) {
                deviceId = simCounter++;
            }
            return new PWMTalonSRX(deviceId);
        }
    }
}
