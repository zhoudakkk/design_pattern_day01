package app.yjkm.com.day_01;

import android.app.Application;

public class APP extends Application {
    public static BeanText text;
    @Override
    public void onCreate() {
        super.onCreate();
        text = new BeanText();
        text.name = "app";
    }
}
