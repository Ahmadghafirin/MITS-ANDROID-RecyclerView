package com.example.ahmad.w;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsMenuActivity extends AppCompatActivity {


    private static final String TAG = DetailsMenuActivity.class.getSimpleName();
    public final int REQUEST_CODE = 1;
    public static final int RESULT_CODE = 5;
    private TextView tvMenu, tvHarga, tvKeterangan;
    private ImageView ivIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsmenu);

        tvMenu = (TextView) findViewById(R.id.tv_menudetails);
        tvHarga = (TextView) findViewById(R.id.tv_hargadetails);
        tvKeterangan = (TextView) findViewById(R.id.tv_keterangan);
        ivIcon = (ImageView) findViewById(R.id.iv_icondetails);

        ItemMenu itemMenu = getIntent().getParcelableExtra(MainActivity.KEY_ITEM);
        getSupportActionBar().setTitle(itemMenu.getMenu());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvMenu.setText(itemMenu.getMenu());
        tvHarga.setText(itemMenu.getHarga());
        tvKeterangan.setText(itemMenu.getKeterangan());
        ivIcon.setImageResource(itemMenu.getIcon());
        Log.d(TAG, "data: " + itemMenu.toString());

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }
}

