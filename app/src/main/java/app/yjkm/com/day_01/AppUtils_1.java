package app.yjkm.com.day_01;

/**
 * 懒汉模式
 */
public class AppUtils_1 {

    private static AppUtils_1 APP_UTILS_1;

    public static synchronized AppUtils_1 getInstance () {
        if (APP_UTILS_1 == null) {
            APP_UTILS_1 = new AppUtils_1();
        }

        return APP_UTILS_1;
    }
}
