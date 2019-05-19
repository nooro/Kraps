package serverConnectivity;

import android.util.Log;

import java.io.IOException;

public class QueryExecutor {
    private static String response;

    private static void execute(final String query) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerConnector.output.writeUTF(query);
                    QueryExecutor.response = ServerConnector.input.readUTF();
                    Log.v("log", "Response: " + QueryExecutor.response);
                } catch (IOException e) {
                    Log.v("log", e.getMessage());
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.v("log", e.getMessage());
        }
    }


    public static void logIn(String email, String password) {
        final String query = "login/" + email + "/" + password;
        QueryExecutor.execute(query);
    }


    public static void register(String name, String email, String password, String iban, String drivingLicensePhoto) {
        final String query = "register/" + name + "/" + email + "/" + password + "/" + iban;
        QueryExecutor.execute(query);
    }
}
