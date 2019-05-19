package dancheff.android.kraps_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import serverConnectivity.ServerConnector;

public class MainActivity extends AppCompatActivity {

    private EditText etServerIP;
    private Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etServerIP = findViewById(R.id.main_et_serverIP);
        btnConnect = findViewById(R.id.main_btn_connect);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ServerConnector.connect(etServerIP.getText().toString());
                startActivity(new Intent(getBaseContext(), LogIn.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        ServerConnector.disconnect();
        super.onDestroy();
    }
}
