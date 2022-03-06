package com.example.basicviewmodel.model;

import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private int mCounter;

    public int getmCounter() {
        return mCounter;
    }

    public void setmCounter(int mCounter) {
        this.mCounter = mCounter;
    }
}
