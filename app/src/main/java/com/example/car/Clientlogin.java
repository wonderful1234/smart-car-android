package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Clientlogin extends AppCompatActivity {
    private EditText iptext;
    private EditText porttext;
    private EditText localporttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iptext=findViewById(R.id.iptext);
        porttext=findViewById(R.id.porttext);
        localporttext=findViewById(R.id.localporttext);
    }

    public void clickbu(View sourse) {
        String ip;
        String portt;
        String localportt;
        int port;
        final int localport;
        ip=iptext.getText().toString();
        portt= porttext.getText().toString();
        localportt= localporttext.getText().toString();
        if(Util.isEmpty(ip)||Util.isEmpty(portt)||Util.isEmpty(localportt)){
            Toast.makeText(Clientlogin.this,"字段不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        port=Integer.parseInt(portt);
        localport= Integer.parseInt(localportt);
        Config.setServerIp(ip);
        Config.setServerPort(port);
        Config.setLocalPort(localport);
        Thread th=new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    DatagramSocket sock=new DatagramSocket(localport);
                    Config.setDs(sock);
                    byte[] buff = "A0".getBytes();
                    DatagramPacket dp = new DatagramPacket(buff,
                            buff.length,
                            InetAddress.getByName(Config.getServerIp()),
                            Config.getServerPort());
                    Config.getDs().send(dp);

                }  catch (Exception e) {

                    e.printStackTrace();
                }
            }

        });
        th.start();
        Intent intent=new Intent(Clientlogin.this,Carview.class);
        startActivity(intent);


    }
}
