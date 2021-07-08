package com.gladkikh.lexeme.lexemeBackend;

import java.util.ArrayList;

public class Lexeme {
    protected String key;
    protected Id_lexemes id;
    protected ArrayList<Double> values = new ArrayList<>();

    public Lexeme() {
        values.add(3.0);
    }

    public Lexeme(Id_lexemes id, ArrayList<Double> values) {
        this.values = values;
        this.id = id;
        key = code();
    }

    public Lexeme(Id_lexemes id) {
        this.id = id;
        key = code();
    }

    public Lexeme(String key) {
        id = Id_lexemes.VARIABLE;
        this.key = key;
    }

    public Id_lexemes get_id() {
        return id;
    }

    public double get_value(int i) {
        return values.get(i);
    }

    public double get_value() {
        return values.get(0);
    }

    public ArrayList<Double> get_values() {
        return values;
    }

    private String code() {
        StringBuilder A = new StringBuilder();
        if (id == Id_lexemes.ARGUMENT) {
            if (values.size() > 1) {
                A.append("(");
                A.append(values.get(0));
                for (int i = 1; i < values.size(); i++) {
                    A.append(",");
                    A.append(Double.toString(values.get(i)));
                }
            } else {
                A.append(Double.toString(values.get(0)));
            }
        } else if (id == Id_lexemes.LEFT_BR) A.append("(");
        else if (id == Id_lexemes.RIGHT_BR) A.append(")");
        else if (id == Id_lexemes.VARIABLE) A.append("x");
        else if (id == Id_lexemes.END) ;
        else {
            A = new StringBuilder(Archieve.code(id));
        }
        return A.toString();
    }

    public String getKey() {
        return key;
    }
}












