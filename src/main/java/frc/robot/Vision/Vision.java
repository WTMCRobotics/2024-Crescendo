package frc.robot.vision;

import edu.wpi.first.apriltag.AprilTagDetection;
import edu.wpi.first.apriltag.AprilTagDetector;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Vision {

    UsbCamera camera;
    CvSink cvSink;
    Thread visionThread;
    AprilTagDetector detector;
    Mat mat;
    CvSource cvSource;

    public CvSource getCvSource() {
        return cvSource;
    }

    public Mat getMat() {
        return mat;
    }

    Mat greyMat;
    Map<Integer, Integer> confidenceMap = new HashMap<>();
    List<Integer> targetTags = new ArrayList<>();
    List<AprilTagDetection> confidentAprilTags = new ArrayList<>();

    public Vision() {
        // AxisCamera d = CameraServer.startAutomaticCapture("Front Cam", 0);;
        camera = CameraServer.startAutomaticCapture("Front Cam", 0);
        camera.setFPS(15);

        // Set the resolution
        camera.setResolution(Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);

        // Get a CvSink. This will capture Mats from the camera
        cvSink = CameraServer.getVideo();

        //cvSource = CameraServer.putVideo("Rectangle", Constants.CAMERA_WIDTH, Constants.CAMERA_HEIGHT);

        mat = new Mat();
        greyMat = new Mat();

        detector = new AprilTagDetector();
        detector.addFamily("tag36h11", Constants.CAMERA_BIT_CORRECTION_AMOUNT);
    }

    public void startVision() {
        visionThread =
            new Thread(() -> {
                while (!Thread.interrupted()) {
                    if (cvSink.grabFrame(mat) == 0) {
                        //cvSource.notifyError(cvSink.getError());
                        //error happened
                        continue;
                    }
                    Imgproc.cvtColor(mat, greyMat, Imgproc.COLOR_RGB2GRAY);

                    AprilTagDetection[] detectedAprilTags = detector.detect(greyMat);
                    updateConfidence(detectedAprilTags);
                    //cvSource.putFrame(mat);
                }
            });
        visionThread.setDaemon(true);
        visionThread.start();
    }

    public List<AprilTagDetection> getConfidentAprilTags() {
        return new ArrayList<AprilTagDetection>(confidentAprilTags);
    }

    private void updateConfidence(AprilTagDetection[] detections) {
        List<Integer> detected = new ArrayList<>();
        for (AprilTagDetection tag : detections) {
            int id = tag.getId();
            if (!targetTags.contains(id)) {
                continue;
            }
            changeTagConfidence(id, 1);
            detected.add(id);
        }
        for (Integer ID : confidenceMap.keySet()) {
            if (!detected.contains(ID)) {
                changeTagConfidence(ID, -1);
            }
        }
        confidentAprilTags.clear();
        for (AprilTagDetection detection : detections) {
            if (
                confidenceMap.get(detection.getId()) != null &&
                confidenceMap.get(detection.getId()) >= Constants.APRIL_TAG_CONFIDENCE_FRAMES
            ) {
                confidentAprilTags.add(detection);
            }
        }
    }

    private void changeTagConfidence(int id, int change) {
        if (!confidenceMap.containsKey(id)) {
            confidenceMap.put(id, 0);
        }
        //Don't set confidence below 0
        if (confidenceMap.get(id) + change < 0) {
            confidenceMap.put(id, 0);
            return;
        }
        if (confidenceMap.get(id) + change > Constants.APRIL_TAG_CONFIDENCE_FRAMES + 2) {
            confidenceMap.put(id, Constants.APRIL_TAG_CONFIDENCE_FRAMES + 2);
            return;
        }
        confidenceMap.put(id, confidenceMap.get(id) + change);
    }

    public boolean isConfidentForAprilTag(int num) {
        return confidenceMap.get(num) >= Constants.APRIL_TAG_CONFIDENCE_FRAMES;
    }

    public void setTargets(AprilTagLocation... targetTag) {
        targetTags.clear();
        for (AprilTagLocation tag : targetTag) {
            targetTags.add(tag.getId());
            System.out.println("Targeting tag ID " + tag.getId());
        }
    }

    enum AprilTagLocation {
        ABOVE_AMP(5, 6),
        UNDER_SPEAKER_CENTER(4, 7),
        UNDER_SPEAKER_OFFCENTER(3, 8),
        TERMINAL_DRIVER_SIDE(9, 2),
        TERMINAL_MIDDLE_SIDE(10, 1),
        STAGE_FACING_AMP(12, 15),
        STAGE_FACING_MIDDLE(13, 14),
        STAGE_FACING_TERMINAL(11, 16);

        private final int redId;
        private final int blueId;

        private AprilTagLocation(int redId, int blueId) {
            this.redId = redId;
            this.blueId = blueId;
        }

        public int getId() {
            DriverStation.Alliance color = DriverStation.getAlliance().get();
            return color == DriverStation.Alliance.Blue ? blueId : redId;
        }
    }
}
