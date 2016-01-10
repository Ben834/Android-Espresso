package com.ben.testingsandbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String KEY_COUNT = "count";
    private static final String KEY_EDIT_TEXT = "editText";

    private int count = 0;
    private String text;
    private TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);
        countView = (TextView) findViewById(R.id.activity_main_count);
        Button incrementView = (Button) findViewById(R.id.activity_main_increment);
        incrementView.setOnClickListener(this);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT, 0);
            text = savedInstanceState.getString(KEY_EDIT_TEXT);
            countView.setText(String.valueOf(count));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
        outState.putString(KEY_EDIT_TEXT, text);
    }

    public void increment() {
        updateCount();
    }

    private void updateCount() {
        count += 1;
        countView.setText(String.valueOf(count));
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if(id == R.id.activity_main_increment){
            increment();
        }
    }
}
