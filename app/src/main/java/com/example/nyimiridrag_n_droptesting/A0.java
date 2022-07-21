package com.example.nyimiridrag_n_droptesting;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.alphamovie.lib.AlphaMovieView;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

import java.util.ArrayList;


public class A0 extends AppCompatActivity {
    LinearLayout topLayout;
    ImageView iv1,iv2,iv3,iv4,iv5,iv6;
    boolean correct = false;
    int i = 0;
    MediaPlayer applaud, elephant, hippo, rhino, congrats;
    ArrayList<ImageView> imageList;
    ArrayList<String> correctTags;
    View draggedImage;
    SwipeButton swipeNext;
    VideoView videoView;
    Button btn_play;
    AlphaMovieView myVideo1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a0);
        imageList = new ArrayList<>();
        correctTags = new ArrayList<>();
        initControls();

    }

    private void initControls() {
        // TODO Auto-generated method stub
            //MediaPlayer
        applaud = MediaPlayer.create(A0.this, R.raw.clapping);
        elephant = MediaPlayer.create(A0.this, R.raw.elepha01);
        hippo = MediaPlayer.create(A0.this, R.raw.hippop01);
        rhino = MediaPlayer.create(A0.this, R.raw.rhinoc01);
        congrats = MediaPlayer.create(A0.this, R.raw.congrats);

        imageList.add(iv1 = findViewById (R.id.Elephant));
         iv2 = findViewById (R.id.Hippo_Shadow);
         iv3 = findViewById (R.id.Elephant_Shadow);
         iv4 = findViewById (R.id.Rhino_Shadow);
        //Two additional images for: Hippo and Rhino.
        imageList.add(iv5 = findViewById(R.id.Hippo));
        imageList.add(iv6 = findViewById(R.id.Rhino));

        // hide all images
        for (int i = 0; i < imageList.size() ; i++) {
            imageList.get(i).setVisibility(View.INVISIBLE);
        }

        // show random image
        int index = (int)(Math.random() * imageList.size());
        imageList.get(index).setVisibility(View.VISIBLE);



        iv1.setTag("ELEPHANT");
        iv2.setTag("HIPPO");
        iv3.setTag("ELEPHANT");
        iv4.setTag("RHINO");
        //Two additional image Tags for: Hippo and Rhino.
        iv5.setTag("HIPPO");
        iv6.setTag("RHINO");


        //setOnTouchListener
        iv1.setOnTouchListener(new MyTouchListener());
        iv5.setOnTouchListener(new MyTouchListener());
        iv6.setOnTouchListener(new MyTouchListener());

        //setOnDragListener
        iv2.setOnDragListener(new MyDragListener());
        iv3.setOnDragListener(new MyDragListener());
        iv4.setOnDragListener(new MyDragListener());

        //Swipe Button
        swipeNext = findViewById(R.id.switch1);
        swipeNext.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                // finish current activity
                finish();
                Intent intent = new Intent(A0.this, A1.class);
                startActivity(intent);
            }
        });

    }

    private final class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                draggedImage = view;
                return true;
            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.setVisibility(View.VISIBLE);
                return true;
            }else {
                return false;
            }
        }
    }

    /**
     * Loop thorough images and check if they have already been shown, if not show image
     */
    private void showNextImage(){
        for (int j = 0; j < imageList.size(); j++) {
            ImageView imageView = imageList.get(j);
            if (!correctTags.contains(imageView.getTag().toString())){
                imageView.setVisibility(View.VISIBLE);
                return;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                        //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                        //Drawable drop_shadow = getResources().getDrawable(R.drawable.elephant1);
                        //view dragged item is being dropped on
                    ImageView dropTarget = (ImageView) v;
                        //view being dragged and dropped
                    ImageView dropped = (ImageView) view;
                        //if there is already an item here, set it back visible in its original place
                    String temp1 = "ELEPHANT";
                    String temp2 = "HIPPO";
                    String temp3 = "RHINO";
                    if(temp1  == view.getTag() && v.getId() == R.id.Elephant_Shadow){
                        Drawable drop_shadow = getResources().getDrawable(R.drawable.elephant1);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                dropTarget.setForeground(drop_shadow);
                                i++;
                                correctTags.add(temp1);
                                showNextImage();
                                if(i==3){
                                    showCongrats();
                            }elephant.start();
                        }
                        correct = true;
                        Toast.makeText(A0.this, "Correct", Toast.LENGTH_SHORT).show();
                    }else if(temp2  == view.getTag() && v.getId() == R.id.Hippo_Shadow){
                        Drawable drop_shadow = getResources().getDrawable(R.drawable.hippo1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            dropTarget.setForeground(drop_shadow);
                            correctTags.add(temp2);
                            showNextImage();
                            i++;
                            if(i==3){
                                showCongrats();
                            }hippo.start();
                        }
                        correct = true;
                        Toast.makeText(A0.this, "Correct", Toast.LENGTH_SHORT).show();
                    }else if(temp3  == view.getTag() && v.getId() == R.id.Rhino_Shadow){
                        Drawable drop_shadow = getResources().getDrawable(R.drawable.rhino1);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            dropTarget.setForeground(drop_shadow);
                            correctTags.add(temp3);
                            showNextImage();
                            i++;
                            if(i==3){
                                showCongrats();
                            }rhino.start();
                        }
                        correct = true;
                        Toast.makeText(A0.this, "Correct", Toast.LENGTH_SHORT).show();
                    }else {
                            // reset image visibility so it can be re-dragged
                        draggedImage.setVisibility(View.VISIBLE);
                        Toast.makeText(A0.this, "Uyira = Repeat", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (dropEventNotHandled(event)){
                        if (!correct){
                            draggedImage.setVisibility(View.VISIBLE);
                            Toast.makeText(A0.this, "Match image with correct Shadow", Toast.LENGTH_SHORT).show();
                        }
                    }
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

    private void showCongrats() {
        // hide other views
        iv1.setVisibility(View.GONE);
        iv2.setVisibility(View.GONE);
        iv3.setVisibility(View.GONE);
        iv4.setVisibility(View.GONE);
        iv5.setVisibility(View.GONE);
        iv6.setVisibility(View.GONE);

        // show switch
        swipeNext.setVisibility(View.VISIBLE);

        // start player
        applaud.start();
        myVideo1 = findViewById(R.id.myVideo);
        myVideo1.setVisibility(View.VISIBLE);
        Uri myUri = Uri.parse("android.resource://"+getPackageName() + "/" + R.raw.congrats);
        myVideo1.setVideoFromUri(A0.this,myUri);
    }
}