package com.example.digitalkeyboard;

import java.util.ArrayList;

public class LogicCalculater {
    String number;
    double result = 0;
    String text;
    ArrayList<Double> numbers = new ArrayList<>();
    ArrayList<Character> operator = new ArrayList<>();

    protected void addText(String buttonText){
        if (text==null){
            text=buttonText;
        }else {
            if (chekOpr(buttonText.charAt(0)) && chekOpr(text.charAt(text.length()-1))){
                text = text.substring(0,text.length()-1);
                operator.remove(operator.size()-1);
                operator.add(buttonText.charAt(0));
            }
            text+=buttonText;
        }
    }

    protected void initNumbers(String buttonText) {
       if(initOpr(text, '-')|| initOpr(text, '*')|| initOpr(text, '/')|| initOpr(text, '+')){
           return;
       }else {
           if (!chekOpr(buttonText.charAt(0))){
               if (number == null)
                   number = String.valueOf(text.charAt(text.length() - 1));
               else
                   if (text.charAt(text.length() - 1) == ',')
                       number += '.';
               else
                   number += (text.charAt(text.length() - 1));
           }
       }
    }

    private boolean chekOpr(char opr){
        return (opr == '-') || (opr == '+') || (opr == '*') || (opr == '/');
    }
    private boolean initOpr(String text, char opr) {
        if ((text.charAt(text.length() - 1) == opr) && (number != null)) {
            numbers.add(Double.valueOf(number));
            operator.add(opr);
            number=null;
            return true;
        }return false;
    }
    protected String res(){
        int count = 0;
        numbers.add(Double.valueOf(number));
        while (operator.size()>0 && count<operator.size()){
            switch (operator.get(count)){
                case '/': {
                    result = numbers.get(count)/numbers.get(count+1);
                    operator.remove(count);
                    numbers.remove(count+1);
                    numbers.set(count,result);}
                default:count++;
            }
        }
        count = 0;
        while (operator.size()>0  && count<operator.size()) {
            switch (operator.get(count)) {
                case '*': {
                    result = numbers.get(count) * numbers.get(count + 1);
                    operator.remove(count);
                    numbers.remove(count + 1);
                    numbers.set(count, result);
                }
                default:
                    count++;
            }
        }
        count = 0;
        while (operator.size()>0  && count<operator.size()) {
            switch (operator.get(count)) {
                case '+': {
                    result = numbers.get(count) + numbers.get(count + 1);
                    operator.remove(count);
                    numbers.remove(count + 1);
                    numbers.set(count, result);
                }
                default:
                    count++;
            }
        }
        count = 0;
        while (operator.size()>0  && count<operator.size()) {
            switch (operator.get(count)) {
                case '-': {
                    result = numbers.get(count) - numbers.get(count + 1);
                    operator.remove(count);
                    numbers.remove(count + 1);
                    numbers.set(count, result);
                }
                default:
                    count++;
            }
        }
        if (result%1!=0)
            return String.valueOf(result);
        return String.valueOf((int)result);
    }
}