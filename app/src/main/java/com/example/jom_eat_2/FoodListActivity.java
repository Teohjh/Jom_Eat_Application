package com.example.jom_eat_2;
//old version of food list page activity
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {

    private RecyclerView m_rv_food_list;
    private FoodAdapter m_food_adapter;

    private FirebaseFirestore m_firestore = FirebaseFirestore.getInstance();
    private CollectionReference foodRef = m_firestore.collection("food");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        m_rv_food_list = findViewById(R.id.rv_food_list);

        listFood();

    }

    private void listFood(){

        //query
        Query food_query = foodRef;

        //options
        FirestoreRecyclerOptions<Food> options = new FirestoreRecyclerOptions.Builder<Food>()
                .setQuery(food_query, Food.class)
                .build();


        //set food adapter
        //m_food_adapter = new FoodAdapter(options);
        m_rv_food_list.setLayoutManager(new LinearLayoutManager(this));
        m_rv_food_list.setAdapter(m_food_adapter);


    }

    @Override
    protected void onStop() {
        super.onStop();
        m_food_adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        m_food_adapter.startListening();
    }

    //bottom navigation bar
    private void bottomNavigation(){

        FloatingActionButton m_random_btn = findViewById(R.id.random_btn);
        LinearLayout m_home_btn = findViewById(R.id.home_btn);
        LinearLayout m_profile_btn = findViewById(R.id.profile_btn);
        LinearLayout m_map_btn = findViewById(R.id.map_btn);
        LinearLayout m_food_list_btn = findViewById(R.id.food_btn);

        m_random_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FoodListActivity.this, RandomFoodActivity.class));
            }
        });

        m_home_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FoodListActivity.this,MainActivity.class));
            }
        });

        m_profile_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent profileActivityIntent = new Intent(FoodListActivity.this, LogRegisterActivity.class);
                startActivity(profileActivityIntent);
            }
        });

        m_map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodListActivity.this,MapsActivity.class));
            }
        });

        m_food_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodListActivity.this,FoodListActivity.class));
            }
        });
    }

}