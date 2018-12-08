// INationAidlInterface.aidl
package wang.jason.server.aidl;

// Declare any non-default types here with import statements

import  wang.jason.server.aidl.Country;
import  wang.jason.server.aidl.Province;
import  wang.jason.server.aidl.CountryCallback;
interface INationInterface {


    /**
    *
    * 默认支持的类型
    * java语言的基础类型，int long char boolean等
    * String
    * CharSequence
    * List
    * List 中的所有元素都必须是以上列表中支持的数据类型、其他 AIDL 生成的接口或您声明的可打包类型。
    * 可选择将 List 用作“通用”类（例如，List<String>）。另一端实际接收的具体类始终是 ArrayList，但生成的方法使用的是 List 接口
    * Map
    * Map 中的所有元素都必须是以上列表中支持的数据类型、其他 AIDL 生成的接口或您声明的可打包类型。 不支持通用 Map（如 Map<String,Integer> 形式的 Map）。
    * 另一端实际接收的具体类始终是 HashMap，但生成的方法使用的是 Map 接口
    * */

    Country getCountry();

    int getCountrySize();

    List<Province> getProvinceList();

    Map getProvinceMap();

    Province getProvince();
    /**
    * 传递数据时采用in，表示数据从客户端单向流动到服务端
    * */
    boolean isProvinceExistIn(in Province province);
    /**
    * 传递数据时采用out，表示数据从服务端单向流动到客户端
    * */
    boolean isProvinceExistOut(out Province province);
    /**
    * 传递数据时采用inout，表示数据在客户端和服务端间双向流通
    * */
    boolean isProvinceExistInOut(inout Province province);

    /**
    *注册回调，这里通过aidl实现回调
    **/
    void registerCallback(in CountryCallback callback);
    /**
    * 取消注册回调
    * */
    void unRegisterCallback(in CountryCallback callback);
}
