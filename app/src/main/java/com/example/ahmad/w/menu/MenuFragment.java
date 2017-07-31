package com.example.ahmad.w.menu;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmad.w.R;
import com.example.ahmad.w.detailsmenu.DetailsMenuActivity;
import com.example.ahmad.w.model.ItemMenu;
import com.example.ahmad.w.recyclerview.RecyclerTouchListener;
import com.example.ahmad.w.recyclerview.SpacesItemDecoration;

import java.util.List;

public class MenuFragment extends Fragment {

    private MenuAdapter adapter;
    private RecyclerView recyclerView;
    private Intent intent;
    public static final int RESULT_ADD = 2;
    public static final int RESULT_UPDATE = 3;
    private int pos;
    private static final String TAG = MenuFragment.class.getSimpleName();
    private long id;
    private boolean isOpenDetail = false;
    private FloatingActionButton fbAddMenu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_item);
        fbAddMenu = (FloatingActionButton) view.findViewById(R.id.fb_add_menu);
        fbAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMenuActivity.class);
                startActivityForResult(intent, RESULT_ADD);
            }
        });
        initRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        isOpenDetail = false;
    }

    public void initRecyclerView() {
        List<ItemMenu> itemMenuList = ItemMenu.getAllMenu();
        adapter = new MenuAdapter(getActivity(), itemMenuList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(getActivity(), R.dimen.space_5));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        if (!isOpenDetail) {
                            intent = new Intent(getActivity(), DetailsMenuActivity.class);
                            ItemMenu menu = adapter.getItem(position);
                            intent.putExtra("menu", menu);
                            startActivity(intent);
                            isOpenDetail = true;
                        }
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        intent = new Intent(getActivity(), AddMenuActivity.class);
                        ItemMenu menu = adapter.getItem(position);

                        if (menu.getId() == null) {
                            id = 0;
                        } else id = menu.getId();

                        intent.putExtra("id_menu", menu.getId());
                        intent.putExtra("menu", menu);
                        pos = position;
                        startActivityForResult(intent, RESULT_UPDATE);
                        Log.d(TAG, "onLongClick: " + menu.toString());
                    }
                }));

        ItemTouchHelper touchHelper = new ItemTouchHelper(simpleCallback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            ItemMenu itemMenu = adapter.getItem(position);
            itemMenu.delete();
            adapter.remove(position);
        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_ADD) {
            ItemMenu menu = data.getParcelableExtra("Data_Add");
            menu.save();
            adapter.insert(menu);
            recyclerView.scrollToPosition(0);
        } else if (resultCode == RESULT_UPDATE) {
            ItemMenu menu = data.getParcelableExtra("Data_Update");
            menu.updateMenu(id, menu);
            adapter.update(pos, menu);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(0);
        }
    }
}