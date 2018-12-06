// CountryCallback.aidl
package wang.jason.server.aidl;

// Declare any non-default types here with import statements
import  wang.jason.server.aidl.Country;
//parcelable CountryCallback;
interface CountryCallback {

    void notifyCountryChange(in  Country country);

}
