package com.gladkikh.lexeme;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyAdapter extends FragmentStateAdapter {
    public MyAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String type = "Лексемы";
        switch (position) {
            case 0:
                type = "Лексемы";
                break;

            case 1:
                type = "Матрицы, определитель, транспонирование";
                break;

            case 2:
                type = "Жорданова форма матрицы";
        }
        return (ActionFragment.newInstance(type));
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}