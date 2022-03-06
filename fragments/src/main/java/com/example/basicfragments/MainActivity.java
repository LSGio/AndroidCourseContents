package com.example.basicfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements HelloWorldFragment.OnSelectedItemListener, View.OnClickListener {

    private static final String TAG = "@@MainActivity";
    public static final String mHelloFragmentTag = "HELLO_WORLD_FRAGMENT";
    public static final String mDetailsFragmentTag = "DETAILS_FRAGMENT";

    private HelloWorldFragment mHelloWorldFragment;
    private DetailsFragment mDetailsFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Button mSwitchToDetailsFragmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: called");

        mFragmentManager = getSupportFragmentManager();
        mSwitchToDetailsFragmentButton = findViewById(R.id.switch_to_details_fragment_button);
        mSwitchToDetailsFragmentButton.setOnClickListener(this);

        // add state management
        if(savedInstanceState == null) {

            // put fragment inside frame dynamically
            // happens once

            mFragmentTransaction = mFragmentManager.beginTransaction();
            mHelloWorldFragment = new HelloWorldFragment();
            mFragmentTransaction.add(R.id.main_frame, mHelloWorldFragment, mHelloFragmentTag);
            mFragmentTransaction.commit();
        } else {

            // TODO : Understand what sets the fragment inside the frame
            // get fragment handle
            // happens every time except for the first time

            mHelloWorldFragment = (HelloWorldFragment) mFragmentManager.findFragmentByTag(mHelloFragmentTag);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: called");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: called");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");
    }

    @Override
    public void onUpdateSelected(String message) {
        if(mHelloWorldFragment != null) {
            mHelloWorldFragment.changeText(message);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: called");

        if(v.getId() == R.id.switch_to_details_fragment_button) {

            // TODO: Understand why exit animation not working
            // TODO: Understand when to create new Fragment Instance

            mDetailsFragment = new DetailsFragment();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            mFragmentTransaction.replace(R.id.main_frame, mDetailsFragment, mDetailsFragmentTag);
            mFragmentTransaction.addToBackStack("BACK_FROM_DETAILS_FRAGMENT");
            mFragmentTransaction.commit();

        }

    }
}