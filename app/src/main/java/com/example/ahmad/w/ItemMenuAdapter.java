package com.example.ahmad.w;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemMenuAdapter extends RecyclerView.Adapter<ItemMenuAdapter.MenuViewHolder> {

    public ArrayList<ItemMenu> dataSet;

    public ItemMenuAdapter(ArrayList<ItemMenu> inputData) {
        dataSet = inputData;
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
        holder.tvHarga.setText(itemMenu.getHarga());
        holder.ivIcon.setImageResource(itemMenu.getIcon());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
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
}

