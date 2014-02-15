package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SpeedController;

public class Storage implements SpeedController {

    double value;

    public double get() {
        return value;
    }

    public void set(double d, byte b) {
        set(d);
    }

    public void set(double d) {
        value = d;
    }

    public void disable() {
    }

    public void pidWrite(double d) {
        set(d);
    }
}
