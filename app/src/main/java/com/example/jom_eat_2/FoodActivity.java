package com.example.jom_eat_2;
//old  version food list page
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.api.Distribution;
import com.google.firebase.database.FirebaseDatabase;

public class FoodActivity extends AppCompatActivity {

    RecyclerView m_rv_food_list;
    FoodAdapter m_food_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        m_rv_food_list = findViewById(R.id.rv_food_list);
        m_rv_food_list.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("food"), Food.class)
                        .build();

       m_food_adapter = new FoodAdapter(options);
        m_rv_food_list.setAdapter(m_food_adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        m_food_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        m_food_adapter.stopListening();
    }
}

