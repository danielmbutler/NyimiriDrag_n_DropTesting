package com.example.nyimiridrag_n_droptesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

public class A1 extends AppCompatActivity {
    SwipeButton swipeNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1);

        initControls();

    }

    private void initControls() {
        // TODO Auto-generated method stub
        //Swipe Button
        swipeNext = findViewById(R.id.switch1);
        swipeNext.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                // finish current activity
                finish();
                Intent intent = new Intent(A1.this, A0.class);
                startActivity(intent);
            }
        });

    }
}