package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    TextView resulttv,solutiontv;
    MaterialButton buttonc,buttonbrackopen,buttonbrackclose;
    MaterialButton buttondivide,buttonmultiply,buttonaddition,buttonequal,buttonminus;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resulttv = findViewById(R.id.result_tv);
        solutiontv = findViewById(R.id.solution_tv);
        assignId(buttonc,R.id.button_c);
        assignId(buttonbrackopen,R.id.button_open_barcket);
        assignId(buttonbrackclose,R.id.button_closed_barcet);
        assignId(buttondivide,R.id.button_divide);
        assignId(buttonmultiply,R.id.button_multiply);
        assignId(buttonaddition,R.id.button_sum);
        assignId(buttonequal,R.id.button_equal);
        assignId(buttonminus,R.id.button_subtract);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonDot,R.id.button_dot);

    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate  = solutiontv.getText().toString();

        if (buttonText.equals("AC")){
            solutiontv.setText("");
            resulttv.setText("0");
            return;
        }

        if (buttonText.equals('=')){
            solutiontv.setText(resulttv.getText());
            return;
        }
        if (buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate+buttonText;
        }


        solutiontv.setText(dataToCalculate);
        String finalresult  = getresult(dataToCalculate);

        if (!finalresult.equals("ERR")){
            resulttv.setText(finalresult);
        }
    }
    String getresult (String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e) {
            return "ERR";
        }
    }
}
