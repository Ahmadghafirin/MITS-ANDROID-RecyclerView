package com.example.ahmad.w.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmad.w.R;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    public List<ItemMenu> dataSet;
    private Context context;

    public MenuAdapter(ArrayList<ItemMenu> inputData) {
        dataSet = inputData;
    }

    public MenuAdapter(Context context, List<ItemMenu> dataset) {
        this.context = context;
        this.dataSet = dataset;
    }

    public void remove(ArrayList<ItemMenu> dataSet, int index) {
        this.dataSet = dataSet;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        ItemMenu itemMenu = dataSet.get(position);
        holder.tvMenu.setText(itemMenu.getMenu());
        holder.tvHarga.setText(itemMenu.getPrice());
        Glide.with(context).load(itemMenu.getImage()).into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public ItemMenu getItem(int position) {
        return dataSet.get(position);
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView tvMenu, tvHarga;
        ImageView ivIcon;

        public MenuViewHolder(View itemView) {
            super(itemView);

            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvMenu = (TextView) itemView.findViewById(R.id.tv_menu);
            tvHarga = (TextView) itemView.findViewById(R.id.tv_harga);
        }
    }

    public void insert(ItemMenu newItemMenu){
        dataSet.add(0, newItemMenu);
        notifyItemInserted(0);
    }

    public void remove(int position){
        dataSet.remove(position);
        notifyItemRemoved(position);
    }

    public void update(int position, ItemMenu itemMenu){
        dataSet.set(position, itemMenu);
        notifyItemChanged(position);
    }
}

