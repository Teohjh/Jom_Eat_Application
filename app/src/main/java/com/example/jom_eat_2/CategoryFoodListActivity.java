package com.example.jom_eat_2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class CategoryFoodListActivity extends AppCompatActivity {

    //declare food recycler view and food adapter
    RecyclerView rvFoodList;
    Adapter mAdapter;
    EditText m_et_search;
    ArrayList<FoodModel> search_array_list;
    Query query;

    FirebaseRecyclerOptions<FoodModel> options;
    private String category="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_model_list);

        //food recycler view
        rvFoodList = (RecyclerView) findViewById(R.id.rv_foodList);
        rvFoodList.setLayoutManager(new LinearLayoutManager(this));

        //edit text for search food
        m_et_search = (EditText)findViewById(R.id.eTxtSearchFoodList);

        m_et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(!s.toString().isEmpty()){
                    search(s.toString());
                }
                else{
                    search("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    search(s.toString());
                }
                else{
                    search("");
                }
            }
        });

        //retrieve data from firebase that had filter by category
        Query query = FirebaseDatabase.getInstance().getReference()
                .child("FoodLists").orderByChild("title")
                .startAt(category)
                .endAt(category + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<FoodModel>()
                .setQuery(query, FoodModel.class)
                .build();

        mAdapter = new Adapter(options);
        rvFoodList.setAdapter(mAdapter);

        //navigation  for  each activity page
        bottomNavigation();
    }

    //start list
    @Override
    protected void onStart()
    {
        super.onStart();
        mAdapter.startListening();
    }

    //stop list
    @Override
    protected void onStop()
    {
        super.onStop();
        mAdapter.stopListening();
    }

    //search food
    private void search(String s){
        query = FirebaseDatabase.getInstance().getReference()
                .child("FoodLists").orderByChild("title")
                .startAt(s)
                .endAt(s + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<FoodModel>()
                .setQuery(query, FoodModel.class)
                .build();

        mAdapter = new Adapter(options);
        rvFoodList.setAdapter(mAdapter);

    }

    //bottom navigation bar
    private void bottomNavigation(){

        FloatingActionButton m_random_btn = findViewById(R.id.random_btn);
        LinearLayout m_home_btn = findViewById(R.id.home_btn);
        LinearLayout m_profile_btn = findViewById(R.id.profile_btn);
        LinearLayout m_map_btn = findViewById(R.id.map_btn);
        LinearLayout m_food_list_btn = findViewById(R.id.food_btn);

        //when click random button
        m_random_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CategoryFoodListActivity.this, RandomFoodActivity.class));
            }
        });

        //when click home button
        m_home_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CategoryFoodListActivity.this,MainActivity.class));
            }
        });

        //when click profile button
        m_profile_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent profileActivityIntent = new Intent(CategoryFoodListActivity.this, LogRegisterActivity.class);
                startActivity(profileActivityIntent);
            }
        });

        //when click map button
        m_map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryFoodListActivity.this,MapsActivity.class));
            }
        });

        m_food_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryFoodListActivity.this,FoodModelListActivity.class));
            }
        });
    }
}

