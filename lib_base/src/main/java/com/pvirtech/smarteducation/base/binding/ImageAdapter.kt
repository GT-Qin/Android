package com.pvirtech.smarteducation.base.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

/**
 * author:Teck
 * date: 2021/8/6 17:06
 * description:图片显示
 */
object ImageAdapter {

    @BindingAdapter(value = ["url", "placeholder"], requireAll = false)
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String, placeholder: Int){
        imageView.load(url) {
            placeholder(placeholder)
        }
    }

}