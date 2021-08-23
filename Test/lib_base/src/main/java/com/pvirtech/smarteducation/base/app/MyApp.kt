package com.pvirtech.smarteducation.base.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.pvirtech.smarteducation.base.BuildConfig
import com.pvirtech.smarteducation.base.utils.ToastUtil
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits

/**
 * author:Teck
 * date: 2021/8/9 10:01
 * description:
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initARouter()
        initAutoSize()
        ToastUtil.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * 以下是 AndroidAutoSize 可以自定义的参数, [AutoSizeConfig] 的每个方法的注释都写的很详细
     * 使用前请一定记得跳进源码，查看方法的注释, 下面的注释只是简单描述!!!
     */
    private fun initAutoSize() {
        AutoSizeConfig.getInstance()
            .unitsManager
            .setSupportDP(false)
            .setSupportSP(false).supportSubunits = Subunits.MM
        AutoSizeConfig.getInstance().isCustomFragment = true
    }

    //路由初始化
    fun initARouter() {
        try {
            if (BuildConfig.DEBUG){
                ARouter.openLog()
                ARouter.openDebug()
                ARouter.printStackTrace()
            }
            ARouter.init(this)
        }catch (e: Exception){
            e.printStackTrace();
            //异常后建议清除映射表官方文档说 开发模式会清除
            ARouter.openDebug()
            ARouter.init(this)
        }
    }

}