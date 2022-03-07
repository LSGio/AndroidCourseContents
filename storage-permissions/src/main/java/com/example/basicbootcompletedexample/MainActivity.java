package com.example.basicbootcompletedexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    private Button mRequestPermissionsButton;
    private Button mCreateTestFileButton;

    private static final String TAG = "@@MainActivity";
    public static final int REQUEST_STORAGE_PERMISSIONS = 4892;
    public static final int REQUEST_STORAGE_PERMISSIONS_LEGACY = 4893;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestPermissionsButton = findViewById(R.id.request_permissions_button);
        mCreateTestFileButton = findViewById(R.id.create_file_button);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            mRequestPermissionsButton.setOnClickListener(v -> requestStoragePermissions());
        } else {
            mRequestPermissionsButton.setOnClickListener(v -> requestStoragePermissionsLegacy());
        }
        mCreateTestFileButton.setOnClickListener(v -> createTestFile());
    }

    private void requestStoragePermissionsLegacy() {
        String[] mPermissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        requestPermissions(mPermissions, REQUEST_STORAGE_PERMISSIONS_LEGACY);
    }

    private void requestStoragePermissions() {
        Intent mRequestPermissionsIntent = new Intent();
        mRequestPermissionsIntent.setAction(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
        Uri mPackageNameUri = Uri.fromParts("package", getPackageName(), null);
        mRequestPermissionsIntent.setData(mPackageNameUri);
        startActivityForResult(mRequestPermissionsIntent, REQUEST_STORAGE_PERMISSIONS);
    }

    private void createTestFile() {
        String mSDCardPath = Environment.getExternalStorageDirectory().getPath();
        String mFileNameUri = "PermissionTest.txt";
        String mAbsolutePathToFile = mSDCardPath + File.separator + mFileNameUri;
        File testFile = new File(mAbsolutePathToFile);

        try {
            boolean fileExists;
            if(!testFile.exists()) {
                fileExists = testFile.createNewFile();
            } else {
                fileExists = true;
            }
            if(fileExists) {
                FileWriter mFileWriter = new FileWriter(mAbsolutePathToFile);
                mFileWriter.write("You are Omo\n");
                mFileWriter.flush();
                mFileWriter.close();
            } else {
                Toast.makeText(this, "Failed creating file", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int mPermissionGranted = PackageManager.PERMISSION_GRANTED;
        if(requestCode == REQUEST_STORAGE_PERMISSIONS_LEGACY) {
            if(grantResults[0] == mPermissionGranted && grantResults[1] == mPermissionGranted) {
                mCreateTestFileButton.setEnabled(true);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_STORAGE_PERMISSIONS) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if(Environment.isExternalStorageManager()) {
                    mCreateTestFileButton.setEnabled(true);
                }
            }
        }
    }
}