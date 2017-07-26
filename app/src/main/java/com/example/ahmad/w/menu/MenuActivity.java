package com.example.ahmad.w.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.ahmad.w.R;
import com.example.ahmad.w.database.DataBaseHandler;
import com.example.ahmad.w.detailsmenu.DetailsMenuActivity;
import com.example.ahmad.w.recyclerview.RecyclerTouchListener;
import com.example.ahmad.w.recyclerview.SpacesItemDecoration;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    public static final String KEY_ITEM = "Item";
    private MenuAdapter adapter;
    private RecyclerView recyclerView;
    private Intent intent;
    public static final int RESULT_ADD = 2;
    public static final int RESULT_UPDATE = 3;
    private DataBaseHandler baseHandler;
    private int pos;
    private static final String TAG = MenuActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        baseHandler = DataBaseHandler.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.rv_item);
        for (ItemMenu itemMenu : baseHandler.getAllMenu()) {
            Log.d(TAG, "List" + itemMenu.toString());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
    }

    public void initRecyclerView() {
        List<ItemMenu> itemMenuList = baseHandler.getAllMenu();
        adapter = new MenuAdapter(MenuActivity.this, itemMenuList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        intent = new Intent(MenuActivity.this, DetailsMenuActivity.class);
                        ItemMenu menu = adapter.getItem(position);

                        intent.putExtra("menu", menu);
                        /*pos = position;*/
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        intent = new Intent(MenuActivity.this, AddMenuActivity.class);
                        ItemMenu menu = adapter.getItem(position);

                        intent.putExtra("menu", menu);
                        pos = position;
                        startActivityForResult(intent, RESULT_UPDATE);
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
            baseHandler.deleteMenuById(adapter.getItem(position).getId());
            adapter.remove(position);
        }

    };

    public void submitAddMenu(View view) {
        Intent intent = new Intent(this, AddMenuActivity.class);
        startActivityForResult(intent, RESULT_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_ADD) {
            ItemMenu menu = data.getParcelableExtra("Data_Add");
            baseHandler.addMenu(menu);
            adapter.insert(menu);
            recyclerView.scrollToPosition(0);
        } else if (resultCode == RESULT_UPDATE) {
            ItemMenu menu = data.getParcelableExtra("Data_Update");
            baseHandler.updateMenu(new ItemMenu(menu.getId(), menu.getMenu(), menu.getPrice(),
                    menu.getDetails(), menu.getImage()));
            adapter.update(pos, menu);
            recyclerView.scrollToPosition(0);
        }
    }
}