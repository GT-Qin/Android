package com.pvirtech.smarteducation.base.base

import com.pvirtech.smarteducation.base.utils.NetResponseUtils

/**
 * author:Teck
 * date: 2021/8/6 16:59
 * description:
 */
data class BaseResult<T>(var msg: String, var code: String, var data: T) : IBaseResponse<T> {
    override fun code(): String = code

    override fun msg(): String = msg

    override fun data(): T = data

    override fun isSuccess(): Boolean {
        return NetResponseUtils.isSuccess(code)
    }
}