package com.example.basicbootcompletedexample;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    private Button mRequestPermissionsButton;
    private Button mCreateFileButton;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestPermissionsButton = findViewById(R.id.request_permissions_button);
        mCreateFileButton = findViewById(R.id.create_file_button);

        mRequestPermissionsButton.setOnClickListener(v -> requestStoragePermissions());
        mCreateFileButton.setOnClickListener(v -> createTestFile());
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void requestStoragePermissions() {
        Intent mRequestPermissionsIntent = new Intent();
        mRequestPermissionsIntent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        Uri mPackageNameUri = Uri.fromParts("package", getPackageName(), null);
        mRequestPermissionsIntent.setData(mPackageNameUri);
        startActivity(mRequestPermissionsIntent);
    }

    private void createTestFile() {
        File testFile = new File("/sdcard/Omo.txt");

        try {
            if(testFile.exists()) {
                FileWriter mFileWriter = new FileWriter("/sdcard/Omo.txt");
                mFileWriter.write("You are Omo\n");
                mFileWriter.flush();
                mFileWriter.close();

            } else {
                testFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}