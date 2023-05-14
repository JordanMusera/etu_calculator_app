package com.example.myapplication555;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication555.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView display1,display2;
    MaterialButton calcButtonC,calcButtonOpenBracket,calcButtonCloseBracket,calcButtonDivide;
    MaterialButton calcButton7,calcButton8,calcButton9,calcButtonMultiply;
    MaterialButton calcButton4,calcButton5,calcButton6,calcButtonAdd;
    MaterialButton calcButton1,calcButton2,calcButton3,calcButtonMinus;
    MaterialButton calcButtonAC,calcButton0,calcButtonPoint,calcButtonEqual;
@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    display1=findViewById(R.id.display1);
    display2=findViewById(R.id.display2);

    assignId(calcButtonC, R.id.calcButtonC);
    assignId(calcButtonOpenBracket, R.id.calcButtonOpenBracket);
    assignId(calcButtonCloseBracket, R.id.calcButtonCloseBracket);
    assignId(calcButtonDivide, R.id.calcButtonDivide);
    assignId(calcButton7, R.id.calcButton7);
    assignId(calcButton8, R.id.calcButton8);
    assignId(calcButton9, R.id.calcButton9);
    assignId(calcButtonMultiply, R.id.calcButtonMultiply);
    assignId(calcButton4, R.id.calcButton4);
    assignId(calcButton5, R.id.calcButton5);
    assignId(calcButton6, R.id.calcButton6);
    assignId(calcButtonAdd, R.id.calcButtonAdd);
    assignId(calcButton1, R.id.calcButton1);
    assignId(calcButton2, R.id.calcButton2);
    assignId(calcButton3, R.id.calcButton3);
    assignId(calcButtonMinus, R.id.calcButtonMinus);
    assignId(calcButtonAC, R.id.calcButtonAC);
    assignId(calcButton0, R.id.calcButtonZero);
    assignId(calcButtonPoint, R.id.calcButtonPoint);
    assignId(calcButtonEqual, R.id.calcButtonEqual);
}
void assignId(MaterialButton btn,int id){
    btn=findViewById(id);
    btn.setOnClickListener(this);
}


    @Override
    public void onClick(View v) {
    MaterialButton button=(MaterialButton) v;
    String buttonText=button.getText().toString();
    String dataToCalculate=display1.getText().toString();

    int display1L=display1.length();

    if(buttonText.equals("AC")){
        display1.setText("");
        display2.setText("0");
        return;
    }
    if(buttonText.equals("*")){
        display1.setText(display2.getText());
    }
    if(buttonText.equals("c")&& display1L!=0){
        dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
    } else if (buttonText.equals("c")&& display1L==0) {
        display1.setText("");
    } else {
        dataToCalculate = dataToCalculate + buttonText;
    }


    display1.setText(dataToCalculate);
    String finalResult= getResults(dataToCalculate);

    if(!finalResult.equals("Err")){
        display2.setText(finalResult);
    }
        if(buttonText.equals("=")){
            display1.setText(display2.getText());
            return;
        }
    }

    String getResults(String data){
    try{
        Context context=Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable=context.initStandardObjects();
        String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
        if(finalResult.endsWith(".0")){
            finalResult=finalResult.replace(".0","");
        } else if (finalResult.endsWith("org.mozilla.javascript.Undefined@0")) {
            finalResult=finalResult.replace("org.mozilla.javascript.Undefined@0", "0");
        }
        return finalResult;
    }catch (Exception e){
        return "Err";
    }
    }
}

