package com.pvirtech.smarteducation.base.app

/**
 * author:Teck
 * date: 2021/7/9 15:56
 * description:常量
 */
class Constants {

    companion object{//伴生对象   相当于final static

        const val BASE_URL = "http://192.168.10.250:9018/" //公司环境
//        const val BASE_URL = "http://192.168.50.227:9008/" //公司环境

        const val FILE_BASE_URL = "http://192.168.10.244/Upload/" //公司环境 文件服务

        //用户信息
        const val SP_KEY_USER_INFO = "SP_KEY_USER_INFO_NAME"

        //用户登录名
        const val SP_KEY_ACCOUNT_INFO = "SP_KEY_ACCOUNT_INFO"
        //用户密码
        const val SP_KEY_PASSWORD_INFO = "SP_KEY_PASSWORD_INFO"
    }
}