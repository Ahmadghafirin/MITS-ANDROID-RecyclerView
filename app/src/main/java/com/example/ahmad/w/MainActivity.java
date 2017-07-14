package com.example.ahmad.w;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ItemMenu> dataSet = new ArrayList<>();
        final ItemMenuAdapter adapter = new ItemMenuAdapter(dataSet);

        dataSet.add(new ItemMenu("Ayam", "50.000", 4));
        dataSet.add(new ItemMenu("Ayam", "50.000", 1));
        dataSet.add(new ItemMenu("Ayam", "50.000", 7));
        dataSet.add(new ItemMenu("Ayam", "50.000", 2));
        dataSet.add(new ItemMenu("Ayam", "50.000", 5));


        RecyclerView rvItemMenu = (RecyclerView) findViewById(R.id.rv_item);
        rvItemMenu.setLayoutManager(new LinearLayoutManager(this));
        rvItemMenu.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        rvItemMenu.setAdapter(adapter);
        rvItemMenu.addOnItemTouchListener(new RecyclerTouchListener(this, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, dataSet.get(position).getMenu(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, dataSet.get(position).getHarga(), Toast.LENGTH_LONG).show();
                adapter.remove(dataSet, position);
            }
        }, rvItemMenu));

    }
}
