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
  
#### 堆
  
  Java的堆是一个运行时数据区,类的对象从中分配空间。
  这些对象通过new、newarray、anewarray和multianewarray等指令建立，
  它们不需要程序代码来显式的释放。堆是由垃圾回收来负责的，
  堆的优势是可以动态地分配内存大小，生存期也不必事先告诉编译器，
  因为它是在运行时动态分配内存的，Java的垃圾收集器会自动收走这些不再使用的数据。
  但缺点是，由于要在运行时动态分配内存，存取速度较慢.
  
#### 栈
  
  栈的优势是，存取速度比堆要快，仅次于寄存器，栈数据可以共享。
  但缺点是，存在栈中的数据大小与生存期必须是确定的，缺乏灵活性。栈中主要存放一些基本类型的变量
  （int, short, long, byte, float, double, boolean, char）和对象句柄。
  
   一般的类 在申明的时候在栈中设置一个地址存放的是为null的应用
   在XX xx = new XX();的时候会在堆中分配内存存放xx 然后堆中的引用指向这个地址
   
# static

   内存不止分为堆和栈，还有另外3个区,
   静态变量在静态存储区 所以所有的静态变量和静态内都是在这个静态区的
   
   一个类中，一个static变量只会有一个内存空间，虽然有多个类实例，但这些类实例中的这个static变量会共享同一个内存空间.
   就是说XX类 有很多new出来的类 xx_0 xx_1; 但是XX类中的static修饰的 变量 在xx_0 或者xx_1 中都是一个地址
   所以这才是单例模式的原理
  
   
  参考地址  https://www.cnblogs.com/avivahe/p/5747127.html
  
  参考地址  https://blog.csdn.net/qianyayun19921028/article/details/80365126
  
  
  问题 静态内部内 中也存在静态变量和非静态变量 这些内存有是怎么分配的呢?


           

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 



