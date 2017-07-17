package com.example.ahmad.w;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_ITEM = "Item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<ItemMenu> dataSet = new ArrayList<>();
        final ItemMenuAdapter adapter = new ItemMenuAdapter(dataSet);

        dataSet.add(new ItemMenu("Ayam Goreng", "Rp 25.000", "Ayam Goreng yang di sajikan dengan " +
                "Sambal serta irisan Timun dan Tomat", R.drawable.ayam_goreng));
        dataSet.add(new ItemMenu("Bebek Goreng", "Rp 27.000", "Bebek Goreng yang di sajikan " +
                "dengan Sambal serta irisan Timun dan Tomat", R.drawable.bebek_goreng));
        dataSet.add(new ItemMenu("Nasi Goreng", "Rp 24.000", "Nasi Goreng yang di beri irisan" +
                " Udang dan bakso serta Tomat dan Kerupuk", R.drawable.nasi_goreng));
        dataSet.add(new ItemMenu("Nasi Uduk", "Rp 15.000", "Nasi Uduk yang di sajikan dengan " +
                "Sambal dan Tempe,Telur Goreng serta irisan Tomat dan Orek", R.drawable.nasi_uduk));
        dataSet.add(new ItemMenu("Mie Goreng", "Rp 24.000", "Mie Goreng yang di beri " +
                "irisan Bakso dan Tomat", R.drawable.mie_goreng));


        RecyclerView rvItemMenu = (RecyclerView) findViewById(R.id.rv_item);
        rvItemMenu.setLayoutManager(new LinearLayoutManager(this));
        rvItemMenu.addItemDecoration(new SpacesItemDecoration(this, R.dimen.space_5));
        rvItemMenu.setAdapter(adapter);
        rvItemMenu.addOnItemTouchListener(new RecyclerTouchListener(this, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ItemMenu itemMenu = dataSet.get(position);
                Intent intent = new Intent(MainActivity.this, DetailsMenuActivity.class);
                intent.putExtra(KEY_ITEM, itemMenu);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                adapter.remove(dataSet, position);
            }
        }, rvItemMenu));

    }
}
