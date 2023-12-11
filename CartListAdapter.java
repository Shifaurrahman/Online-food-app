package com.example.myfinalproject.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.myfinalproject.Domain.FoodDomain;
import com.example.myfinalproject.Helper.ChangeNumberItemListner;
import com.example.myfinalproject.Helper.ManagementCart;
import com.example.myfinalproject.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    ArrayList<FoodDomain> listFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberItemListner changeNumberItemListner;


    public CartListAdapter(ArrayList<FoodDomain> listFoodSelected, Context context, ChangeNumberItemListner changeNumberItemListner) {
        this.listFoodSelected = listFoodSelected;
        managementCart=new ManagementCart(context);
        this.changeNumberItemListner = changeNumberItemListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {

        holder.title.setText(listFoodSelected.get(position).getTittle());
        holder.feeEachItem.setText("Rs"+listFoodSelected.get(position).getPrice());
        holder.totalEachItem.setText("Rs"+Math.round((listFoodSelected.get(position).getNumberinCart()*listFoodSelected.get(position).getPrice())));
        holder.nun.setText(String.valueOf(listFoodSelected.get(position).getNumberinCart()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(listFoodSelected.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(listFoodSelected, position, new ChangeNumberItemListner() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListner.changed();

                    }
                });

            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(listFoodSelected, position, new ChangeNumberItemListner() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListner.changed();

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,feeEachItem,plusItem,minusItem;
        ImageView pic;
        TextView totalEachItem,nun;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            pic=itemView.findViewById(R.id.pic);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
            nun=itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
