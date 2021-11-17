package com.example.jom_eat_2;

//food list adapter for firebase recycler view
import android.content.Intent;
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

//food list adapter
public class Adapter extends FirebaseRecyclerAdapter<FoodModel, Adapter.ViewHolder>
{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirebaseRecyclerOptions<FoodModel> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull FoodModel model)
    {
        //retrieve data
        holder.tvFoodTitle.setText(model.getTitle());
        holder.tvFoodHours.setText(model.getHours());
        holder.tvFoodReview.setText(model.getReview().toString());
        holder.tvFoodAddress.setText(model.getAddress());
        holder.tvFoodOrder.setText(model.getOrder());
        holder.tvFoodPhone.setText(model.getPhone());

        Glide.with(holder.ivFoodImage.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.ivFoodImage);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext() , FoodDetails.class);
                intent.putExtra("image",model.getImage());
                intent.putExtra("title", model.getTitle());
                intent.putExtra("hours", model.getHours());
                intent.putExtra("review", model.getReview().toString());
                intent.putExtra("address", model.getAddress());
                intent.putExtra("phone", model.getPhone());
                intent.putExtra("order", model.getOrder());
                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent,false);
        return new ViewHolder(v);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        //ui variable
        ImageView ivFoodImage, iv_food_detail;
        TextView tvFoodTitle, tvFoodHours, tvFoodReview, tvFoodAddress, tvFoodPhone, tvFoodOrder;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            ivFoodImage = itemView.findViewById(R.id.iv_foodImage);
            iv_food_detail = itemView.findViewById(R.id.img_food_detail);
            tvFoodTitle = itemView.findViewById(R.id.tv_foodTitle);
            tvFoodReview = itemView.findViewById(R.id.tv_review_ans);
            tvFoodHours = itemView.findViewById(R.id.tv_hours_ans);
            tvFoodAddress = itemView.findViewById(R.id.tv_food_list_address);
            tvFoodPhone = itemView.findViewById(R.id.tv_food_list_phone);
            tvFoodOrder = itemView.findViewById(R.id.tv_food_list_order);

        }
    }
}
