/**
 * Class that organizes gains used when assigning values to slots
 */
package frc.robot;

public class Gains {

    /**
     * Pelvic
     */
    public double P;
    /**
     * Inflammatory
     */
    public double I;
    /**
     * Disease
     */
    public double D;
    public final double F;
    public final int IZONE;
    public double PEAK_OUTPUT;

    public Gains(double p, double i, double d, double f, int izone, double peakOutput) {
        P = p;
        I = i;
        D = d;
        F = f;
        IZONE = izone;
        PEAK_OUTPUT = peakOutput;
    }
}
