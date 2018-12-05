package com.hydra3_2.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.hydra3_2.client.answers.AnswersActivity;
import com.hydra3_2.client.utils.PreferenceUtility;

public class MainActivity extends AppCompatActivity {

    EditText profileNameInput;
    Button connectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileNameInput = findViewById(R.id.nameInput);
        connectButton = findViewById(R.id.connectButton);
        String profileName = PreferenceUtility.getUsername(this);
        if (!TextUtils.isEmpty(profileName)) {
            profileNameInput.setText(profileName);
        }
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = profileNameInput.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    PreferenceUtility.setUsername(MainActivity.this, name);
                    openNextScreen();
                    //todo start search activity
                } else {
                    profileNameInput.setError(getString(R.string.profile_name_error_msg));
                }
            }
        });
    }

    private void openNextScreen() {
        Intent intent = new Intent(this, AnswersActivity.class);
        startActivity(intent);
    }

}
