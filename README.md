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
 
 ```
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
```
#### 对比
         相对对于上一个模式这个模式在getInstance方法中对instance进行了两次判空
         第一次判断是为了避免不必要的同步 第二次就是为了判断在null的情况下创建实例
         这样就避免了反复同步也达到了单例的要求.
        
#### 隐患
         这个写法大概做了3件事情
         1 给AppUtils 的实例分配内存
         2 调用AppUtils的构造函数,初始化成员字段
         3 将APP_UTILS_1_1对象指向分配的内存空间
         但是 java的编译器允许处理器乱序执行以及Cache,
         寄存器到内存回写内存顺序规定上面的 2 和3 是没有先后的顺序的
         就是说 执行顺序可以是 123 也可以是132 
         就是在两个线程中同时执行getInstance()函数 假设a线程在执行很快 APP_UTILS_1_1就不会是空了 
         b线程就会直接取走APP_UTILS_1_1在使用就会有问题了
        
        
#### 优点 : 
            资源利用率高,第一次执行getInstance是单例对象才会被实例化
            
#### 缺点 : 
             第一次加载反应稍慢,也由于java内存模型的原因偶尔加载失败
             在高并发环境下也有一定的缺陷,虽然概率小

## 3 静态内部单例模式

```
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

```
#### 说明 : 
           当第一次加载AppUtils_2类是并不会初始化 UTILS_2 只有在第一次调用AppUtils_2的getInstance
           才会初始化 UTILS_2 类 因此第一次调用getInstance会导致虚拟机加载AppUtils_2Holder类
           这样的方法不仅保证线程安全也能保证单例对象唯一性同时也实现了单例的实例化
           所以推荐使用这个方法
           
# static在内存中是怎么分配的   

  为什么静态修饰后反复获取这个类 就是唯一的一个呢?在内存中有是怎么存放的呢?
  https://www.cnblogs.com/avivahe/p/5747127.html
  
  
  


           

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 



