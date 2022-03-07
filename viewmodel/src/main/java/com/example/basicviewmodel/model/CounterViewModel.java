package com.example.basicviewmodel.model;

import androidx.lifecycle.ViewModel;

public class CounterViewModel extends ViewModel {
    private int mCounter;

    public int getCounter() {
        return mCounter;
    }

    public void setCounter(int mCounter) {
        this.mCounter = mCounter;
    }
}
