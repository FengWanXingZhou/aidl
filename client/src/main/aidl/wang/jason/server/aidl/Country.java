package wang.jason.server.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author wj
 * @Date: 2018/12/3
 * @Description:
 **/
public class Country implements Parcelable {


    /**
     * `Country`的成员变量包含`List`,`HashMap`和普通的类，要序列化，只需调用`Parcel`的`writeList`,`writeMap`,`writeParcelable`
     * 如果成员变量是`int`,` long`等，只需调用`Parcel`的`writeInt`,`writeLong`
     * */
    private List<Province> mProvinceList;
    private HashMap<Integer,Province> mProvinceMap;
    private Province mProvince;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mProvinceList);
        dest.writeMap(mProvinceMap);
        dest.writeParcelable(mProvince,0);
    }

    public static final Creator<Country> CREATOR = new Creator<Country>(){

        @Override
        public Country createFromParcel(Parcel source) {
            Country country = new Country();
            List<Province> provinceList = new ArrayList<>();
            HashMap<Integer,Province> provinceHashMap = new HashMap<>();
            source.readList(provinceList,getClass().getClassLoader());
            source.readMap(provinceHashMap,getClass().getClassLoader());

            country.setProvinceList(provinceList);
            country.setProvinceMap(provinceHashMap);
            country.setProvince((Province)source.readParcelable(getClass().getClassLoader()));
            //country.setProvinceList(source.readArrayList(Thread.currentThread().getContextClassLoader()));

            return country;
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[0];
        }
    };


    public List<Province> getProvinceList() {
        return mProvinceList;
    }

    public void setProvinceList(List<Province> mProvinceList) {
        this.mProvinceList = mProvinceList;
    }

    public HashMap<Integer, Province> getProvinceMap() {
        return mProvinceMap;
    }

    public void setProvinceMap(HashMap<Integer, Province> mProvinceMap) {
        this.mProvinceMap = mProvinceMap;
    }

    public Province getProvince() {
        return mProvince;
    }

    public void setProvince(Province mProvince) {
        this.mProvince = mProvince;
    }
}
