package com.pvirtech.smarteducation.base.base

/**
 * author:Teck
 * date: 2021/8/6 16:58
 * description:
 */
interface IBaseResponse<T> {
    fun code(): String
    fun msg(): String
    fun data(): T
    fun isSuccess(): Boolean
}