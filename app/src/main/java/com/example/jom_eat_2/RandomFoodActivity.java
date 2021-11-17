package com.example.jom_eat_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RandomFoodActivity extends AppCompatActivity{

    ImageView imgWheel;
    ImageView imgBottomUp;

    String[] sectors = {
           "Beverage", "Cafe", "Chinese Food", "Fast Food",
            "Hawker Food", "India Food", "Japanese Food",
            "Korea  Food", "Western Food"
    };

    TextView mtvRandomSpinFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_food);

        imgWheel = findViewById(R.id.pin_wheel);
        imgBottomUp = findViewById(R.id.img_random_food_list_up);
        mtvRandomSpinFood = findViewById(R.id.tv_random_food);

        Collections.reverse(Arrays.asList(sectors));

        bottomSheetDialog();
        bottomNavigation();
    }

    //spin  wheel for random food and show food  list
    public void spinWheel(View view){

        Random randomSpin = new Random();
        final int degree = randomSpin.nextInt(360);

        RotateAnimation rotateAnimation = new RotateAnimation(
                0, degree + 720, // 720 to take two rotation
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);

        //spin animation for spin wheel
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                CalculatePoint(degree);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imgWheel.startAnimation(rotateAnimation);
    }

    //calculate the spin degree for  each category
    public void CalculatePoint(int degree){
        //total degree 360
        int initialPoint = 0;
        int endPoint = 40;
        int i = 0;
        String res = null;

        do{
            if(degree > initialPoint && degree < endPoint){

                res = sectors[i];

            }
            initialPoint += 40;
            endPoint += 40;
            i++;

        }while(res == null);

        mtvRandomSpinFood.setText(res);
    }

    //bottom  sheet for  random food categories list
    public void bottomSheetDialog(){
        /**

        imgBottomUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                BottomSheetDialog sheetDialog = new BottomSheetDialog(RandomFoodActivity.this, R.style.bottomS);
                View sheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.bottom_sheet_random_food_list,
                                (LinearLayout)findViewById(R.id.bottom_sheet_layout));

                sheetView.findViewById(R.id.img_random_food_list_scroll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sheetDialog.dismiss();
                    }
                });

                sheetDialog.setContentView(sheetView);
                sheetDialog.show();
            }
        });**/

        //show  the category of food list
        imgBottomUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RandomFoodBottomSheetDialog bottomSheet = new RandomFoodBottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(),  "TAG");
            }
        });
    }

    //bottom navigation bar
    private void bottomNavigation(){

        FloatingActionButton m_random_btn = findViewById(R.id.random_btn);
        LinearLayout m_home_btn = findViewById(R.id.home_btn);
        LinearLayout m_profile_btn = findViewById(R.id.profile_btn);
        LinearLayout m_map_btn = findViewById(R.id.map_btn);
        LinearLayout m_food_list_btn = findViewById(R.id.food_btn);

        //click for  random food page
        m_random_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(RandomFoodActivity.this, RandomFoodActivity.class));
            }
        });

        //click for home page
        m_home_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(RandomFoodActivity.this,MainActivity.class));
            }
        });

        //click for profile page
        m_profile_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent profileActivityIntent = new Intent(RandomFoodActivity.this, LogRegisterActivity.class);
                startActivity(profileActivityIntent);
            }
        });

        //click for map page activity
        m_map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RandomFoodActivity.this,MapsActivity.class));
            }
        });

        //click for food list activity
        m_food_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RandomFoodActivity.this,FoodModelListActivity.class));
            }
        });
    }

}