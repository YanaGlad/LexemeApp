package com.example.tinkoffsiriusapptry8.ui.gallery;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.gladkikh.lexeme.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;


public class MatrixFragment extends Fragment {

    private MatrixViewModel matrixViewModel;
    private LinearLayoutCompat matrixLayout;
    private ExtendedFloatingActionButton okBtn;
    private static int N = -1, M = -1;
    private AppCompatSpinner spinnerM, spinnerN;
    private ArrayList<EditText> matrixDots = new ArrayList<>();

    String[] dimens = {"1", "2", "3", "4", "5", "6", "7"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        matrixViewModel = ViewModelProviders.of(this).get(MatrixViewModel.class);
        View root = inflater.inflate(R.layout.fragment_matrix, container, false);
        spinnerN = root.findViewById(R.id.nSize);
        spinnerM = root.findViewById(R.id.mSize);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.my_spinner_item, dimens);
        adapter.setDropDownViewResource(R.layout.my_spinner_item);
        spinnerM.setAdapter(adapter);
        spinnerN.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListenerN = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String) parent.getItemAtPosition(position);
                N = Integer.parseInt(item);
                System.out.println("N is " + N);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        AdapterView.OnItemSelectedListener itemSelectedListenerM = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String) parent.getItemAtPosition(position);
                M = Integer.parseInt(item);
                System.out.println("M is " + M);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinnerN.setOnItemSelectedListener(itemSelectedListenerN);
        spinnerM.setOnItemSelectedListener(itemSelectedListenerM);

//        matrixViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        matrixLayout = root.findViewById(R.id.linearLayoutCompatMatrix);
        okBtn = root.findViewById(R.id.okFloat);

        okBtn.setOnClickListener(v -> {
            matrixLayout.removeAllViews();


            for (int i = 0; i < N; i++) {
                LinearLayoutCompat linearLayoutCompat = new LinearLayoutCompat(getContext());
                linearLayoutCompat.setOrientation(LinearLayoutCompat.HORIZONTAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayoutCompat.setLayoutParams(params);

                for (int j = 0; j < M; j++) {
                    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    EditText editText = new EditText(getContext());
                    editText.setHint("A" + (i+1) + (j+1));
                    editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    editText.setWidth(250 - M * 10);
                    editText.setHeight(250 - M * 10);

                    editText.setTextSize(20);
                    params2.weight = 1;
                    editText.setLayoutParams(params2);

                    linearLayoutCompat.addView(editText);
                }
                matrixLayout.addView(linearLayoutCompat);
            }
        });

        return root;
    }
}