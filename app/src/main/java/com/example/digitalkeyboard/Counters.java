package com.example.digitalkeyboard;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Counters implements Parcelable {
    String number;
    double result = 0;
    String text;
    private String operator= "/*+-";
    private int count = 0;
    ArrayList<Double> numbers = new ArrayList<>();
    ArrayList<Character> operators = new ArrayList<>();

    protected Counters(Parcel in) {
        number = in.readString();
        result = in.readDouble();
        text = in.readString();
        operator = in.readString();
        count = in.readInt();
    }

    public static final Creator<Counters> CREATOR = new Creator<Counters>() {
        @Override
        public Counters createFromParcel(Parcel in) {
            return new Counters(in);
        }

        @Override
        public Counters[] newArray(int size) {
            return new Counters[size];
        }
    };

    public Counters() {

    }

    protected void addText(String buttonText){
        if (text==null){
            text=buttonText;
        }else {
            if (chekOpr(text.charAt(text.length()-1))&&chekOpr(text.charAt(text.length()-2))&&chekOpr(buttonText.charAt(0)))
                return;
            if (chekOpr(text.charAt(text.length()-1)) && buttonText.equals("-")){

            }
            else
            if (chekOpr(buttonText.charAt(0)) && chekOpr(text.charAt(text.length()-1))){
                text = text.substring(0,text.length()-1);
                operators.remove(operators.size()-1);
                operators.add(buttonText.charAt(0));
            }
            text+=buttonText;
        }
    }

    protected void initNumbers(String buttonText) {
        if(initOpr(text))
            return;
        if (buttonText.charAt(0) == '-' || !chekOpr(buttonText.charAt(0))){
            if (number == null)
                if (text.charAt(text.length() - 1) == ',')
                    number = "0.";
                else number = String.valueOf(text.charAt(text.length() - 1));
            else
            if (text.charAt(text.length() - 1) == ',')
                number += '.';
            else
                number += (text.charAt(text.length() - 1));
        }
    }

    private boolean chekOpr(char opr){
        return (opr == '-') || (opr == '+') || (opr == '*') || (opr == '/');
    }

    private boolean initOpr(String text) {
        if (chekOpr(text.charAt(text.length()-1))&&chekOpr(text.charAt(text.length()-2)))
            return false;
        if ((chekOpr(text.charAt(text.length()-1)) && (number != null))&&(!(chekOpr(text.charAt(text.length()-2)) && text.charAt(text.length()-1)!='-'))){
            numbers.add(Double.valueOf(number));
            operators.add(text.charAt(text.length()-1));
            number=null;
            return true;
        }return false;
    }

    protected String res(){
        numbers.add(Double.valueOf(number));
       try {
           for (int i = 0; i < operator.length(); i++) {
               while (operators.size() > 0 && count < operators.size()) {
                   if (operators.get(count) == operator.charAt(i)) {
                       helpRes(operators.get(count));
                   } else count++;
               }
               count = 0;
           }
           textRes();
       } catch (Exception e) {
           return "Не правильное выражение";
       }
        if (result%1!=0)
            return String.valueOf(result);
        else return String.valueOf((int)result);
    }

    private void textRes() {
        numbers=new ArrayList<>();
        if (result%1 != 0)
            number = String.valueOf(result);
        else number = String.valueOf((int)result);
        operators=new ArrayList<>();
        text = number;
    }

    private void helpRes(char opr){
        switch (opr){
            case '/': {
                result = numbers.get(count) / numbers.get(count + 1);
                break;
            }
            case '-':{
                result = numbers.get(count) - numbers.get(count + 1);
                break;
            }
            case '+':{
                result = numbers.get(count) + numbers.get(count + 1);
                break;
            }
            case '*':{
                result = numbers.get(count) * numbers.get(count + 1);
                break;
            }
        }
        operators.remove(count);
        numbers.remove(count + 1);
        numbers.set(count, result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeDouble(result);
        dest.writeString(text);
        dest.writeString(operator);
        dest.writeInt(count);
    }
}
