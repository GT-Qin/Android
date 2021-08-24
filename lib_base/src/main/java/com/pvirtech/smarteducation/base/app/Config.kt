package com.pvirtech.smarteducation.base.app

import androidx.lifecycle.ViewModelProvider
import com.pvirtech.smarteducation.base.net.ExceptionHandle
import com.pvirtech.smarteducation.base.utils.ViewModelFactory

/**
 * author:Teck
 * date: 2021/8/6 16:47
 * description:
 */
interface Config {

    fun viewModelFactory(): ViewModelProvider.Factory? = ViewModelFactory.getInstance()

    fun globalExceptionHandle(e: Throwable) = ExceptionHandle.handleException(e)

}