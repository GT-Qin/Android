package com.pvirtech.smarteducation.base.event

import com.alibaba.android.arouter.launcher.ARouter

/**
 * author:Teck
 * date: 2021/8/9 09:57
 * description:路由中心  skip
 */
object ARouterCenter {

    /**
     * 登录
     */
    fun toLogin() {
        ARouter.getInstance().build(ARouterUrl.commonLogin).navigation()
    }

    /**
     * 首页
     */
    fun toMain() {
        ARouter.getInstance().build(ARouterUrl.commonMain).navigation()
    }

}