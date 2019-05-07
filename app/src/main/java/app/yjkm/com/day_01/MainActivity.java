package app.yjkm.com.day_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 单例模式测试项目
 */
public class MainActivity extends AppCompatActivity {

    private String TAG = "text123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppUtils_0.getInstance();
    }
}
