package com.rapture.watchdog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button s_button = (Button) findViewById(R.id.s_button);
        EditText et_time = (EditText) findViewById(R.id.time);
        et_time.setText("5");

        s_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = 5;
                EditText et_time = (EditText) findViewById(R.id.time);
                if (et_time.getText().toString() != null)       time = Integer.parseInt(et_time.getText().toString());

                Chrono c = Chrono.getInstance();
                c.setTime(time);
                Intent i = new Intent(MainActivity.this, screenWatchdog.class);
                startService(i);
            }
        });

        Button button_s = (Button) findViewById(R.id.button_s);

        button_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, screenWatchdog.class));
            }
        });
    }
}
