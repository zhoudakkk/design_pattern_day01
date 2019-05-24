package app.yjkm.com.day_01;

import android.util.Log;

/**
 * 懒汉模式
 */
public class AppUtils_2 {

    public static String TAG = "text123";

    public AppUtils_2() {
    }

    static {
        Log.e(TAG, "static initializer: AppUtils_2 加载");
    }

    public static class AppUtils_2Holder {

        static {
            Log.e(TAG, "static initializer: AppUtils_2Holder 加载");
        }

        public BeanText beanText;
        private static final AppUtils_2 UTILS_2 = new AppUtils_2();
    }

    public static AppUtils_2 getInstance() {
        return AppUtils_2Holder.UTILS_2;
    }
}
