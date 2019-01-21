package com.example.bindpoolserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class BindPoolService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

    public IBinderPool.Stub mStub = new IBinderPool.Stub() {
        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode){
                case 0:{
                    binder = new BookNameBinder();
                    break;
                }
                case 1:{
                    binder = new BookPriceBinder();
                    break;
                }
                default:break;
            }

            return  binder;


        }
    };


    public static class BookNameBinder extends INameBinder.Stub{
        @Override
        public String getBookName() throws RemoteException {
            return "math";
        }
    }

    public static class BookPriceBinder extends IPriceBinder.Stub{


        @Override
        public int getBookPrice() throws RemoteException {
            return 19;
        }
    }

}
