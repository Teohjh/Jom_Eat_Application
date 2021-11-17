package com.example.jom_eat_2;

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

public class CommentAdapter extends FirebaseRecyclerAdapter<CommentModel, CommentAdapter.ViewHolder>
{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CommentAdapter(@NonNull FirebaseRecyclerOptions<CommentModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull CommentModel model)
    {
        holder.tvCommentText.setText(model.getComment());
        holder.tvRatingNumber.setText(model.getRating().toString());

        Glide.with(holder.ivCommentUser.getContext())
                .load(model.getUserImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.ivCommentUser);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent,false);
        return new ViewHolder(v);
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivCommentUser;
        TextView tvCommentText , tvRatingNumber;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            ivCommentUser = itemView.findViewById(R.id.ivCommentPerson);
            tvCommentText = itemView.findViewById(R.id.tvCommentText);
            tvRatingNumber = itemView.findViewById(R.id.tvRatingNumber);

        }
    }
}
