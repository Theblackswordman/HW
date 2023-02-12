package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.Motortype;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Drivebase extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  //Left Gearbox
  CANSparkMax m_leftMaster = new CANSparkMax(1, Motortype.kBrushed);
  CANSparkMax m_leftMiddleSlave = new CANSparkMax(2, Motortype.kBrushed);
  CANSparkMax m_leftSlave = new CANSparkMax(3, Motortype.kBrushed);
  // Creating m_left object
  MotorControllerGroup m_left = new MotorControllerGroup(m_leftMaster, m_leftMiddleSlave, m_leftSlave);
  
  //Right Gearbox
  CANSparkMax m_rightMaster = new CANSparkMax(4, Motortype.kBrushed);
  CANSparkMax m_rightMiddleSlave = new CANSparkMax(5, Motortype.kBrushed);
  CANSparkMax m_rightSlave = new CANSparkMax(6, Motortype.kBrushed);

  // Creating m_right object
  MotorControllerGroup m_right = new MotorControllerGroup(m_rightMaster, m_rightMiddleSlave, m_rightSlave);
  // Differential drive class
  DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
  public double getAngle(){
    return m_gyro.getAngle();
  }
  public void arcadeDrive(double xSpeed, double zRotation){
    m_drive.arcadeDrive(xSpeed, zRotation);
  }
  
  public Drivebase() {
   
    // We want to invert our right motor because we don't want our robot to be running a circle
    m_right.setInverted(true);

    // Gyro
    AHRS m_gyro = new AHRS(Port.kMXP);
  }
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Angle", getAngle()); // This should be always be returning the angle to the WPI smartdashboard
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}