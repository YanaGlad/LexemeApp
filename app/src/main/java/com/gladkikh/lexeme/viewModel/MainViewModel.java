package com.gladkikh.lexeme.viewModel;

import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    MutableLiveData<Boolean> hasText = new MutableLiveData<>(false);
    private MutableLiveData<String> text = new MutableLiveData<>("");

    private static ArrayList<String>keys = new ArrayList<>();
    private static ArrayList<Double>values = new ArrayList<>();

    public void addKeyAndValue(String key, double value){
        keys.add(key);
        values.add(value);
    }



    public void setHasText(boolean hasText) {
       this.hasText.setValue(hasText);
    }

    public boolean hasText() {
        return hasText.getValue();
    }


}
