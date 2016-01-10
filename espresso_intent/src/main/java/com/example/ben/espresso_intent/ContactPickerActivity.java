package com.example.ben.espresso_intent;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactPickerActivity extends AppCompatActivity implements View.OnClickListener {

    private final static  int PICK_CONTACT_REQUEST = 111;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pickBtn = (Button) findViewById(R.id.activity_main_pick);
        pickBtn.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.activity_main_contact_uri);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_CONTACT_REQUEST){
            if(resultCode == RESULT_OK){
                // Get the URI that points to the selected contact
                Uri contactUri = data.getData();
                textView.setText(contactUri.toString());
            }
        }
    }

    private void pickContact(){
        //Pick contact
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if(id == R.id.activity_main_pick){
            pickContact();
        }
    }
}
