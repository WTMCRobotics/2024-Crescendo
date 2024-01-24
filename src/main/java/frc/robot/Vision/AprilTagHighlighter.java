package frc.robot.Vision;

import edu.wpi.first.apriltag.AprilTagDetection;
import edu.wpi.first.apriltag.AprilTagPoseEstimator;
import edu.wpi.first.apriltag.AprilTagPoseEstimator.Config;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Vision.Vision.AprilTagLocation;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class AprilTagHighlighter {

    CvSource cvSource;
    Vision robotVision;
    AprilTagPoseEstimator estimator;
    Runnable estimatorAction;
    //We got this from here: https://github.com/PhotonVision/photonvision/blob/7f09f9e4f5b4237ef4b9dde7fdcb747115315659/photon-server/src/main/resources/calibration/lifecam480p.json#L10
    //Lower res stuff here: https://github.com/PhotonVision/photonvision/blob/7f09f9e4f5b4237ef4b9dde7fdcb747115315659/photon-server/src/main/resources/calibration/lifecam240p.json#L10
    static final Config aprilTagCameraConfig = new Config(
        0.1651,
        699.3778103158814,
        677.7161226393544,
        345.6059345433618,
        207.12741326228522
    );

    static final Scalar purple = new Scalar(255, 3, 251);

    public AprilTagHighlighter() {
        robotVision = new Vision();
        estimator = new AprilTagPoseEstimator(aprilTagCameraConfig);
        robotVision.setTargets(AprilTagLocation.UNDER_SPEAKER_CENTER, AprilTagLocation.UNDER_SPEAKER_OFFCENTER);
        robotVision.startVision();
        estimatorAction =
            () -> {
                for (AprilTagDetection detection : robotVision.getConfidentAprilTags()) {
                    drawAprilTagOutlines(robotVision.getMat(), detection, purple);
                    Transform3d estimation = estimator.estimate(detection);
                    SmartDashboard.putNumber(
                        "Tag " + detection.getId() + " Rotation",
                        Math.toDegrees(estimation.getRotation().getAngle())
                    );
                    SmartDashboard.putNumber("Tag " + detection.getId() + " X", estimation.getX());
                    SmartDashboard.putNumber("Tag " + detection.getId() + " Y", estimation.getY());
                    SmartDashboard.putNumber("Tag " + detection.getId() + " Z", estimation.getZ());
                    double distance = estimation.getTranslation().getDistance(new Translation3d());
                    SmartDashboard.putNumber("Tag " + detection.getId() + " Distance", distance);
                }
            };
    }

    public void doEveryFrame() {
        if (robotVision.getConfidentAprilTags().size() == 0) {
            return;
        }
        Thread thread = new Thread(estimatorAction);
        thread.setDaemon(true);
        thread.start();
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
