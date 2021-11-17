package com.example.jom_eat_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodDetails extends AppCompatActivity
{
    TextView tvFoodTitle , tvRatingText, tvCommentText, tvFoodAddress
            ,tvFoodHours, tvFoodReview,tvFoodPhone, tvFoodOrder;
    ImageView ivFoodImage, ivBackClick;
    RecyclerView rvCommentList;
    CommentAdapter mCommentAdapter;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        tvFoodTitle = findViewById(R.id.tv_foodTitle);
        ivFoodImage = findViewById(R.id.iv_foodImage);
        ivBackClick = findViewById(R.id.img_back);
        tvCommentText = findViewById(R.id.tvCommentText);
        tvRatingText = findViewById(R.id.tvRatingNumber);
        tvFoodAddress = findViewById(R.id.tv_food_address);
        tvFoodHours = findViewById(R.id.tv_foodHours);
        tvFoodReview = findViewById(R.id.tv_foodReview);
        //tvFoodOrder = findViewById(R.id.tv_foodPhone);
        tvFoodPhone = findViewById(R.id.tv_foodOrder);

        Intent i = getIntent();
        String foodTitle =i.getExtras().get("title").toString();
        String foodHours =i.getExtras().get("hours").toString();
        String foodReview =i.getExtras().get("review").toString();
        String foodAddress =i.getExtras().get("address").toString();
       // String foodOrder =i.getExtras().get("order").toString();
        String foodPhone =i.getExtras().get("phone").toString();

        tvFoodTitle.setText(foodTitle);
        tvFoodHours.setText("Hours : " + foodHours);
        tvFoodReview.setText("Review : " + foodReview);
        tvFoodAddress.setText("Address : " + foodAddress);
        //tvFoodOrder.setText("Order : " + foodOrder);
        tvFoodPhone.setText("Phone : " + foodPhone);

        String foodImage = getIntent().getStringExtra("image");
        Glide.with(this).load(foodImage)
                .into(ivFoodImage);


        displayComment();

        ivBackClick.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(FoodDetails.this,FoodModelListActivity.class));
                    }
                }

        );
    }

    private void displayComment(){
        rvCommentList = findViewById(R.id.rv_commentList);
        rvCommentList.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<CommentModel> options =
                new FirebaseRecyclerOptions.Builder<CommentModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("UserComments"), CommentModel.class)
                        .build();

        mCommentAdapter = new CommentAdapter(options);
        rvCommentList.setAdapter(mCommentAdapter);
    }
    /*
    private void displayFoodDetail(String title){

        mDataReference = FirebaseDatabase.getInstance().getReference().child("FoodLists").child(title);
        mDataReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String title = snapshot.child("title").getValue().toString();
                String image = snapshot.child("image").getValue().toString();

                tvFoodTitle.setText(title);

                Glide.with(mContext).load(image)
                        .into(ivFoodImage);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

    @Override
    protected void onStart()
    {
        super.onStart();
        mCommentAdapter.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mCommentAdapter.stopListening();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent i = new Intent(FoodDetails.this, FoodModelListActivity.class);
            startActivity(i);
        }
        return true;
    }
}