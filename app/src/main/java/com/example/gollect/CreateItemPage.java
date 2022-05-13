package com.example.gollect;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.UUID;

public class CreateItemPage extends SelectedCollectionPage implements View.OnClickListener {

    private TextView createItem;
    private EditText editTextItemName, editTextItemType, editTextItemDescription, editTextItemDate;
    private ProgressBar progressBar;
    private ImageView imageView;
    private Button takePhoto;
    private Bitmap imageBitmap;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item_page);

        editTextItemName = (EditText) findViewById(R.id.create_item_name);
        editTextItemType = (EditText) findViewById(R.id.create_item_type);
        editTextItemDescription = (EditText) findViewById(R.id.create_item_description);
        editTextItemDate = (EditText) findViewById(R.id.create_item_date);
        imageView = (ImageView) findViewById(R.id.itemPhoto);

        mContext = getApplicationContext();

        createItem = (Button) findViewById(R.id.createItem);
        createItem.setOnClickListener(this);

        takePhoto = (Button) findViewById(R.id.takePhoto);
        takePhoto.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (ContextCompat.checkSelfPermission(CreateItemPage.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreateItemPage.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);

        }

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();

            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, 100);
        } catch (ActivityNotFoundException e) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createItem:
                CreateTheItem();

                break;
        }
    }

    private void CreateTheItem()  {

        String iName = editTextItemName.getText().toString().trim();
        String iType = editTextItemType.getText().toString().trim();
        String iDescription = editTextItemDescription.getText().toString().trim();
        String iDate = editTextItemDate.getText().toString().trim();

        if (iName.isEmpty()) {
            editTextItemName.setError("Collection name is required");
            editTextItemName.requestFocus();
            return;
        }
        if (iType.isEmpty()) {
            editTextItemType.setError("Collection type is required");
            editTextItemType.requestFocus();
            return;
        }
        if (iDescription.isEmpty()) {
            editTextItemDescription.setError("Collection goal is required");
            editTextItemDescription.requestFocus();
            return;
        }
        if (iDate.isEmpty()) {
            editTextItemDate.setError("Collection goal is required");
            editTextItemDate.requestFocus();
            return;
        }
        String uniqueString = UUID.randomUUID().toString();
        try {
            saveImage(imageBitmap, uniqueString);
            itemCreator(iName, iType, iDescription, iDate, uniqueString);
            startActivity(new Intent(this, SelectedCollectionPage.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //https://stackoverflow.com/questions/63776744/save-bitmap-image-to-specific-location-of-gallery-android-10
    private void saveImage(Bitmap bitmap, @NonNull String name) throws IOException {
        boolean saved;
        OutputStream fos;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = mContext.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/" + "Gollect");
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            fos = resolver.openOutputStream(imageUri);
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM).toString() + File.separator +  "Gollect";

            File file = new File(imagesDir);

            if (!file.exists()) {
                file.mkdir();
            }

            File image = new File(imagesDir, name + ".png");
            fos = new FileOutputStream(image);

        }
        saved = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.flush();
        fos.close();
    }
}