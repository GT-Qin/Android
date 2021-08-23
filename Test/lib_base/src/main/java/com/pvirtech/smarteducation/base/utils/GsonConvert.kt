package com.pvirtech.smarteducation.base.utils

/**
 * author:Teck
 * date: 2021/7/21 15:43
 * description:
 */
class GsonConvert {

    companion object {

        fun getRequestString(vararg a: String): String {
            val builder = StringBuilder("{")
            var i = 0
            while (i < a.size) {
                builder.append("\"")
                builder.append(a[i])
                builder.append("\":\"")
                builder.append(a[i + 1])
                builder.append("\"")
                if (i + 2 < a.size) builder.append(",")
                i += 2
            }
            builder.append("}")
            return builder.toString()
        }
    }
}