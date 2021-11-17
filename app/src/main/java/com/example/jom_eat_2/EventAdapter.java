package com.example.jom_eat_2;
//event adapter for recycler view at homepage
import android.content.Context;
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

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    ArrayList<Event> event;
    Context context;

    public EventAdapter(ArrayList<Event> event) {
        this.event = event;
    }

    @NonNull
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_event, parent, false);

        return new EventAdapter.ViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.m_tv_event_name.setText((event.get(position).getEvent_name()));
        String pic_location = "";
        switch (position){
            case 0:
                pic_location = "event_deepavali";
                holder.m_event_holder.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 1:
                pic_location = "event_christmas";
                holder.m_event_holder.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

            case 2:
                pic_location = "event_valentine";
                holder.m_event_holder.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_bckgrd));
                break;

        }

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(
                pic_location,"drawable",holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.m_img_event);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView m_tv_event_name;
        ImageView m_img_event;
        ConstraintLayout m_event_holder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            m_tv_event_name = itemView.findViewById(R.id.tv_event_name);
            m_img_event = itemView.findViewById(R.id.img_event);
            m_event_holder = itemView.findViewById(R.id.event_holder);
        }
    }


    @Override
    public int getItemCount() {
        return event.size();
    }

}
