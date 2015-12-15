
package net.inkyquill.equestria.flight.math;

public class FlightBean {
    private int power;
    private int duration;

    public FlightBean(int i, int j) {
        this.setDuration(i);
        this.setPower(j);
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

