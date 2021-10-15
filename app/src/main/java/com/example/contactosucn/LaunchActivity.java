package com.example.contactosucn;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // Add Animations
        Animation swipeUpAnimation = AnimationUtils.loadAnimation(this, R.anim.swipe_up);
        Animation swipeDownAnimation = AnimationUtils.loadAnimation(this, R.anim.swipe_down);

        TextView txtCourse = findViewById(R.id.txtCurso);
        ImageView imgLogo = findViewById(R.id.imgLogo);

        txtCourse.setAnimation(swipeUpAnimation);
        imgLogo.setAnimation(swipeDownAnimation);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LaunchActivity.this, ContactListActivity.class);
            startActivity(intent);
            finish();
        }, 4000);

    }
}
