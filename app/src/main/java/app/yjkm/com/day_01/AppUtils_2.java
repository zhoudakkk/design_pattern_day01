package app.yjkm.com.day_01;

/**
 * 懒汉模式
 */
public class AppUtils_2 {

    private AppUtils_2() {
    }
    private static class AppUtils_2Holder {
        private static final AppUtils_2 UTILS_2 = new AppUtils_2();
    }

    public static AppUtils_2 getInstance() {
        return AppUtils_2Holder.UTILS_2;
    }
}
