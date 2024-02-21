package com.math.findingmememo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgLogo = findViewById(R.id.Logo);
        TextView textLogo = findViewById(R.id.textLogo);

        // This part is for the ImageView Animation
        ObjectAnimator imgAnimator = ObjectAnimator.ofFloat(imgLogo, "translationY", 0f, 200f);
        imgAnimator.setDuration(1000);
        imgAnimator.start();

        // This part is for the Text Logo
        ObjectAnimator textAnimator = ObjectAnimator.ofFloat(textLogo, "alpha", 0f, 1f); // This will handle the Opacity of the code
        textAnimator.setDuration(6000);
        textAnimator.start();

        // Adding sound effect
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.waterdrop); // Replace "your_sound_effect" with the name of your MP3 file in the raw folder

        imgAnimator.addListener(new android.animation.Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(android.animation.Animator animation) {}

            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                mediaPlayer.start(); // Start playing the sound effect
                // Delay before starting another activity
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Start another activity when the delay is over
                        Intent intent = new Intent(MainActivity.this, problem.class);
                        startActivity(intent);
                        // Finish current activity if you don't want to return to it
                        finish();
                    }
                }, 3500); // Adjust the delay time in milliseconds (e.g., 2000 for 2 seconds)
            }

            @Override
            public void onAnimationCancel(android.animation.Animator animation) {}

            @Override
            public void onAnimationRepeat(android.animation.Animator animation) {}
        });
    }
}
