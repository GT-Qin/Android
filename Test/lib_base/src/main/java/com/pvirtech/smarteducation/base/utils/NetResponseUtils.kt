package com.pvirtech.smarteducation.base.utils

/**
 * author:Teck
 * date: 2021/7/21 16:40
 * description:网络请求对应业务的处理
 */
class NetResponseUtils {

    companion object {

        fun isSuccess(code: String): Boolean{
            val newcode = getResultCode(code)
            return when (newcode) {
                "00", "10", "20", "30", "40", "50", "60","41" -> true
                "01", "12", "22", "32", "42", "52", "62" -> false
                else -> false
            }
        }

        /**
         * 获取网络请求的返回码最后两位
         * @param code
         * @return
         */
        private fun getResultCode(code: String?): String? {
            return code?.substring(code.length - 2)
        }
    }

}