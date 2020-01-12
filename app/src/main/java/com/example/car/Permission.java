package com.example.car;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Permission extends AppCompatActivity {

    private static List<String> sNeedPermissions=new ArrayList<>();

    private PermissionUtils permissionUtils= null;

    //静态块中初始化所需要的权限
    static {
        sNeedPermissions.add(Manifest.permission.INTERNET);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        permissionUtils=new PermissionUtils(this);

        permissionUtils.request(sNeedPermissions, 100, new PermissionUtils.CallBack() {
            @Override
            public void grantAll() {
                toMainActivity();
                finish();
            }

            @Override
            public void denied() {
                fileList();
            }
        });

    }


    public void toMainActivity(){
        //进入主Activity
        startActivity(new Intent(Permission.this,Clientlogin.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}

