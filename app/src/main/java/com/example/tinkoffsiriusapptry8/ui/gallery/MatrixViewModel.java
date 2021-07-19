package com.example.tinkoffsiriusapptry8.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MatrixViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MatrixViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("МАТРИЦЫ И ОПЕРАЦИИ ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}