package com.gladkikh.lexeme;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.gladkikh.lexeme.Lexemes.ErrorHandler;
import com.gladkikh.lexeme.MRV.MRV;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton button;
    private EditText editText, editTextVar;
    private TextView textResult;
    private LinearLayoutCompat keysLayout, valuesLayout;
    private FloatingActionButton floatingActionButton, restartButton;
    private int N = 1;
    private ArrayList<String> keys = new ArrayList<>();
    private ArrayList<Double> values = new ArrayList<>();
    private ArrayList<EditText> editTextsKey = new ArrayList<>();
    private ArrayList<EditText> editTextsValue = new ArrayList<>();

    static int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_compute);
        button.setEnabled(false);

        editText = findViewById(R.id.editText);
        editTextVar = findViewById(R.id.editTextVar);
        textResult = findViewById(R.id.result);
        keysLayout = findViewById(R.id.keys);
        valuesLayout = findViewById(R.id.values);
        restartButton = findViewById(R.id.floatingRestart);

        floatingActionButton = findViewById(R.id.buttonFloat);

        ViewPager2 pager = (ViewPager2) findViewById(R.id.viewPager2);
        FragmentStateAdapter pageAdapter = new MyAdapter(this);
        pager.setAdapter(pageAdapter);


        floatingActionButton.setOnClickListener(v -> {
            ErrorHandler.set_default();

            N = Integer.parseInt(editTextVar.getText().toString());

            for (int i = 0; i < N; i++) {

                EditText editText = new EditText(getApplicationContext());
                editText.setTextColor(Color.WHITE);
                editText.setHint("x" + (i + 1));
                editText.setHintTextColor(Color.GRAY);
                editText.getBackground()
                        .setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                editText.setId(i + 10001);
                keysLayout.addView(editText);
                editTextsKey.add(editText);

                EditText val = new EditText(getApplicationContext());
                val.setTextColor(Color.WHITE);
                val.setHint("0.5");
                val.setHintTextColor(Color.GRAY);
                val.getBackground()
                        .setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                editText.setId(i + 200);
                valuesLayout.addView(val);
                editTextsValue.add(val);

            }

            button.setEnabled(true);
            floatingActionButton.setEnabled(false);
        });

        button.setOnClickListener(v -> {


            for (int i = 0; i < N; i++) {
                System.out.println("I is " + i);

                View view = valuesLayout;
                EditText editText = valuesLayout.findViewById(i + 10001);

                System.out.println(editText);

                keys.add(editTextsKey.get(i + step).getText().toString());
                System.out.println("ADD KEY " + editTextsKey.get(i + step).getText().toString());

                values.add(Double.parseDouble(editTextsValue.get(i + step).getText().toString()));
                System.out.println("ADD VALUE " + editTextsValue.get(i + step).getText().toString());

            }


            for (int i = 0; i < keys.size(); i++) {
                System.out.println(keys.get(i));
                System.out.println(values.get(i));
            }
            ErrorHandler.set_default();
            System.out.println("!!!!!!!!Error begin : " + ErrorHandler.get_begin_error() + " error end : " + ErrorHandler.get_end_error());
            System.out.println("ED string is... " + editText.getText().toString());
            for (int i = 0; i < keys.size(); i++) {
                System.out.println("Key " + i + " is " + keys.get(i));
            }
            for (int i = 0; i < values.size(); i++) {
                System.out.println("Value " + i + " is " + values.get(i));
            }

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
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step++;
                textResult.setText("");
                editText.setText("");
                editTextVar.setText("");
                keysLayout.removeAllViews();
                valuesLayout.removeAllViews();
                keys.clear();
                values.clear();
                ErrorHandler.set_default();
                restartButton.setVisibility(View.GONE);
                button.setEnabled(false);
                floatingActionButton.setEnabled(true);
            }
        });
    }

    public void redText(int error_begin, int error_end) {
        String full = editText.getText().toString();
        editText.setText("");
        Spannable word0;
        System.out.println("Error begin : " + error_begin + " error end : " + error_end);
        if (error_begin != 0) {
            word0 = new SpannableString(full.substring(0, error_begin));
            word0.setSpan(new ForegroundColorSpan(Color.WHITE), 0, word0.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

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
        wordTwo.setSpan(new ForegroundColorSpan(Color.WHITE), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.append(wordTwo);
    }
}

