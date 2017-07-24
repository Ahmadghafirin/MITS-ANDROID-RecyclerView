package com.example.ahmad.w.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ahmad.w.R;

import java.io.File;

import pl.aprilapps.easyphotopicker.EasyImage;

public class AddMenuActivity extends AppCompatActivity {

    private EditText etName, etPrice, etDetails;
    private ImageView image;
    private ItemMenu menu = null;
    private int id;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);

        image = (ImageView) findViewById(R.id.img_imagemenu);
        etName = (EditText) findViewById(R.id.et_name_menu);
        etPrice = (EditText) findViewById(R.id.et_price);
        etDetails = (EditText) findViewById(R.id.et_details);

        menu = getIntent().getParcelableExtra("menu");

        if (menu != null) {
            getSupportActionBar().setTitle("Edit ItemMenu");
            id = menu.getId();
            etName.setText(menu.getMenu());
            etPrice.setText(menu.getPrice());
            etDetails.setText(menu.getDetails());
            Glide.with(AddMenuActivity.this).load(menu.getImage()).into(image);
        } else getSupportActionBar().setTitle("Add Menu");
    }

    public void selectImage(View view) {
        EasyImage.openChooserWithDocuments(this, "Select Image", 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new EasyImage.Callbacks() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {

            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {
                onPhotoReturned(imageFile);
            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(AddMenuActivity.this);
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }

    private void onPhotoReturned(File imageFile) {
        if (imageFile != null) {
            Glide.with(this)
                    .load(imageFile)
                    .crossFade()
                    .centerCrop()
                    .into(image);
            path = imageFile.getAbsolutePath();
        }
    }

    public void submitSave(View view) {
        String name, price, details;

        name = etName.getText().toString();
        price = etPrice.getText().toString();
        details = etDetails.getText().toString();

        Intent returnIntent = new Intent();
        if (menu != null) {
            returnIntent.putExtra("Data_Update", new ItemMenu(id, name, price, details, path));
            setResult(MenuActivity.RESULT_UPDATE, returnIntent);
        } else {
            returnIntent.putExtra("Data_Add", new ItemMenu(name, price, details, path));
            setResult(MenuActivity.RESULT_ADD, returnIntent);
        }
        finish();
    }

    public void submitCancel(View view) {
        finish();
    }
}
