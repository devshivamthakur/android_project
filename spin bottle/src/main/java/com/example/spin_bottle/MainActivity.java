package com.example.spin_bottle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
private ImageView bottle;
private  Random random=new Random();
private int lastdir;
private boolean spinning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottle=findViewById(R.id.imageView);
        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!spinning){
                    int newdir=random.nextInt(2000);
                    float pivotX=bottle.getWidth()/2;
                    float pivoty=bottle.getHeight()/2;
                    Animation rotate=new RotateAnimation(lastdir,newdir,pivotX,pivoty);
                    rotate.setDuration(2000);
                    rotate.setFillAfter(true);
                    rotate.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            spinning=true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            spinning=false;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    lastdir=newdir;
                    bottle.startAnimation(rotate);

                }
            }
        });

    }
}