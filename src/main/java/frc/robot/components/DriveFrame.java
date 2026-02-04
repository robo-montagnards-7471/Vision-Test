package frc.robot.components;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkFlex;
import frc.robot.data.StickPosition;

public class DriveFrame {
    private final DifferentialDrive differentialDrive;

    public DriveFrame() {
        var leftController = new PWMSparkFlex(1);
        leftController.setInverted(false);

        var rightController = new PWMSparkFlex(0);
        rightController.setInverted(true);

        differentialDrive = new DifferentialDrive(leftController, rightController);

    }

    public void poll() {}

    
    public void singleStickDrive(final StickPosition stickPosition) {
        // FIXME: Use PID
        differentialDrive.arcadeDrive(stickPosition.y(), stickPosition.x());
    }

    public void dualStickDrive( final StickPosition leftStickPosition, final StickPosition rightStickPosition ) {

        differentialDrive.arcadeDrive(leftStickPosition.y(), rightStickPosition.x());
        
        /* Tout est bon ici */
    }
}
