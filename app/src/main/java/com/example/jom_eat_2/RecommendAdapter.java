package com.example.jom_eat_2;

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

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    ArrayList<RecommendFood> food;

    public RecommendAdapter(ArrayList<RecommendFood> food) {
        this.food = food;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_recomd,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.m_tv_food_name.setText((food.get(position).getTitle_rcmd()));
        holder.m_tv_food_rcmd_review.setText((food.get(position).getM_tv_food_rcmd_review()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                food.get(position).getImg_rcmd_icon(),
                "drawable",holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.m_img_food_img);
    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView m_tv_food_name, m_tv_food_rcmd_review;
        ImageView m_img_food_img;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            m_tv_food_name = itemView.findViewById(R.id.tv_food_rcmd);
            m_img_food_img = itemView.findViewById(R.id.img_food_rcmd);
            m_tv_food_rcmd_review = itemView.findViewById(R.id.tv_food_rmd_review_ans);
        }
    }
}
