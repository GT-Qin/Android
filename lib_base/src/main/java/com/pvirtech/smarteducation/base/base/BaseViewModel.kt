package com.pvirtech.smarteducation.base.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.Utils
import com.pvirtech.smarteducation.base.app.CommonUtil
import com.pvirtech.smarteducation.base.event.Message
import com.pvirtech.smarteducation.base.event.SingleLiveEvent
import com.pvirtech.smarteducation.base.net.ResponseThrowable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import shark.HeapAnalysisSuccess

/**
 * author:Teck
 * date: 2021/8/9 10:24
 * description:
 */
open class BaseViewModel
    (application: Application = Utils.getApp()) : AndroidViewModel(application), LifecycleObserver {

    val defUI: UIChange by lazy { UIChange() }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动调用ViewModel的[onCleared] 方法取消所有协程
     * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Unit>
     * @return Job
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

    /**
     * 用流的方式进行网络请求
     * @param block SuspendFunction0<T>
     * @return Flow<T>
     */
    fun <T> launchFlow(block: suspend() -> T): Flow<T> = flow { emit(block()) }

    /**
     * 不过滤请求结果
     * @param block  请求体 [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Unit>
     * @param error  失败回调 [@kotlin.ExtensionFunctionType] SuspendFunction2<CoroutineScope, ResponseThrowable, Unit>
     * @param complete  完成回调，成功失败都会调用 [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Unit>
     * @param isShowDialog Boolean  是否显示加载框
     */
    fun launchGo(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit = {
            defUI.toastEvent.postValue("${it.code}:${it.errMsg}")
        },
        complete: suspend CoroutineScope.() -> Unit = {},
        isShowDialog: Boolean = true
    ){
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleException(
                withContext(Dispatchers.IO) { block },
                { error(it) },
                {
                    defUI.dismissDialog.call()
                    complete()
                }
            )
        }
    }

    /**
     * 过滤请求结果
     * @param block 请求体 [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, IBaseResponse<T>>
     * @param success 成功回调 Function1<T, Unit>
     * @param error 失败回调 Function1<ResponseThrowable, Unit>
     * @param complete 完成回调 Function0<Unit>
     * @param isShowDialog 是否显示加载框 Boolean
     */
    fun <T> launchWithResult(
        block: suspend CoroutineScope.() -> IBaseResponse<T>,
        success: (T) -> Unit,
        error: (ResponseThrowable) -> Unit,
        complete: () -> Unit,
        isShowDialog: Boolean = true
    ){
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleException(
                {
                    withContext(Dispatchers.IO) {
                        block().let {
                            if (it.isSuccess())
                                it.data()
                            else
                                throw ResponseThrowable(it.code(), it.msg())
                        }
                    }.also { success(it) }
                },
                {
                    error(it)
                    defUI.toastEvent.postValue("${it.code}:${it.errMsg}")
                },
                {
                    defUI.dismissDialog.call()
                    complete()
                }
            )
        }
    }

    /**
     * 异常处理
     * @param block [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Unit>
     * @param error [@kotlin.ExtensionFunctionType] SuspendFunction2<CoroutineScope, ResponseThrowable, Unit>
     * @param complete [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Unit>
     */
    private suspend fun handleException(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ){
        coroutineScope {
            try {
                block()
            }catch (e:Throwable){
                error(CommonUtil.getConfig().globalExceptionHandle(e))
            }finally {
                complete()
            }
        }
    }

    /**
     * UI事件
     */
    inner class UIChange {
        val showDialog by lazy { SingleLiveEvent<String>() }
        val dismissDialog by lazy { SingleLiveEvent<Void>() }
        val toastEvent by lazy { SingleLiveEvent<String>() }
        val msgEvent by lazy { SingleLiveEvent<Message>() }
    }
}