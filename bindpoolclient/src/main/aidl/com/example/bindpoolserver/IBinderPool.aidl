// IBinderPool.aidl
package com.example.bindpoolserver;

// Declare any non-default types here with import statements
import android.os.IBinder;
interface IBinderPool {

    IBinder queryBinder(int binderCode);

}
