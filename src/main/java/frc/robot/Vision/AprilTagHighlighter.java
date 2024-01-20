package frc.robot.Vision;

import edu.wpi.first.apriltag.AprilTagDetection;
import edu.wpi.first.apriltag.AprilTagPoseEstimator;
import edu.wpi.first.apriltag.AprilTagPoseEstimator.Config;
import edu.wpi.first.cscore.CvSource;
import frc.robot.Vision.Vision.AprilTagLocation;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class AprilTagHighlighter {

    CvSource cvSource;
    Vision robotVision;
    AprilTagPoseEstimator est;
    static final Scalar purple = new Scalar(255, 3, 251);

    public AprilTagHighlighter() {
        robotVision = new Vision();
        // Config con = new Config(0.17, 699.3778103158814, 677.7161226393544, 345.6059345433618, 207.12741326228522);
        // est = new AprilTagPoseEstimator(con);

        // est.robotVision.setTargets(AprilTagLocation.ABOVE_AMP);
        robotVision.startVision();
    }

    public void doEveryFrame() {
        for (AprilTagDetection detection : robotVision.getConfidentAprilTags()) {
            drawAprilTagOutlines(robotVision.getMat(), detection, purple);
        }
    }

    private void drawAprilTagOutlines(Mat mat, AprilTagDetection tag, Scalar color) {
        Imgproc.line(
            mat,
            new Point(tag.getCornerX(0), tag.getCornerY(0)),
            new Point(tag.getCornerX(1), tag.getCornerY(1)),
            color,
            5
        );
        Imgproc.line(
            mat,
            new Point(tag.getCornerX(1), tag.getCornerY(1)),
            new Point(tag.getCornerX(2), tag.getCornerY(2)),
            color,
            5
        );
        Imgproc.line(
            mat,
            new Point(tag.getCornerX(2), tag.getCornerY(2)),
            new Point(tag.getCornerX(3), tag.getCornerY(3)),
            color,
            5
        );
        Imgproc.line(
            mat,
            new Point(tag.getCornerX(3), tag.getCornerY(3)),
            new Point(tag.getCornerX(0), tag.getCornerY(0)),
            color,
            5
        );

        Imgproc.circle(
            mat,
            new Point(tag.getCenterX(), tag.getCenterY()), // Circle the center point
            6,
            color
        );

        Imgproc.putText(
            mat,
            "ID: " + String.valueOf(tag.getId()),
            new Point(tag.getCornerX(2), tag.getCornerY(2)), // with ID // Place text by one of the corners
            Imgproc.FONT_HERSHEY_SIMPLEX,
            2,
            color,
            7
        );
    }
}
