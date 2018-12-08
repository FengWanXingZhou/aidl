package wang.jason.server.aidl;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * @author wj
 * @Date: 2018/12/3
 * @Description:
 **/
public class Province implements Parcelable,Comparable<Province> {
    private String mName;
    private int mCode;

    public static final Creator<Province> CREATOR = new Creator<Province>(){

        @Override
        public Province createFromParcel(Parcel source) {
            Province province = new Province();
            province.setName(source.readString());
            province.setCode(source.readInt());
            return province;
        }

        @Override
        public Province[] newArray(int size) {
            return new Province[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mCode);
    }

    public void readFromParcel(Parcel source){
        mName = source.readString();
        mCode = source.readInt();
    }

    @Override
    public int compareTo(@NonNull Province o) {
        if(this.getCode() == o.getCode()
                &&this.getName().equals(o.getName())) {
            return 0;
        }else{
            return 1;
        }
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int mCode) {
        this.mCode = mCode;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }
}
