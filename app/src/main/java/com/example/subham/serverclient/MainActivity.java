package com.example.subham.serverclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.EOFException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
        private EditText editText_ip,editText_port,editText_mess;
    private String ipadress;
    private int port=0;

    private Socket client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_ip=(EditText)findViewById(R.id.ip);
        editText_port=(EditText)findViewById(R.id.port);
        editText_mess=(EditText)findViewById(R.id.message);

            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ipadress=editText_ip.getText().toString();
                    port=Integer.valueOf(editText_port.getText().toString());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                client = new Socket(ipadress, port);

                                PrintWriter printWriter = new PrintWriter(client.getOutputStream());
                                printWriter.write("ITS GINNEE");
                                printWriter.flush();
                                printWriter.close();
                                client.close();
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            });
    }
}
