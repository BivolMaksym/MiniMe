package com.application.minime;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionClient;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private TextView tvSteps;
    private ActivityRecognitionClient activityRecognitionClient;
    private int steps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSteps = findViewById(R.id.tvSteps);

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            startTracking();
        }
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACTIVITY_RECOGNITION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you receive empty arrays.
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startTracking();
            }
        }
    }

    private void startTracking() {
        activityRecognitionClient = ActivityRecognition.getClient(this);
        activityRecognitionClient.requestActivityUpdates(
                1000,
                getActivityDetectionPendingIntent()
        );
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(this, ActivityRecognitionService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void updateSteps(int newSteps) {
        steps += newSteps;
        tvSteps.setText("Steps: " + steps);
    }
}
