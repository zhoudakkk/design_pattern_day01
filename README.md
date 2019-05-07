# android的单例模式学习

## 1 饿汉单例模式

```
public class AppUtils_0 {
    
    private static final AppUtils_0 APP_UTILS_0 = new AppUtils_0();

    private AppUtils_0() {
        Log.e("text123", "AppUtils_0: " );
    }

    public static AppUtils_0 getInstance() {
        return APP_UTILS_0;
    }

 }
 ```
 
 #### 优点 : 
            类在被加载的时候，单件模式实例就已经被创建。
            如果单件模式实例在系统中经常会被用到，饿汉式是一个不错的选择。
 #### 缺点 : 
             类在被加载的时候，单件模式实例就已经被创建,会占用资源
             如果此类用的不是很频繁就亏了 
 
 ## 2.0 懒汉模式
 ```
 public class AppUtils_1 {
    
    private static AppUtils_1 APP_UTILS_1;

    public static synchronized AppUtils_1 getInstance () {
        if (APP_UTILS_1 == null) {
            APP_UTILS_1 = new AppUtils_1();
        }

        return APP_UTILS_1;
    }
}
```
 #### 优点 : 
            这样写的单例模式只有在使用的时候才会被实例化,在一定程度上节约了资源
            
 #### 缺点 : 
             第一次加载的时候需要及时进行实例化,反应稍慢,最大的问题是每次调用getInstance 的时候都会同步
             这样就会造成不必要的同步开销,所以这样的写法不建议使用.
             
 ## 2.1 懒汉模式   (改进版)



