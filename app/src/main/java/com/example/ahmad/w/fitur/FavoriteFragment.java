package com.example.ahmad.w.fitur;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmad.w.R;
import com.example.ahmad.w.model.ItemMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmad on 30/07/17.
 */

public class FavoriteFragment extends Fragment {

    private MenuAdapter adapter;
    private RecyclerView recyclerView;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_viewFavorite);
        context = container.getContext();

        initView();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((NavDrawerActivity) getActivity()).setActionBarTitle("Favorite");
    }

    private void initView(){

        List<ItemMenu> list;
        list = new ArrayList<>();
    }
}
