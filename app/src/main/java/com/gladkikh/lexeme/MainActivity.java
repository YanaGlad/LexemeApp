package com.gladkikh.lexeme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.gladkikh.lexeme.lexemeBackend.Archieve;
import com.gladkikh.lexeme.lexemeBackend.ErrorHandler;
import com.gladkikh.lexeme.lexemeBackend.Id_errors;
import com.gladkikh.lexeme.lexemeBackend.Lexeme;
import com.gladkikh.lexeme.lexemeBackend.Sentence;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private ExtendedFloatingActionButton button;
    private EditText editText, editTextVar;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_compute);
        editText = findViewById(R.id.editText);
        editTextVar = findViewById(R.id.editTextVar);
        textResult = findViewById(R.id.result);

        ViewPager2 pager = (ViewPager2) findViewById(R.id.viewPager2);
        FragmentStateAdapter pageAdapter = new MyAdapter(this);
        pager.setAdapter(pageAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x;
                new Archieve(getApplicationContext());
                Lexeme answer;
                String input = editText.getText().toString();
                Log.d("IPUT_MAIN", input);
                Sentence check = new Sentence(input);
                System.out.println(check.code());
                input = editTextVar.getText().toString();
                x = Double.parseDouble(input);
                check.substitute("x", x);
                answer = check.count();

                if (ErrorHandler.getError() == Id_errors.NON_ERROR) {
                    textResult.setText(String.valueOf(answer.get_value()));
                } else if (ErrorHandler.getError() == Id_errors.UNKNOWN_FUNCTION) {
                    textResult.setText("Неизвестная функция.");
                } else if (ErrorHandler.getError() == Id_errors.ERROR_SIGNS) {
                    textResult.setText("Какое-то из чисел записано с ошибкой: слишком много точек.");
                } else if (ErrorHandler.getError() == Id_errors.IMPOSSIBLE_COUNT) {
                    textResult.setText("Функцию в заданной точке невозможно вычислить.");
                } else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_BINARY_OPERATOR) {
                    textResult.setText("У какого-то из бинарных операторов отсутствует аргумент.");
                } else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_PRE_OPERATOR) {
                    textResult.setText("У какого-то из преоператоров отсутствует аргумент.");
                } else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_POST_OPERATOR) {
                    textResult.setText("У какого-то из постоператоров отсутствует аргумент. ");
                } else if (ErrorHandler.getError() == Id_errors.HAVE_OPEN_BRACKETS) {
                    textResult.setText("Не все скобки закрыты.");
                } else if (ErrorHandler.getError() == Id_errors.MORE_RIGHT_BRACKETS) {
                    textResult.setText("Закрыто больше скобок, чем открыто.");
                } else if (ErrorHandler.getError() == Id_errors.BAD_ARGUMENTS) {
                    textResult.setText("У какого-то из операторов не соответствует число аргументов.");
                } else {
                    textResult.setText("Неизвестная ошибка.");
                }


            }
        });
    }
}

