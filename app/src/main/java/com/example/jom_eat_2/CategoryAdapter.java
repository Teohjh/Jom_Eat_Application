package com.example.jom_eat_2;
// category adapter for recycler view
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<Category> category;
    private Context m_context;

    public CategoryAdapter(ArrayList<Category> category) {
        this.category = category;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_category,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category currentCategory = category.get(position);

        holder.m_tv_category_name.setText((category.get(position).getCategory()));
        String pic_location = "";
        switch (position){
            case 0:
                pic_location = "category_beverage";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 1:
                pic_location = "category_cafe";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 2:
                pic_location = "category_chinese";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 3:
                pic_location = "category_fast_food";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 4:
                pic_location = "category_hawker";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 5:
                pic_location = "category_india";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 6:
                pic_location = "category_korea";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 7:
                pic_location = "category_japan";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 8:
                pic_location = "category_malay";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 9:
                pic_location = "category_western";
                holder.m_mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;
        }

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                pic_location,"drawable",holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.m_img_category_img);


        holder.m_tv_category_name.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m_context, CategoryFoodListActivity.class);
                intent.putExtra("category",category.get(position).getCategory());
                m_context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView m_tv_category_name;
        ImageView m_img_category_img;
        ConstraintLayout m_mainLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            m_tv_category_name = itemView.findViewById(R.id.tv_category_name);
            m_img_category_img = itemView.findViewById(R.id.img_category_img);
            m_mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
