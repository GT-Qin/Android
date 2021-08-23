package com.pvirtech.smarteducation.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.pvirtech.smarteducation.base.R
import com.pvirtech.smarteducation.base.app.CommonUtil
import com.pvirtech.smarteducation.base.event.Message
import java.lang.reflect.ParameterizedType

/**
 * author:Teck
 * date: 2021/8/9 11:14
 * description:基类Fragment
 */
abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {
    protected lateinit var viewModel: VM
    protected lateinit var mBinding: VB
    private var mDialog: MaterialDialog? = null
    //是否第一次加载
    private var isFirst: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return initBinding(inflater, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onVisible()
        createViewModel()
        lifecycle.addObserver(viewModel)
        registorDefUIChange()
        initView(savedInstanceState)
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val cls = type.actualTypeArguments[1] as Class<*>
            return when {
                ViewDataBinding::class.java.isAssignableFrom(cls) && cls != ViewDataBinding::class.java -> {
                    if (layoutId() == 0) throw IllegalArgumentException("Using DataBinding requires overriding method layoutId")
                    mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
                    (mBinding as ViewDataBinding).lifecycleOwner = this
                    mBinding.root
                }
                ViewBinding::class.java.isAssignableFrom(cls) && cls != ViewBinding::class.java -> {
                    cls.getDeclaredMethod("inflate", LayoutInflater::class.java).let {
                        @Suppress("UNCHECKED_CAST")
                        mBinding = it.invoke(null, inflater) as VB
                        mBinding.root
                    }
                }
                else -> {
                    if (layoutId() == 0) throw IllegalArgumentException("If you don't use ViewBinding, you need to override method layoutId")
                    inflater.inflate(layoutId(), container, false)
                }
            }
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
        (mDialog ?: MaterialDialog(requireContext())
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
     * 创建 ViewModel
     *
     * 共享 ViewModel的时候，重写  Fragmnt 的 getViewModelStore() 方法，
     * 返回 activity 的  ViewModelStore 或者 父 Fragmnt 的 ViewModelStore
     */
    @Suppress("UNCHECKED_CAST")
    private fun createViewModel() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val tp = type.actualTypeArguments[0]
            val tClass = tp as? Class<VM> ?: BaseViewModel::class.java
            viewModel = ViewModelProvider(viewModelStore, defaultViewModelProviderFactory)
                .get(tClass) as VM
        }
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return CommonUtil.getConfig().viewModelFactory() ?: super.getDefaultViewModelProviderFactory()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            lazyLoadData()
            isFirst = false
        }
    }

    /**
     * 使用 DataBinding时,要重写此方法返回相应的布局 id
     * 使用ViewBinding时，不用重写此方法
     */
    open fun layoutId(): Int = 0

    /**
     * 懒加载
     */
    open fun lazyLoadData() {}

    open fun handleEvent(msg: Message) {}

    open fun initView(savedInstanceState: Bundle?){}
}