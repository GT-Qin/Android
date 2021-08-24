package com.pvirtech.smarteducation.base.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * author:Teck
 * date: 2021/8/6 16:54
 * description:
 */
class ViewModelFactory : ViewModelProvider.Factory {

    companion object {
        private var sInstance: ViewModelFactory? = null

        fun getInstance() = sInstance ?: ViewModelFactory().also { sInstance = it }
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.newInstance()
    }
}