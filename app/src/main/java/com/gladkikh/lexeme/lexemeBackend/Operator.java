package com.gladkikh.lexeme.lexemeBackend;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.*;
import java.util.ArrayList;

public class Operator {
    protected Id_lexemes id;
    protected Function func = new F_Sin();
    protected int left_argue = 0;
    protected int right_argue = 0;
    protected int priority = 0;
    protected ArrayList<String> decode_base = new ArrayList<>();
    private Context context;

    public Operator(Context context){
        this.context = context;
    }

    public int get_id() {
        return Id_lexemes.getId(id);
    }

    public int get_left_argue() {
        return left_argue;
    }

    public int get_right_argue() {
        return right_argue;
    }

    public int is_it(String input) {
        int max_code = 0;
        for (int i = 0; i < decode_base.size(); i++) {
            if (decode_base.get(i).length() == input.length() && decode_base.get(i).equals(input))
                return 2;
            else if (decode_base.get(i).length() >= input.length() && decode_base.get(i).startsWith(input)) {
                max_code = 1;
            }
        }
        return max_code;
    }

    public int get_priority() {
        return priority;
    }

    public double count(ArrayList<Double> args) {
        return func.count(args);
    }

    public boolean check(ArrayList<Double> args) {
        return func.check(args);
    }

    //id, количество аргументов слева, количество аргументов справа, приоритет, количество кодовых слов, кодовые слова
    Operator(Function func, Id_lexemes id, int left_argue, int right_argue, int priority, ArrayList<String> list_of_words) {
        this.id = id;
        this.left_argue = left_argue;
        this.right_argue = right_argue;
        this.priority = priority;
        decode_base.addAll(list_of_words);
    }

    Operator() {
    }

    public String code() {
        return decode_base.get(0);
    }


//    protected void load_decode_base(String src) {
//        if (src.equals("")) return;
//        else {
//            try {
//                FileReader localisation_file = new FileReader(src);
//                BufferedReader scan = new BufferedReader(localisation_file);
//                String line;
//                while ((line = scan.readLine()) != null) {
//                    if (line.equals("") != true) decode_base.add(line);
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    protected void load_decode_base(String src) {
        if (src.equals("")) return;
        else {
            try {
                AssetManager assetManager = context.getAssets();
                InputStreamReader istream = new InputStreamReader(assetManager.open(src));
                BufferedReader in = new BufferedReader(istream);
                String line;
                while ((line = in.readLine()) != null) {
                    if (!line.equals("")) decode_base.add(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

class Argument extends Operator {
    Argument(Context context) {
        super(context);
        id = Id_lexemes.ARGUMENT;
    }
}

class Valuable extends Operator {
    Valuable(Context context) {
        super(context);
        id = Id_lexemes.VARIABLE;
        load_decode_base("valuable.txt");
    }
}

class Left_br extends Operator {
    Left_br(Context context) {
        super(context);
        id = Id_lexemes.LEFT_BR;
        decode_base.add("(");
    }
}

class Right_br extends Operator {
    Right_br(Context context) {
        super(context);
        id = Id_lexemes.RIGHT_BR;
        decode_base.add(")");
    }
}

class Comma extends Operator {
    Comma(Context context) {
        super(context);
        id = Id_lexemes.COMMA;
        decode_base.add(",");
    }
}

class Abs extends Operator {
    Abs(Context context) {
        super(context);
        load_decode_base("abs.txt");
        id = Id_lexemes.ABS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Abs();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Sin extends Operator {
    Sin(Context context) {
        super(context);
        load_decode_base("sin.txt");
        id = Id_lexemes.SIN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Sin();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Cos extends Operator {
    Cos(Context context) {
        super(context);
        load_decode_base("cos.txt");
        id = Id_lexemes.COS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Cos();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Tg extends Operator {
    Tg(Context context) {
        super(context);
        load_decode_base("tg.txt");
        id = Id_lexemes.TG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Tg();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Ctg extends Operator {
    Ctg(Context context) {
        super(context);
        load_decode_base("ctg.txt");
        id = Id_lexemes.CTG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Ctg();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Arcsin extends Operator {
    Arcsin(Context context) {
        super(context);
        load_decode_base("arcsin.txt");
        id = Id_lexemes.ARCSIN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arcsin();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Arccos extends Operator {
    Arccos(Context context) {
        super(context);
        load_decode_base("arccos.txt");
        id = Id_lexemes.ARCCOS;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arccos();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Arctg extends Operator {
    Arctg(Context context) {
        super(context);
        load_decode_base("arctg.txt");
        id = Id_lexemes.ARCTG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arctg();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Arcctg extends Operator {
    Arcctg(Context context) {
        super(context);
        load_decode_base("arcctg.txt");
        id = Id_lexemes.ARCCTG;
        left_argue = 0;
        right_argue = 1;
        func = new F_Arcctg();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Exp extends Operator {
    Exp(Context context) {
        super(context);
        load_decode_base("exp.txt");
        id = Id_lexemes.EXP;
        left_argue = 0;
        right_argue = 1;
        func = new F_Exp();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Ln extends Operator {
    Ln(Context context) {
        super(context);
        load_decode_base("ln.txt");
        id = Id_lexemes.LN;
        left_argue = 0;
        right_argue = 1;
        func = new F_Ln();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Log extends Operator {
    Log(Context context) {
        super(context);
        load_decode_base("log.txt");
        id = Id_lexemes.LOG;
        left_argue = 0;
        right_argue = 2;
        func = new F_Log();
        priority = Priority_operators.getId(Priority_operators.PRIOR_FUNC);
    }
}

class Pow extends Operator {
    Pow(Context context) {
        super(context);
        load_decode_base("pow.txt");
        id = Id_lexemes.POW;
        left_argue = 1;
        right_argue = 1;
        func = new F_Pow();
        priority = Priority_operators.getId(Priority_operators.PRIOR_POW);
    }
}

class Mult extends Operator {
    Mult(Context context) {
        super(context);
        load_decode_base("mult.txt");
        id = Id_lexemes.MULT;
        left_argue = 1;
        right_argue = 1;
        func = new F_Mult();
        priority = Priority_operators.getId(Priority_operators.PRIOR_MULT_DIV);
    }
}

class Div extends Operator {
    Div(Context context) {
        super(context);
        load_decode_base("div.txt");
        id = Id_lexemes.DIV;
        left_argue = 1;
        right_argue = 1;
        func = new F_Div();
        priority = Priority_operators.getId(Priority_operators.PRIOR_MULT_DIV);
    }
}

class Plus extends Operator {
    Plus(Context context) {
        super(context);
        load_decode_base("plus.txt");
        id = Id_lexemes.PLUS;
        left_argue = 1;
        right_argue = 1;
        func = new F_Plus();
        priority = Priority_operators.getId(Priority_operators.PRIOR_PLUS_MINUS);
    }
}

class Minus extends Operator {
    Minus(Context context) {
        super(context);
        load_decode_base("minus.txt");
        id = Id_lexemes.MINUS;
        left_argue = 1;
        right_argue = 1;
        func = new F_Minus();
        priority = Priority_operators.getId(Priority_operators.PRIOR_PLUS_MINUS);
    }
}