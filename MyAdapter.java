package com.example.finalsupport;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<MyItems> items;
    private final Context context;

    public MyAdapter(List<MyItems> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerview_adapter_layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        MyItems myItems=items.get(position);

        holder.foodname.setText(myItems.getFoodname());
        holder.price.setText(myItems.getPrice());
        holder.duration.setText(myItems.getDuration());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), information.class);
                String foo= myItems.getFoodname();
                String price= myItems.getPrice();

                intent.putExtra("FOO",foo);
                intent.putExtra("PRICE",price);


                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView foodname,price,duration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foodname=itemView.findViewById(R.id.foodnameTV);
            price=itemView.findViewById(R.id.priceTV);
            duration=itemView.findViewById(R.id.durationTV);
        }
    }
}
