package com.example.nyimiridrag_n_droptesting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import com.example.nyimiridrag_n_droptesting.models.Pieces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class A0 extends AppCompatActivity {
    ImageView iv1,iv2,iv3,iv4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a0);

        initControls();
    }

    private void initControls() {
        // TODO Auto-generated method stub

        iv1 = findViewById (R.id.A_Cups);
        iv2 = findViewById (R.id.A_Cups_N_small);
        iv3 = findViewById (R.id.A_Cups_Shadow);
        iv4 = findViewById (R.id.A_Small);

        iv1.setTag("A_CUPS");
        iv2.setTag("A_CUPS_N_SMALL");
        iv3.setTag("A_CUPS");
        iv4.setTag("A_SMALL");

        //iv3 = (ImageView) findViewById (R.id.imageView3);
        iv1.setOnTouchListener(new MyTouchListener());
        //iv2.setOnTouchListener(new MyTouchListener());
        iv3.setOnDragListener(new MyDragListener());
        //iv4.setOnTouchListener(new MyTouchListener());

    }

    private final class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.setVisibility(View.VISIBLE);
                return true;
            }else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    if(v != iv3)
                        v.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    Drawable dropshadow = getResources().getDrawable(R.drawable.elephant1);
                    //view dragged item is being dropped on
                    ImageView dropTarget = (ImageView) v;
                    //view being dragged and dropped
                    ImageView dropped = (ImageView) view;
                    //if there is already an item here, set it back visible in its original place
                    String temp = "A_CUPS";
                    if(temp.equals(view.getTag())){
                        dropTarget.setBackground(dropshadow);
                        Toast.makeText(A0.this, "Correct", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(A0.this, "Uyira", Toast.LENGTH_SHORT).show();
                        Log.d("A_CUPS_N_SMALL","Uyira");
                        view.setVisibility(View.INVISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (dropEventNotHandled(event))
                    break;

                default:
                    break;
            }
            return true;
        }
        private boolean dropEventNotHandled(DragEvent dragEvent) {

            return !dragEvent.getResult();
        }
    }
}