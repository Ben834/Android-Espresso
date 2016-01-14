package com.ben.testing.espresso.basics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ben.testingsandbox.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String KEY_COUNT = "count";

    private int count = 0;
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
            countView.setText(String.valueOf(count));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
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
