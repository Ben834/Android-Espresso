package com.ben.testing.espresso.intent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ben.espresso_intent.R;

/**
 * A simple {@link AppCompatActivity} allowing the user to pick a contact or to select an image.
 */
public class ContactPickerActivity extends AppCompatActivity implements View.OnClickListener {

    @VisibleForTesting
    protected static final String KEY_IMAGE_DATA = "data";

    private final static int PICK_CONTACT_REQUEST = 111;
    private final static int SELECT_IMAGE_REQUEST = 222;

    private TextView textView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pickBtn = (Button) findViewById(R.id.activity_main_pick);
        pickBtn.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.activity_main_contact_uri);
        mImageView = (ImageView) findViewById(R.id.activity_main_picture_pick);
        mImageView.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                textView.setText(contactUri.toString());
            }
        } else if (requestCode == SELECT_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                if(extras == null || !extras.containsKey(KEY_IMAGE_DATA)){
                    return;
                }
                Bitmap bm = (Bitmap) extras.get(KEY_IMAGE_DATA);
                mImageView.setImageBitmap(bm);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Opens an intent displaying the contacts.
     */
    private void dispatchContactIntent() {
        //Pick contact
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    /**
     * Opens the camera to take a photo
     */
    private void dispatchPictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, SELECT_IMAGE_REQUEST);
        }
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.activity_main_pick) {
            dispatchContactIntent();
        } else if (id == R.id.activity_main_picture_pick) {
            dispatchPictureIntent();
        }
    }
}
