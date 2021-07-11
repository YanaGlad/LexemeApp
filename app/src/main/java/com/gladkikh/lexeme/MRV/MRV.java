package com.gladkikh.lexeme.MRV;

import java.util.ArrayList;

public class MRV {
    public static double count_lexemes( String input, ArrayList<String> keys, ArrayList<Double> values ) throws ARGUMENT_LIST_MISMATCH, UNKNOWN_FUNCTION, ERROR_SIGNS, IMPOSSIBLE_COUNT, MISS_ARGUMENT_BINARY_OPERATOR, MISS_ARGUMENT_PRE_OPERATOR, MISS_ARGUMENT_POST_OPERATOR, HAVE_OPEN_BRACKETS, MORE_RIGHT_BRACKETS, BAD_ARGUMENTS, UNKNOWN_ERROR {
        if (keys.size() != values.size()) {
            throw new ARGUMENT_LIST_MISMATCH ();
        }
        new Archieve (keys);
        Lexeme answer;
        Sentence sentence = new Sentence(input);
        if ( ErrorHandler.getError () == Id_errors.ERROR_SIGNS ) throw new ERROR_SIGNS ();
        else if ( ErrorHandler.getError () == Id_errors.UNKNOWN_FUNCTION ) throw new UNKNOWN_FUNCTION ();
        sentence.substitute(keys, values);
        answer = sentence.count();
        if (ErrorHandler.getError() == Id_errors.NON_ERROR)
        {
            return answer.get_value();
        }
        else if (ErrorHandler.getError() == Id_errors.UNKNOWN_FUNCTION)
        {
            throw new UNKNOWN_FUNCTION ();
        }
        else if (ErrorHandler.getError() == Id_errors.ERROR_SIGNS)
        {
            throw new ERROR_SIGNS ();
        }
        else if (ErrorHandler.getError() == Id_errors.IMPOSSIBLE_COUNT)
        {
            throw new IMPOSSIBLE_COUNT ();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_BINARY_OPERATOR)
        {
            throw new MISS_ARGUMENT_BINARY_OPERATOR ();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_PRE_OPERATOR)
        {
            throw new MISS_ARGUMENT_PRE_OPERATOR ();
        }
        else if (ErrorHandler.getError() == Id_errors.MISS_ARGUMENT_POST_OPERATOR)
        {
            throw new MISS_ARGUMENT_POST_OPERATOR ();
        }
        else if (ErrorHandler.getError() == Id_errors.HAVE_OPEN_BRACKETS)
        {
            throw new HAVE_OPEN_BRACKETS ();
        }
        else if (ErrorHandler.getError() == Id_errors.MORE_RIGHT_BRACKETS)
        {
            throw new MORE_RIGHT_BRACKETS ();
        }
        else if (ErrorHandler.getError() == Id_errors.BAD_ARGUMENTS)
        {
            throw new BAD_ARGUMENTS ();
        }
        else
        {
            throw new UNKNOWN_ERROR ();
        }

    }

    static class Func_Input_Exception extends Exception{
        private int error_begin = -1;
        private int error_end = -1;
        Func_Input_Exception(){
    
        }
        Func_Input_Exception(int begin, int end){
            error_begin = begin;
            error_end = end;
        }
        public int getError_begin() {
            return error_begin;
        }
    
        public int getError_end() {
            return error_end;
        }
    }

    public static class ARGUMENT_LIST_MISMATCH extends Func_Input_Exception {
        public ARGUMENT_LIST_MISMATCH(){
        }
    }

    public static class BAD_ARGUMENTS extends Func_Input_Exception {
        public BAD_ARGUMENTS(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class ERROR_SIGNS extends Func_Input_Exception {
        public ERROR_SIGNS(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class HAVE_OPEN_BRACKETS extends Func_Input_Exception {
        HAVE_OPEN_BRACKETS(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }}

    public static class IMPOSSIBLE_COUNT extends Func_Input_Exception {
        public IMPOSSIBLE_COUNT(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class MISS_ARGUMENT_BINARY_OPERATOR extends Func_Input_Exception {
        public MISS_ARGUMENT_BINARY_OPERATOR(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class MISS_ARGUMENT_POST_OPERATOR extends Func_Input_Exception {
        public MISS_ARGUMENT_POST_OPERATOR(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class MISS_ARGUMENT_PRE_OPERATOR extends Func_Input_Exception {
        public MISS_ARGUMENT_PRE_OPERATOR(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class MORE_RIGHT_BRACKETS extends Func_Input_Exception {
        public MORE_RIGHT_BRACKETS(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class UNKNOWN_ERROR extends Func_Input_Exception {
        public UNKNOWN_ERROR(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }

    public static class UNKNOWN_FUNCTION extends Func_Input_Exception {
        public UNKNOWN_FUNCTION(){
            super(ErrorHandler.get_begin_error(), ErrorHandler.get_end_error());
        }
    }
}



