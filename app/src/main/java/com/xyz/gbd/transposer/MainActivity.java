package com.xyz.gbd.transposer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    ImageView wholeNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner begin = findViewById(R.id.original_key);
        Spinner end = findViewById(R.id.transpose_key);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.key_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        begin.setAdapter(adapter);
        end.setAdapter(adapter);

        wholeNote = findViewById(R.id.wholenote);
        wholeNote.setOnTouchListener(this);
    }

    private float snapY(float rawY) {
        return 0;
    }

    private boolean moving = false;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (moving) {
                    float y = event.getRawY() - wholeNote.getHeight() * 3 / 2;
                    wholeNote.setY(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e("noteCheck", "Raw y: " + event.getRawY());
                Log.e("posCheck", "Modified height: " + (wholeNote.getHeight() * 3 / 2));
                Log.e("endCheck", "Combined ending pos = : " + (event.getRawY() - wholeNote.getHeight() * 3 / 2));
                moving = false;
                break;
        }
        return true;
    }
}

