package wang.jason.server.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import wang.jason.server.aidl.Country;
import wang.jason.server.aidl.CountryCallback;
import wang.jason.server.aidl.INationInterface;
import wang.jason.server.aidl.Province;

import java.util.ArrayList;
import java.util.List;

public class NationService extends Service {
    private static final String TAG ="aidl";
    private static Country  mCountry =new Country();
    private RemoteCallbackList<CountryCallback> mCountryCallbckList = new RemoteCallbackList<>();
    static{
        List<Province>  provinces = new ArrayList<>(3);
        Province province = new Province();
        province.setCode(0);
        province.setName("beijing");
        provinces.add(province);
        province = new Province();
        province.setCode(1);
        province.setName("shanghai");
        provinces.add(province);
        province = new Province();
        province.setCode(2);
        province.setName("chongqing");
        provinces.add(province);
        mCountry.setProvinceList(provinces);
    }
    public NationService() {



    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return stub;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    private INationInterface.Stub stub = new INationInterface.Stub() {
        @Override
        public int getCountrySize() throws RemoteException {
            if(mCountry.getProvinceList()!=null){
                return mCountry.getProvinceList().size();
            }else{
                return 0;
            }

        }

        @Override
        public Province getProvince(int code) throws RemoteException {
            if(mCountry.getProvinceList()!=null&&mCountry.getProvinceList().size()>0){
                for(Province province:mCountry.getProvinceList()){
                    if(province.getCode() == code){
                        return province;
                    }
                }
            }
            return null;
        }


        @Override
        public Country getCountry() throws RemoteException {
            return mCountry;
        }

        @Override
        public boolean isProvinceExistIn(Province province) throws RemoteException {
            Log.i(TAG,"server isProvinceExistIn province:"+province
                    +" code:"+province.getCode()+" name:"+province.getName());
            if(mCountry.getProvinceList()!=null&&mCountry.getProvinceList().size()>0
                    &&province !=null){
                for(Province provinceCache:mCountry.getProvinceList()){
                    if(provinceCache.getCode() == province.getCode()
                            &&provinceCache.getName().equals(province.getName())){
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean isProvinceExistOut(Province province) throws RemoteException {
            Log.i(TAG,"server isProvinceExistOut province:"+province+" code:"+province.getCode()+" name:"+province.getName());


            if(mCountry.getProvinceList()!=null&&mCountry.getProvinceList().size()>0
                    &&province !=null){
                for(Province provinceCache:mCountry.getProvinceList()){
                    if(provinceCache.getCode() == province.getCode()
                            &&provinceCache.getName().equals(province.getName())){
                        return true;
                    }
                }
            }
            province.setCode(4);
            province.setName("chengdu");
            return false;
        }

        @Override
        public boolean isProvinceExistInOut(Province province) throws RemoteException {
            Log.i(TAG,"server isProvinceExistInOut province:"+province+" code:"+province.getCode()+" name:"+province.getName());
            if(mCountry.getProvinceList()!=null&&mCountry.getProvinceList().size()>0
                    &&province !=null){
                for(Province provinceCache:mCountry.getProvinceList()){
                    if(provinceCache.getCode() == province.getCode()
                            &&provinceCache.getName().equals(province.getName())){
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void registerCallback(CountryCallback callback) throws RemoteException {
            mCountryCallbckList.register(callback);
        }

        @Override
        public void unRegisterCallback(CountryCallback callback) throws RemoteException {
            mCountryCallbckList.unregister(callback);
        }
    };





}
