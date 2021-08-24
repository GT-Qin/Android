package com.pvirtech.smarteducation.base.utils

import android.app.Application
import android.content.Context
import android.widget.Toast

/**
 * author:Teck
 * date: 2021/7/9 15:07
 * description:Toast工具类
 */
object ToastUtil {

    private var time: Long = 0L
    private var oldMsg: String? = null
    private var application:Application? = null

    fun init(application: Application){
        this.application = application
    }

    fun show(msg:String){
        if (msg != oldMsg){
            create(msg)
            time = System.currentTimeMillis()
        }else{
            if (System.currentTimeMillis() - time > 2000){
                create(msg)
                time = System.currentTimeMillis()
            }
        }
        oldMsg = msg
    }

    private fun create(message: String){
        val toast = Toast.makeText(application?.applicationContext, "", Toast.LENGTH_SHORT)
        toast.setText(message)
        toast.show()
    }
}