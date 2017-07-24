package com.example.ahmad.w.categorymenu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahmad.w.R;

import java.util.ArrayList;

/**
 * Created by ahmad on 15/07/17.
 */

public class CategoryMenuAdapter extends RecyclerView.Adapter<CategoryMenuAdapter.CategoryMenuViewHolder> {

    public ArrayList<CategoryMenu> categorySet;

    public CategoryMenuAdapter(ArrayList<CategoryMenu> inputData) {
        categorySet = inputData;
    }

    public void remove(ArrayList<CategoryMenu> categorySet, int index) {
        this.categorySet = categorySet;
    }

    @Override
    public CategoryMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_menu, parent, false);
        return new CategoryMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryMenuViewHolder holder, int position) {
        CategoryMenu categoryMenu = categorySet.get(position);
        holder.tvMenu.setText(categoryMenu.getName());
        holder.ivIcon.setImageResource(categoryMenu.getImage());
    }

    @Override
    public int getItemCount() {
        return categorySet.size();
    }

    public class CategoryMenuViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;
        private TextView tvMenu;

        public CategoryMenuViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_iconcategory);
            tvMenu = (TextView) itemView.findViewById(R.id.tv_menucategory);

        }
    }
}
