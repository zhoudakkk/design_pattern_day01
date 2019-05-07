package app.yjkm.com.day_01;

import android.util.Log;

/**
 * 饿汉单例模式
 * 在程序启动或单件模式类被加载的时候，单件模式实例就已经被创建。
 */
public class AppUtils_0 {

    static {
        Log.e("text123", "AppUtils_0----我被加载了" );
    }
    /**
     * 问题 java中new一个类 jvm是怎么分配内存的? 有static 会怎么样?
     *
     * static修饰符能够与属性、方法和内部类一起使用，表示静态的。
     * 类中的静态变量和静态方法能够与类名一起使用，
     * 不需要创建一个类的对象来访问该类的静态成员，
     * 所以，static修饰的变量又称作“类变量”。
     *
     * 一个类中，一个static变量只会有一个内存空间，
     * 虽然有多个类实例，但这些类实例中的这个static变量会共享同一个内存空间。
     */
    private static final AppUtils_0 APP_UTILS_0 = new AppUtils_0();

    private AppUtils_0() {
        Log.e("text123", "AppUtils_0: " );
    }

    public static AppUtils_0 getInstance() {
        return APP_UTILS_0;
    }

}
