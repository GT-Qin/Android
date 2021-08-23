package com.pvirtech.smarteducation.base.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.pvirtech.smarteducation.base.R
import com.pvirtech.smarteducation.base.app.CommonUtil
import com.pvirtech.smarteducation.base.app.StatusBar
import com.pvirtech.smarteducation.base.event.Message
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * author:Teck
 * date: 2021/8/9 11:13
 * description:基类Activity
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {
    protected lateinit var viewModel: VM
    protected lateinit var mBinding: VB
    private var mDialog: MaterialDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBar().fitSystemBar(this)
        initViewDataBinding()
        lifecycle.addObserver(viewModel)
        registorDefUIChange()
        initView(savedInstanceState)
        initData()
    }

    private fun initViewDataBinding() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val cls = type.actualTypeArguments[1] as Class<*>
            when {
                ViewDataBinding::class.java.isAssignableFrom(cls) && cls != ViewDataBinding::class.java -> {
                    if (layoutId() == 0) throw IllegalArgumentException("Using DataBinding requires overriding method layoutId")
                    mBinding = DataBindingUtil.setContentView(this, layoutId())
                    (mBinding as ViewDataBinding).lifecycleOwner = this
                }
                ViewBinding::class.java.isAssignableFrom(cls) && cls != ViewBinding::class.java -> {
                    cls.getDeclaredMethod("inflate", LayoutInflater::class.java).let {
                        @Suppress("UNCHECKED_CAST")
                        mBinding = it.invoke(null, layoutInflater) as VB
                        setContentView(mBinding.root)
                    }
                }
                else -> {
                    if (layoutId() == 0) throw IllegalArgumentException("If you don't use ViewBinding, you need to override method layoutId")
                    setContentView(layoutId())
                }
            }
            createViewModel(type.actualTypeArguments[0])
        } else throw IllegalArgumentException("Generic error")
    }

    /**
     * 注册 UI 事件
     */
    private fun registorDefUIChange() {
        viewModel.defUI.showDialog.observe(this, {
            showLoading()
        })
        viewModel.defUI.dismissDialog.observe(this, {
            dismissLoading()
        })
        viewModel.defUI.toastEvent.observe(this, {
            ToastUtils.showShort(it)
        })
        viewModel.defUI.msgEvent.observe(this, {
            handleEvent(it)
        })
    }

    /**
     * 打开等待框
     */
    private fun showLoading() {
        (mDialog ?: MaterialDialog(this)
            .cancelable(false)
            .cornerRadius(8f)
            .customView(R.layout.custom_progress_dialog_view, noVerticalPadding = true)
            .lifecycleOwner(this)
            .maxWidth(R.dimen.dialog_width).also {
                mDialog = it
            })
            .show()
    }

    /**
     * 关闭等待框
     */
    private fun dismissLoading() {
        mDialog?.run { if (isShowing) dismiss() }
    }

    /**
     * 使用 DataBinding时,要重写此方法返回相应的布局 id
     * 使用ViewBinding时，不用重写此方法
     * @return Int
     */
    open fun layoutId(): Int = 0
    open fun handleEvent(msg: Message) {}
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData()

    /**
     * 创建 ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel(type: Type) {
        val tClass = type as? Class<VM> ?: BaseViewModel::class.java
        viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
            .get(tClass) as VM
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return CommonUtil.getConfig().viewModelFactory()
            ?: super.getDefaultViewModelProviderFactory()
    }

}