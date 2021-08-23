package com.pvirtech.smarteducation.base.net

import com.pvirtech.smarteducation.base.base.IBaseResponse

open class ResponseThrowable : Exception {
    var code: String
    var errMsg: String

    constructor(error: ERROR, e: Throwable? = null) : super(e) {
        code = error.getKey()
        errMsg = error.getValue()
    }

    constructor(code: String, msg: String, e: Throwable? = null) : super(e) {
        this.code = code
        this.errMsg = msg
    }

    constructor(base: IBaseResponse<*>, e: Throwable? = null) : super(e) {
        this.code = base.code()
        this.errMsg = base.msg()
    }
}

