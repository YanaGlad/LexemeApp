package com.example.tinkoffsiriusapptry8.ui.home;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.gladkikh.lexeme.Lexemes.ErrorHandler;
import com.gladkikh.lexeme.MRV.MRV;
import com.gladkikh.lexeme.MyAdapter;
import com.gladkikh.lexeme.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class LexemeFragment extends Fragment {

    private LexemeViewModel lexemeViewModel;
    private ExtendedFloatingActionButton button;
    private EditText editText, editTextVar;
    private TextView textResult;
    private LinearLayoutCompat keysLayout, valuesLayout;
    private FloatingActionButton floatingActionButton, restartButton, aboutBtn;
    private int N = 1;
    private ArrayList<String> keys = new ArrayList<>();
    private ArrayList<Double> values = new ArrayList<>();
    private ArrayList<EditText> editTextsKey = new ArrayList<>();
    private ArrayList<EditText> editTextsValue = new ArrayList<>();
    // private AppCompatImageView aboutImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lexemeViewModel =
                ViewModelProviders.of(this).get(LexemeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lexeme, container, false);



        FloatingActionButton info = root.findViewById(R.id.infoButton);
//        info.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (aboutImage.getVisibility() == View.GONE)
//                    aboutImage.setVisibility(View.VISIBLE);
//                else aboutImage.setVisibility(View.GONE);
//            }
//        });

        button = root.findViewById(R.id.btn_compute);
        button.setEnabled(false);

        editText = root.findViewById(R.id.editText);
        editTextVar = root.findViewById(R.id.editTextVar);
        textResult = root.findViewById(R.id.result);
        keysLayout = root.findViewById(R.id.keys);
        valuesLayout = root.findViewById(R.id.values);
        restartButton = root.findViewById(R.id.floatingRestart);
        floatingActionButton = root.findViewById(R.id.buttonFloat);
        //   aboutImage = findViewById(R.id.imageAbout);

        ViewPager2 pager = (ViewPager2) root.findViewById(R.id.viewPager2);
        FragmentStateAdapter pageAdapter = new MyAdapter(getActivity());
        pager.setAdapter(pageAdapter);


        floatingActionButton.setOnClickListener(v -> {
            ErrorHandler.set_default();

            N = Integer.parseInt(editTextVar.getText().toString());

            for (int i = 0; i < N; i++) {

                EditText editText = new EditText(getContext());
                editText.setTextColor(Color.BLACK);
                editText.setHint("x" + (i + 1));
                editText.setHintTextColor(Color.GRAY);
                editText.getBackground()
                        .setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);

                keysLayout.addView(editText);
                editTextsKey.add(editText);

                EditText val = new EditText(getContext());
                val.setTextColor(Color.BLACK);
                val.setHint("0.5");
                val.setHintTextColor(Color.GRAY);
                val.getBackground()
                        .setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                valuesLayout.addView(val);
                editTextsValue.add(val);

            }

            button.setEnabled(true);
            floatingActionButton.setEnabled(false);
        });

        button.setOnClickListener(v -> {
            boolean filled = true;

            for (int i = 0; i < N; i++) {
                if(editTextsKey.get(i).getText().toString().equals("") || editTextsValue.get(i).getText().toString().equals("")){
                    filled = false;
                    break;
                }
                System.out.println(editText);

                keys.add(editTextsKey.get(i).getText().toString());

                values.add(Double.parseDouble(editTextsValue.get(i).getText().toString()));

            }

            if(filled) {
                String temp = "";
                try {
                    temp = "Result" + "\n" + String.valueOf(MRV.count_lexemes(editText.getText().toString(), keys, values));
                } catch (MRV.ARGUMENT_LIST_MISMATCH error) {

                    temp = "Списки аргументов не соответствуют.";
                } catch (MRV.UNKNOWN_FUNCTION error) {
                    temp = "Неизвестная функция ";
                    redText(error.getError_begin(), error.getError_end());
                } catch (MRV.ERROR_SIGNS error) {
                    temp = "Какое-то из чисел записано с ошибкой: слишком много точек." + "Место ошибки: " + Integer.toString(error.getError_begin());
                    redText(error.getError_begin(), error.getError_end());
                } catch (MRV.IMPOSSIBLE_COUNT error) {
                    temp = "Функцию в заданной точке невозможно вычислить.";
                    redText(error.getError_begin(), error.getError_end());
                } catch (MRV.MISS_ARGUMENT_BINARY_OPERATOR error) {
                    temp = "У какого-то из бинарных операторов отсутствует аргумент.";
                    redText(error.getError_begin(), error.getError_end());
                } catch (MRV.MISS_ARGUMENT_PRE_OPERATOR error) {
                    temp = "У какого-то из преоператоров отсутствует аргумент.";
                    redText(error.getError_begin(), error.getError_end());
                } catch (MRV.MISS_ARGUMENT_POST_OPERATOR error) {
                    temp = "У какого-то из постоператоров отсутствует аргумент." + "Ошибка от: " + error.getError_begin() + " до: " + error.getError_end();
                } catch (MRV.HAVE_OPEN_BRACKETS error) {
                    temp = "Есть незакрытая скобка.";
                    redText(error.getError_begin(), error.getError_end());
                } catch (MRV.MORE_RIGHT_BRACKETS error) {
                    temp = "Закрыто больше скобок, чем открыто.";
                } catch (MRV.BAD_ARGUMENTS error) {
                    temp = "У какого-то операторов недостаточно или слишком много аргументов.";
                    redText(error.getError_begin(), error.getError_end());
                } catch (MRV.UNKNOWN_ERROR error) {
                    temp = "Неизвестная ошибка.";
                }
                textResult.setText(temp);
                restartButton.setVisibility(View.VISIBLE);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // step++;
                textResult.setText("");
                editText.setText("");
                editTextVar.setText("");
                keysLayout.removeAllViews();
                valuesLayout.removeAllViews();
                editTextsKey.clear();
                editTextsValue.clear();
                keys.clear();
                values.clear();
                ErrorHandler.set_default();
                restartButton.setVisibility(View.GONE);
                button.setEnabled(false);
                floatingActionButton.setEnabled(true);
            }
        });

        return root;
    }

    public void redText(int error_begin, int error_end) {
        String full = editText.getText().toString();
        editText.setText("");
        Spannable word0;
        System.out.println("Error begin : " + error_begin + " error end : " + error_end);
        if (error_begin != 0) {
            word0 = new SpannableString(full.substring(0, error_begin));
            word0.setSpan(new ForegroundColorSpan(Color.BLACK), 0, word0.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        } else word0 = new SpannableString("");
        System.out.println("Word 0 : " + word0);
        editText.setText(word0);


        Spannable word = new SpannableString(full.substring(error_begin, error_end + 1));
        word.setSpan(new ForegroundColorSpan(Color.RED), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        System.out.println("Word1: " + word);
        editText.append(word);

        Spannable wordTwo = new SpannableString(full.substring(error_end + 1, full.length()));
        System.out.println("Error end: " + error_end + " len " + full.length());
        System.out.println("Word2 : " + wordTwo);
        wordTwo.setSpan(new ForegroundColorSpan(Color.BLACK), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.append(wordTwo);
    }

}