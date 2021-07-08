package com.gladkikh.lexeme.lexemeBackend;

 import android.content.Context;

 import java.util.ArrayList;

//token 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
public class Archieve {
    static ArrayList<Operator> base = new ArrayList<>();

    public Archieve(Context context) {
        int n = Id_lexemes.getId(Id_lexemes.NUMBER_OPERATORS);
        for (int i = 0; i < n; i++) {
            base.add(new Sin(context));
        }
        add_operator(new Argument(context));
        add_operator(new Valuable(context));
        add_operator(new Left_br(context));
        add_operator(new Right_br(context));
        add_operator(new Comma(context));
        add_operator(new Abs(context));
        add_operator(new Sin(context));
        add_operator(new Cos(context));
        add_operator(new Tg(context));
        add_operator(new Ctg(context));
        add_operator(new Arcsin(context));
        add_operator(new Arccos(context));
        add_operator(new Arctg(context));
        add_operator(new Arcctg(context));
        add_operator(new Exp(context));
        add_operator(new Ln(context));
        add_operator(new Log(context));
        add_operator(new Pow(context));
        add_operator(new Mult(context));
        add_operator(new Div(context));
        add_operator(new Plus(context));
        add_operator(new Minus(context));
    }

    public void add_operator(Operator A) {
        int n = A.get_id();
        base.set(n, A);
    }

    static ArrayList<Integer> decode(String input, ArrayList<Integer> verif) {
        ArrayList<Integer> answer = new ArrayList<>();
        if (verif == null || verif.size() == 0) {
            for (int i = 0; i < Id_lexemes.getId(Id_lexemes.NUMBER_OPERATORS); i++) {
                int check = base.get(i).is_it(input);
                if (check == 2) {
                    answer.add(i);
                } else if (check == 1) {
                    answer.add(-i);
                }
            }
        } else {
            for (int i = 0; i < verif.size(); i++) {
                int b = Math.abs(verif.get(i));
                int check = base.get(b).is_it(input);
                if (check == 2) {
                    answer.add(b);
                } else if (check == 1) {
                    answer.add(-b);
                }
            }
        }
        return answer;
    }

    static String code(Id_lexemes id) {
        return base.get(Id_lexemes.getId(id)).code();
    }

    static int get_priority(Id_lexemes id) {
        if (Id_lexemes.getId(id) <= base.size())
            return base.get(Id_lexemes.getId(id)).get_priority();
        else return 0;
    }

    static int get_left_argue(Id_lexemes id) {
        if (Id_lexemes.getId(id) <= base.size())
            return base.get(Id_lexemes.getId(id)).get_left_argue();
        else return 0;
    }

    static int get_right_argue(Id_lexemes id) {
        if (Id_lexemes.getId(id) <= base.size())
            return base.get(Id_lexemes.getId(id)).get_right_argue();
        else return 0;
    }

    static boolean check_countable(Id_lexemes id, ArrayList<Double> argues) {

        return base.get(Id_lexemes.getId(id)).check(argues);
    }

    static double count(Id_lexemes id, ArrayList<Double> argues) {
        return base.get(Id_lexemes.getId(id)).count(argues);

    }
}
