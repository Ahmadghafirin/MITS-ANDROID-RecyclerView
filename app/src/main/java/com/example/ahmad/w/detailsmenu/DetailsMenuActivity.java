package com.example.ahmad.w.detailsmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmad.w.menu.MenuActivity;
import com.example.ahmad.w.menu.ItemMenu;
import com.example.ahmad.w.R;

public class DetailsMenuActivity extends AppCompatActivity {


    private static final String TAG = DetailsMenuActivity.class.getSimpleName();
    private ItemMenu itemMenu;
    private TextView tvMenu, tvHarga, tvKeterangan;
    private ImageView ivIcon;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsmenu);

        tvMenu = (TextView) findViewById(R.id.tv_menudetails);
        tvHarga = (TextView) findViewById(R.id.tv_hargadetails);
        tvKeterangan = (TextView) findViewById(R.id.tv_keterangan);
        ivIcon = (ImageView) findViewById(R.id.iv_icondetails);

        itemMenu = getIntent().getParcelableExtra("menu");
        getSupportActionBar().setTitle(itemMenu.getMenu());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (itemMenu != null) {
            getSupportActionBar().setTitle(itemMenu.getMenu());
            id = itemMenu.getId();
            tvMenu.setText(itemMenu.getMenu());
            tvHarga.setText(itemMenu.getPrice());
            tvKeterangan.setText(itemMenu.getDetails());
            Glide.with(DetailsMenuActivity.this).load(itemMenu.getImage()).into(ivIcon);
        }

        /*ItemMenu itemMenu = getIntent().getParcelableExtra(MenuActivity.KEY_ITEM);
        getSupportActionBar().setTitle(itemMenu.getMenu());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvMenu.setText(itemMenu.getMenu());
        tvHarga.setText(itemMenu.getPrice());
        tvKeterangan.setText(itemMenu.getDetails());
        ivIcon.setImageResource(Integer.parseInt(itemMenu.getImage()));
        Log.d(TAG, "data: " + itemMenu.toString());*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }
}

