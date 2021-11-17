package com.example.jom_eat_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.AutoScrollHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter rv_event_adapter;
    private RecyclerView.Adapter rv_adapter;
    private RecyclerView.Adapter rv_rcmd_adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewRecommendList;
    private RecyclerView recyclerViewEventList;
    EditText m_search_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search();
        recyclerViewEvent();
        recyclerViewCategory();
        RecyclerViewRecommend();
        bottomNavigation();
    }

    //searchfood

    private void search(){
        //edit text for search food
        m_search_food = (EditText)findViewById(R.id.eTxtSearchFood);

        m_search_food.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                startActivity(new Intent(MainActivity.this, FoodModelListActivity.class));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    //recycler view event for homepage
    private  void recyclerViewEvent(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewEventList = findViewById(R.id.rv_event);
        recyclerViewEventList.setLayoutManager(linearLayoutManager);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(new Event("Happy   Deepavali !!!", "event_deepavali"));
        eventList.add(new Event("Merry Christmas !!!", "event_christmas"));
        eventList.add(new Event("Valentine  Day !!!", "event_valentine"));

        rv_event_adapter = new EventAdapter(eventList);
        recyclerViewEventList.setAdapter(rv_event_adapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerViewCategoryList);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() <
                        (rv_event_adapter.getItemCount() - 1)) {
                    linearLayoutManager.smoothScrollToPosition(recyclerViewEventList,
                            new RecyclerView.State(), linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                } else if (linearLayoutManager.findLastCompletelyVisibleItemPosition() <
                        (rv_event_adapter.getItemCount() - 1)) {
                    linearLayoutManager.smoothScrollToPosition(recyclerViewEventList, new RecyclerView.State(), 0);

                }
            }
        },0,3000);


    }

    //recycler view category for homepage
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recycler_view_cat);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Beverage", "category_beverage"));
        categoryList.add(new Category("Cafe", "category_cafe"));
        categoryList.add(new Category("Chinese", "category_chinese"));
        categoryList.add(new Category("Fast Food", "category_fast_food"));
        categoryList.add(new Category("Hawker", "category_hawker"));
        categoryList.add(new Category("India", "category_india"));
        categoryList.add(new Category("Japan", "category_japan"));
        categoryList.add(new Category("Korea", "category_korea"));
        categoryList.add(new Category("Malay", "category_malay"));
        categoryList.add(new Category("Western", "category_western"));

        rv_adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(rv_adapter);
    }

    //recycler view recommend food for homepage
    private void RecyclerViewRecommend(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewRecommendList = findViewById(R.id.recycler_view_recomd);
        recyclerViewRecommendList.setLayoutManager(linearLayoutManager);

        ArrayList<RecommendFood> recommendList = new ArrayList<>();
        recommendList.add(new RecommendFood("Woodland Vegetarian", "india_woodland_vegetarian", "5"));
        recommendList.add(new RecommendFood("Ippudo", "japan_gurney_ippudo", "5"));
        recommendList.add(new RecommendFood("Sushi Ya", "japan_jelutong_sushi_ya", "5"));
        recommendList.add(new RecommendFood("Kim's Korea BBQ", "korea_jelutong_kims", "5"));
        recommendList.add(new RecommendFood("Daseo", "korea_queensbay_daseo", "5"));

        rv_rcmd_adapter = new RecommendAdapter(recommendList);
        recyclerViewRecommendList.setAdapter(rv_rcmd_adapter);
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
                startActivity(new Intent(MainActivity.this, RandomFoodActivity.class));
             }
         });

         m_home_btn.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 startActivity(new Intent(MainActivity.this,MainActivity.class));
             }
         });

         m_profile_btn.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view){
                 Intent profileActivityIntent = new Intent(MainActivity.this, ProfileActivity.class);
                 startActivity(profileActivityIntent);
             }
         });

         m_map_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(MainActivity.this,MapsActivity.class));
             }
         });

         m_food_list_btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(MainActivity.this,FoodModelListActivity.class));
             }
         });
     }


}