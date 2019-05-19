package dancheff.android.kraps_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import serverConnectivity.QueryExecutor;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final EditText etEmail = findViewById(R.id.login_et_email);
        final EditText etPassword = findViewById(R.id.login_et_password);

        Button button = findViewById(R.id.login_button);

        TextView registrationLink = findViewById(R.id.login_registrationLink);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                QueryExecutor.logIn(email, password);
            }
        });

        registrationLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Registration.class));
            }
        });
    }
}
