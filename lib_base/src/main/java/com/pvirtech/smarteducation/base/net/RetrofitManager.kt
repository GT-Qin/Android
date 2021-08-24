package com.pvirtech.smarteducation.base.net

import android.util.Log
import com.pvirtech.smarteducation.base.app.Constants
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownServiceException
import java.util.concurrent.TimeUnit

/**
 * author:Teck
 * date: 2021/8/9 13:56
 * description:
 */
object RetrofitManager {
    private lateinit var mRetrofit: Retrofit
    private lateinit var mFileRetrofit: Retrofit

    private val mOkHttpClient = OkHttpClient.Builder()
        .callTimeout(20L, TimeUnit.SECONDS)
        .connectTimeout(20L, TimeUnit.SECONDS)
        .readTimeout(20L, TimeUnit.SECONDS)
        .writeTimeout(20L, TimeUnit.SECONDS)
//        .addNetworkInterceptor(LoggingInterceptor())
        .addInterceptor(HttpLoggingInterceptor { message -> LogUtil.e("log:${getFormat(message)}") }
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectionPool(ConnectionPool(8, 15, TimeUnit.SECONDS))
        .build()

    fun getRetrofit(): RetrofitManager{
        mRetrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this
    }

    fun getFileRetrofit() : RetrofitManager {
        mFileRetrofit = Retrofit.Builder()
            .baseUrl(Constants.FILE_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this
    }

    fun <T> getService(serviceClass: Class<T>): T{
        return mRetrofit.create(serviceClass)
    }

    fun <T> getFileService(serviceClass: Class<T>) : T {
        return mFileRetrofit.create(serviceClass)
    }

    fun getFormat(msg:String): String{
        return if (msg.isNotEmpty() && (msg.contains("{") && msg.contains("}"))) JsonFormat.format(msg) else msg
    }


}