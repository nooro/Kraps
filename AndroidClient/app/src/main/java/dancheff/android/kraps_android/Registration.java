package dancheff.android.kraps_android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Registration extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText etName = findViewById(R.id.registration_et_name);
        EditText etEmail = findViewById(R.id.registration_et_email);
        EditText etPassword = findViewById(R.id.registration_et_password);
        EditText etPasswordRepetition = findViewById(R.id.registration_et_password_repetition);
        EditText etIBAN = findViewById(R.id.registration_et_iban);

        Button button = findViewById(R.id.registration_button);
        Button selectImageButton = findViewById(R.id.registration_selectImageButton);

        TextView loginLink = findViewById(R.id.registration_loginLink);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select image"), PICK_IMAGE);
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LogIn.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK) {

        }
    }

}
