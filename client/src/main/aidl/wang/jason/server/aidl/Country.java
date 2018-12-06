package wang.jason.server.aidl;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wj
 * @Date: 2018/12/3
 * @Description:
 **/
public class Country implements Parcelable {

    private List<Province> mProvinceList;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mProvinceList);

    }

    public static final Creator<Country> CREATOR = new Creator<Country>(){

        @Override
        public Country createFromParcel(Parcel source) {
            Country country = new Country();
            List<Province> provinceList = new ArrayList<>();
            source.readList(provinceList,getClass().getClassLoader());
            country.setProvinceList(provinceList);
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
}
