// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.SparkMaxAlternateEncoder.Type;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Elevator() {
    m_encoder.setPosition(0);
    m_elevatorMotor.setSoftLimit(SoftLimitDirection.kReverse, 0);
  }

  public CANSparkMax m_elevatorMotor = new CANSparkMax(3, MotorType.kBrushless);
  SparkMaxLimitSwitch m_limitSwitch = m_elevatorMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
  public PIDController m_pid = new PIDController(1, 0.1, 0.1);

  public RelativeEncoder m_encoder = m_elevatorMotor.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 8192);

  public double setHeightWithConstraint(double height){
    double output = m_pid.calculate(m_encoder.getPosition(), height);
    return output;
  }
  public boolean isLimitSwitchEnabled(){
    return m_limitSwitch.isLimitSwitchEnabled();
  }

  public double getEncoderPosition(){
    return m_encoder.getPosition();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("limit switch state", isLimitSwitchEnabled());
    SmartDashboard.putNumber("Elevator Height", getEncoderPosition());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
