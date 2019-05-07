package app.yjkm.com.day_01;

/**
 * 懒汉模式
 */
public class AppUtils_1_1 {
    private static AppUtils_1_1 APP_UTILS_1_1;

    public static  AppUtils_1_1 getInstance () {
        if (APP_UTILS_1_1 == null) {
            synchronized (AppUtils_1_1.class){
                if (APP_UTILS_1_1 == null) {
                    APP_UTILS_1_1 = new AppUtils_1_1();
                }
            }
        }
        return APP_UTILS_1_1;
    }
}
