package serverConnectivity;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnector {
    private static String serverIP;
    private static Socket socket = null;
    private static DataOutputStream output = null;
    private static DataInputStream input = null;

    public static void connect(String ip) {
        ServerConnector.serverIP = ip;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerConnector.socket = new Socket(ServerConnector.serverIP, 2345);
                    ServerConnector.output = new DataOutputStream(ServerConnector.socket.getOutputStream());
                    ServerConnector.input = new DataInputStream(ServerConnector.socket.getInputStream());
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


    public static void disconnect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerConnector.output.writeUTF("EXIT");
                    ServerConnector.socket.close();
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

}
