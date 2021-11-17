package com.example.jom_eat_2;
//old version of food adapter  page
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FoodAdapter extends FirebaseRecyclerAdapter<Food,FoodAdapter.ViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FoodAdapter(@NonNull FirebaseRecyclerOptions<Food> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Food model) {

        holder.m_tv_food_name.setText(model.getName());
        holder.m_tv_food_hours.setText(model.getHours());
        holder.m_tv_food_review.setText(model.getReview());

        Glide.with(holder.m_food_image.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.m_food_image);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_food_list, parent,false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView m_tv_food_name, m_tv_food_hours,
        m_tv_food_review;
        ImageView m_food_image;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            m_food_image = itemView.findViewById(R.id.img_food);
            m_tv_food_name = itemView.findViewById(R.id.tv_food_name);
            m_tv_food_hours = itemView.findViewById(R.id.tv_food_hours);
            m_tv_food_review = itemView.findViewById(R.id.tv_food_review);

        }
    }
}
