package com.pvirtech.smarteducation.base.base

import com.pvirtech.smarteducation.base.net.ResponseThrowable

/**
 * author:Teck
 * date: 2021/8/6 17:16
 * description:网络请求
 */
abstract class BaseModel {

    /**
     * 网络请求
     * @param remote SuspendFunction0<IBaseResponse<T>>
     * @return T
     */
    suspend fun <T> netCall(
        remote:suspend() -> IBaseResponse<T>
    ):T{
        return remote().let { net ->
                if (net.isSuccess()) net.data()
                throw ResponseThrowable(net)
            }
    }

}