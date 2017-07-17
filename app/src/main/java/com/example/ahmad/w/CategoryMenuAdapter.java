package com.example.ahmad.w;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ahmad on 15/07/17.
 */

public class CategoryMenuAdapter extends RecyclerView.Adapter<CategoryMenuAdapter.CategoryMenuViewHolder> {

    private ArrayList<CategoryMenu> categorySet;

    public CategoryMenuAdapter(ArrayList<CategoryMenu> inputData) {
        categorySet = inputData;
    }

    @Override
    public CategoryMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_menu, parent, false);
        return new CategoryMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryMenuViewHolder holder, int position) {
        CategoryMenu categoryMenu = categorySet.get(position);
        holder.tvMenu.setText(categoryMenu.getMenu());
        holder.ivIcon.setImageResource(categoryMenu.getIcon());
    }

    @Override
    public int getItemCount() {
        return 0;
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
