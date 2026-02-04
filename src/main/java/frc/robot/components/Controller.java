package frc.robot.components;

import frc.robot.data.StickPosition;
import edu.wpi.first.wpilibj.XboxController;

public class Controller {
    private final XboxController hardwareController;

    public Controller() {
        this.hardwareController = new XboxController(0);
    }

    public void poll() {}


    public StickPosition getLeftStickPosition() {
        return new StickPosition(hardwareController.getLeftX(), hardwareController.getLeftY());
    }


    public StickPosition getRightStickPosition() {
        return new StickPosition(hardwareController.getRightX(), hardwareController.getRightY());
    }
}
