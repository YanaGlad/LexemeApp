package com.gladkikh.lexeme;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton button;
    private EditText editText, editTextVar;
    private TextView textResult;
    private LinearLayoutCompat keysLayout, valuesLayout;
    private FloatingActionButton floatingActionButton;
    private int N = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_compute);
        editText = findViewById(R.id.editText);
        editTextVar = findViewById(R.id.editTextVar);
        textResult = findViewById(R.id.result);
        keysLayout = findViewById(R.id.keys);
        valuesLayout = findViewById(R.id.values);

        floatingActionButton = findViewById(R.id.buttonFloat);

        ViewPager2 pager = (ViewPager2) findViewById(R.id.viewPager2);
        FragmentStateAdapter pageAdapter = new MyAdapter(this);
        pager.setAdapter(pageAdapter);

        ArrayList<String> keys = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>();
        ArrayList<EditText> editTexts = new ArrayList<>();

        floatingActionButton.setOnClickListener(v -> {
            N = Integer.parseInt(editTextVar.getText().toString());

            for (int i = 0; i < N; i++) {

                EditText editText = new EditText(getApplicationContext());
                editText.setTextColor(Color.WHITE);
                editText.setHint("x" + (i+1));
                editText.setHintTextColor(Color.GRAY);
                editText.getBackground()
                        .setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                keysLayout.addView(editText);
                editTexts.add(editText);

                EditText val = new EditText(getApplicationContext());
                val.setTextColor(Color.WHITE);
                val.setHint("0.5");
                val.setHintTextColor(Color.GRAY);
                val.getBackground()
                        .setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                valuesLayout.addView(val);
                editTexts.add(val);

            }


        });

        button.setOnClickListener(v -> {

            for (int i = 0; i < N * 2; i++)
                if (i % 2 == 0)
                    keys.add(editTexts.get(i).getText().toString());
                else
                    values.add(Double.parseDouble(editTexts.get(i).getText().toString()));


            for (int i = 0; i < keys.size(); i++) {
                System.out.println(keys.get(i));
                System.out.println(values.get(i));
            }

            try {
                textResult.setText("Result" + "\n" + String.valueOf(MRV.count_lexemes(editText.getText().toString(), keys, values)));
            } catch (MRV_ARGUMENT_LIST_MISMATCH error) {
                textResult.setText("Списки аргументов не соответствуют.");
            } catch (MRV_UNKNOWN_FUNCTION error) {
                textResult.setText("Неизвестная функция от : " + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ()));
            } catch (MRV_ERROR_SIGNS error) {
                textResult.setText("Какое-то из чисел записано с ошибкой: слишком много точек." + "Место ошибки: " + Integer.toString (error.getError_begin ()));
            } catch (MRV_IMPOSSIBLE_COUNT error) {
                textResult.setText("Функцию в заданной точке невозможно вычислить. Начало функции: "
                        + Integer.toString (error.getError_begin ()) + " конец: " + Integer.toString (error.getError_end ()));
            } catch (MRV_MISS_ARGUMENT_BINARY_OPERATOR error) {
                textResult.setText("У какого-то из бинарных операторов отсутствует аргумент." + "Ошибка от: "
                        + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ()));
            } catch (MRV_MISS_ARGUMENT_PRE_OPERATOR error) {
                textResult.setText("У какого-то из преоператоров отсутствует аргумент." + "Ошибка от: "
                        + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ()));
            } catch (MRV_MISS_ARGUMENT_POST_OPERATOR error) {
                textResult.setText("У какого-то из постоператоров отсутствует аргумент." + "Ошибка от: " +
                        Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ()));
            } catch (MRV_HAVE_OPEN_BRACKETS error) {
                textResult.setText("Есть незакрытая скобка." + "Не закрыта: " + Integer.toString (error.getError_begin ()));
            } catch (MRV_MORE_RIGHT_BRACKETS error) {
                textResult.setText("Закрыто больше скобок, чем открыто.");
            } catch (MRV_BAD_ARGUMENTS error) {
                textResult.setText("У какого-то операторов недостаточно или слишком много аргументов." + "Ошибка от: "
                        + Integer.toString (error.getError_begin ()) + " до: " + Integer.toString (error.getError_end ()));
            } catch (MRV_UNKNOWN_ERROR error) {
                textResult.setText("Неизвестная ошибка.");
            }

         });
    }
}

