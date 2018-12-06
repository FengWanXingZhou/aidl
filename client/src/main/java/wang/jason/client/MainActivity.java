package wang.jason.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import wang.jason.server.aidl.Country;
import wang.jason.server.aidl.INationInterface;
import wang.jason.server.aidl.Province;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="aidl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setPackage("wang.jason.server");
        intent.setAction("wang.jason.service.NationService");
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            INationInterface nationInterface = INationInterface.Stub.asInterface(service);
            try {
                Country country = nationInterface.getCountry();

                Province province = nationInterface.getProvince(1);
                Log.i(TAG,"client province code = "+province.getCode()+" name:"+province.getName());
                boolean provinceExistIn =  nationInterface.isProvinceExistIn(province);
                Log.i(TAG,"client provinceExistIn = "+provinceExistIn);
                boolean provinceExistOut =  nationInterface.isProvinceExistOut(province);
                Log.i(TAG,"client provinceExistOut = "+provinceExistOut+" code:"+province.getCode()+" name:"+ province.getName());
                boolean provinceExistInout =  nationInterface.isProvinceExistInOut(province);
                Log.i(TAG,"client provinceExistInout = "+provinceExistInout);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
