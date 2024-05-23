package com.application.minime;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class ActivityRecognitionService extends IntentService {

    private static final String TAG = "ActivityRecognitionService";

    public ActivityRecognitionService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getProbableActivities());
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for (DetectedActivity activity : probableActivities) {
            switch (activity.getType()) {
                case DetectedActivity.WALKING:
                case DetectedActivity.RUNNING:
                    Log.d(TAG, "Detected activity: " + activity.getType() + ", Confidence: " + activity.getConfidence());
                    if (activity.getConfidence() >= 75) {
                        // Assuming 1 activity detection = 1 step for simplicity
                        MainActivity mainActivity = new MainActivity();
                        mainActivity.updateSteps(1);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
