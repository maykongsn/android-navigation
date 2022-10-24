package com.example.android_navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_navigation.entity.Product;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<Product> dataSet;
    MainActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewPrice;

        ImageView imageViewDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }

        public TextView getTextViewName() {
            return textViewName;
        }
        public TextView getTextViewPrice() {
            return textViewPrice;
        }
        public ImageView getImageViewDelete() {
            return imageViewDelete;
        }
    }

    public CustomAdapter(MainActivity activity, ArrayList<Product> data) {
        this.dataSet = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.products_layout, parent, false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        Product product = dataSet.get(position);

        holder.getTextViewName().setText(product.getId() + " - " + product.getName());
        holder.getTextViewPrice().setText("R$ " + product.getPrice());

        holder.getImageViewDelete().setOnClickListener(
                view -> activity.removeProduct(holder.getAdapterPosition())
        );
    }

    @Override
    public int getItemCount() {
        if (dataSet == null)
            return 0;
        return dataSet.size();
    }
}
