package com.example.car;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Carview extends AppCompatActivity {
    private TextView tmptext;
    private TextView humtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carview);
        tmptext=findViewById(R.id.tmptext);
        humtext=findViewById(R.id.humtext);
        final Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    byte[] buff = new byte[1024];
                    DatagramPacket dp = new DatagramPacket(buff, 1024);

                    try {
                        // 接收消息
                        Config.getDs().receive(dp);
                        if (dp.getLength() > 0) {
                            String str = new String(buff, 0, dp.getLength());
                            String type = str.substring(0, 1);
                            if (type.equals("0")) {
                                final String hum = str.substring(1, 3);
                                final String temp = str.substring(7, 9);
                                // 显示消息
                                humtext.post(new Runnable() {

                                    @Override
                                    public void run() {
                                        tmptext.setText(temp+"°");
                                        humtext.setText(hum+"%");
                                    }

                                });
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        });
        th.start();
    }

    public void clickstop(View view) {
        new Stop().execute();
    }

    public void clickforward(View view) {
        new Forward().execute();
    }

    public void clickleft(View view) {
        new Left().execute();
    }

    public void clickright(View view) {
        new Right().execute();
    }

    public void clickback(View view) {
        new Back().execute();
    }
    //前进
    private class Forward extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... args) {

            byte[] buff = "911".getBytes();
            DatagramPacket dp;
            try {
                dp = new DatagramPacket(buff, buff.length,
                        InetAddress.getByName(Config.getServerIp()),
                        Config.getServerPort());
                Config.getDs().send(dp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return null;
        }
    }
    //刹车
    private class Stop extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... args) {

            byte[] buff = "981".getBytes();
            DatagramPacket dp;
            try {
                dp = new DatagramPacket(buff, buff.length,
                        InetAddress.getByName(Config.getServerIp()),
                        Config.getServerPort());
                Config.getDs().send(dp);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            return null;
        }
    }
    //左转
    private class Left extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... args) {

            byte[] buff = "941".getBytes();
            DatagramPacket dp;
            try {
                dp = new DatagramPacket(buff, buff.length,
                        InetAddress.getByName(Config.getServerIp()),
                        Config.getServerPort());
                Config.getDs().send(dp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return null;
        }
    }
    //右转
    private class Right extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... args) {

            byte[] buff = "931".getBytes();
            DatagramPacket dp;
            try {
                dp = new DatagramPacket(buff, buff.length,
                        InetAddress.getByName(Config.getServerIp()),
                        Config.getServerPort());
                Config.getDs().send(dp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return null;
        }
    }
    // 后退
    private class Back extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... args) {

            byte[] buff = "921".getBytes();
            DatagramPacket dp;
            try {
                dp = new DatagramPacket(buff, buff.length,
                        InetAddress.getByName(Config.getServerIp()),
                        Config.getServerPort());
                Config.getDs().send(dp);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return null;
        }
    }
}
