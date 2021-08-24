package com.pvirtech.smarteducation.module.common.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.pvirtech.smarteducation.base.base.BaseActivity
import com.pvirtech.smarteducation.base.base.NoViewModel
import com.pvirtech.smarteducation.base.event.ARouterCenter
import com.pvirtech.smarteducation.base.utils.ACache
import com.pvirtech.smarteducation.base.utils.ConstantConfig
import com.pvirtech.smarteducation.module.common.databinding.ActivitySplashBinding
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : BaseActivity<NoViewModel, ActivitySplashBinding>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isTaskRoot) { // 判断当前activity是不是所在任务栈的根
            if (intent != null) {
                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER)
                    && Intent.ACTION_MAIN == intent.action) {
                    finish()
                    return
                }
            }
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initData() {
//        Timer().schedule(2000) {
//            if (ACache.get(this@SplashActivity).getAsObject(ConstantConfig.student_info) != null) ARouterCenter.toMain()
//            else ARouterCenter.toLogin()
//        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

}