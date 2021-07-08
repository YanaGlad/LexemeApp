package com.gladkikh.lexeme;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ActionFragment extends Fragment {
    private String text;

    public static ActionFragment newInstance(String type) {

        ActionFragment fragment = new ActionFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    public ActionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text = getArguments() != null ? getArguments().getString("type") : "Лексемы";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_action, container, false);

        TextView pageHeader = (TextView) result.findViewById(R.id.displayText);
        pageHeader.setText(text);
        return result;
    }
}