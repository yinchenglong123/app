package com.example.rk2;


import android.app.Application;
import android.os.Environment;


import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


import java.io.File;


/**
 * author:Created by WangZhiQiang on 2017-10-10.
 * 系统启动 会先运行这个MApp,所以我们在这里进行初始化 框架 组件等等;
 */


public class App extends Application {
    File cacheFile = new File(Environment.getExternalStorageDirectory() + "/" + "image");


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件,链式开发思想,整个框架的参数初始化配置
        //imageLoader的全局配置
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions 内存缓存文件的最大长宽
                .diskCacheExtraOptions(480, 800, null)  // 本地缓存的详细信息(缓存的最大长宽)，最好不要设置这个
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default

                .threadPoolSize(3)//线程池数量

                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
                .diskCacheFileCount(100)  // 可以缓存的文件数量
                .diskCache(new UnlimitedDiskCache(cacheFile))//自定义缓存目录
                // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs() // 打印debug log
                .build();
        ImageLoader.getInstance().init(configuration);


    }
}