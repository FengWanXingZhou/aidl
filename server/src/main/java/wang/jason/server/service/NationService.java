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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NationService extends Service {
    private static final String TAG ="aidl";
    private static Country  mCountry =new Country();
    private RemoteCallbackList<CountryCallback> mCountryCallbckList = new RemoteCallbackList<>();
    static{
        List<Province>  provinces = new ArrayList<>(3);
        HashMap<Integer,Province> provinceHashMap = new HashMap<>();
        Province province = new Province();
        province.setCode(0);
        province.setName("beijing");
        provinces.add(province);
        provinceHashMap.put(0,province);
        province = new Province();
        province.setCode(1);
        province.setName("shanghai");
        provinces.add(province);
        provinceHashMap.put(1,province);
        province = new Province();
        province.setCode(2);
        province.setName("chongqing");
        provinces.add(province);
        provinceHashMap.put(2,province);
        mCountry.setProvinceList(provinces);
        mCountry.setProvinceMap(provinceHashMap);
        mCountry.setProvince(province);


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
        public List<Province> getProvinceList() throws RemoteException {
            return mCountry.getProvinceList();
        }

        @Override
        public Map getProvinceMap() throws RemoteException {
            return mCountry.getProvinceMap();
        }

        @Override
        public Province getProvince() throws RemoteException {

            return mCountry.getProvince();
        }


        @Override
        public Country getCountry() throws RemoteException {
            return mCountry;
        }

        @Override
        public boolean isProvinceExistIn(Province province) throws RemoteException {
            Log.i(TAG,"server isProvinceExistIn province:"+province
                    +" code:"+province.getCode()+" name:"+province.getName());
            if(mCountry.getProvince()!=null
                    &&mCountry.getProvince().compareTo(province) == 0){
                return true;
            }
            return false;
        }

        @Override
        public boolean isProvinceExistOut(Province province) throws RemoteException {
            Log.i(TAG,"server isProvinceExistOut province:"+province+" code:"+province.getCode()+" name:"+province.getName());

            if(mCountry.getProvince()!=null
                    &&mCountry.getProvince().compareTo(province) == 0){
                return true;
            }

            province.setCode(4);
            province.setName("chengdu");
            return false;
        }

        @Override
        public boolean isProvinceExistInOut(Province province) throws RemoteException {
            Log.i(TAG,"server isProvinceExistInOut province:"+province+" code:"+province.getCode()+" name:"+province.getName());
            if(mCountry.getProvince()!=null
                    &&mCountry.getProvince().compareTo(province) == 0){
                return true;
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
