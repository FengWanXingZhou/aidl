package com.example.bindpoolclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bindpoolserver.IBinderPool;
import com.example.bindpoolserver.INameBinder;
import com.example.bindpoolserver.IPriceBinder;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "BindPool";
    private Button mBindBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBindBtn = findViewById(R.id.bind);
        mBindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.bindpoolserver.BindPoolService");
                intent.setPackage("com.example.bindpoolserver");
                MainActivity.this.bindService(intent,mServiceConnection,BIND_AUTO_CREATE);
            }
        });

    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBinderPool binderPool = IBinderPool.Stub.asInterface(service);
            if(binderPool!=null){
                try {
                    INameBinder nameBinder = (INameBinder.Stub.asInterface( binderPool.queryBinder(0)));
                    IPriceBinder priceBinder = (IPriceBinder.Stub.asInterface(binderPool.queryBinder(1))) ;
                    if(nameBinder!=null){
                        Log.i(TAG,"name = "+nameBinder.getBookName());
                    }
                    if(priceBinder!=null){
                        Log.i(TAG,"price = "+priceBinder.getBookPrice());
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
