// IMyAidlInterface.aidl
package wang.jason.server.aidl;

// Declare any non-default types here with import statements

import  wang.jason.server.aidl.Country;
import  wang.jason.server.aidl.Province;
import  wang.jason.server.aidl.CountryCallback;
interface INationInterface {

    Country getCountry();

    int getCountrySize();

    List<Province> getProvinceList();

    Map getProvinceMap();

    Province getProvince();



    boolean isProvinceExistIn(in Province province);

    boolean isProvinceExistOut(out Province province);

    boolean isProvinceExistInOut(inout Province province);

    void registerCallback(in CountryCallback callback);

    void unRegisterCallback(in CountryCallback callback);
}
