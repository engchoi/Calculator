package com.example.jchoi.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button calcButton;
    EditText equationEditText;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equationEditText = (EditText)findViewById(R.id.editText);
        resultTextView = (TextView)findViewById(R.id.textView);
        calcButton = (Button)findViewById(R.id.button);
        ButtonEventListener listener = new ButtonEventListener();
        calcButton.setOnClickListener(listener);
    }

    private class ButtonEventListener implements View.OnClickListener {
        public void onClick(View view) {
            String equation = equationEditText.getText().toString();
            resultTextView.setText(equation);
        }
    }
}