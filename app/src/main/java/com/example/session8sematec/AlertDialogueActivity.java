package com.example.session8sematec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlertDialogueActivity extends AppCompatActivity {
Button btnCameraPermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialogue);


        btnCameraPermission = findViewById(R.id.btnCameraPermission);

        btnCameraPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AlertDialogueActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                ) {

                    ActivityCompat.requestPermissions(AlertDialogueActivity.this,
                            new String[]{Manifest.permission.CAMERA},1500);



                }
            }
        });
    }
}
