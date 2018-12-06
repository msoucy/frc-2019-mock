package frc.team166.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import frc.team166.chopshoplib.commands.SubsystemCommand;
import frc.team166.robot.Robot;

public class Drive extends Subsystem {

    WPI_VictorSPX leftMotor = new WPI_VictorSPX(4);
    WPI_VictorSPX rightMotor = new WPI_VictorSPX(5);
    DifferentialDrive controller = new DifferentialDrive(leftMotor, rightMotor);

    public Drive() {

    }

    public void initDefaultCommand() {
        setDefaultCommand(xboxDrive());
    }

    public Command xboxDrive() {
        return new SubsystemCommand("XBox Drive", this) {
            @Override
            public void execute() {
                double forward = Robot.m_oi.driver.getTriggerAxis(Hand.kRight);
                double reverse = Robot.m_oi.driver.getTriggerAxis(Hand.kLeft);
                double rotation = Robot.m_oi.driver.getX(Hand.kLeft);
                controller.arcadeDrive(forward - reverse, rotation);
            }

            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }
}
