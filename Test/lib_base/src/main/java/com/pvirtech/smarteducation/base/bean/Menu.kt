package com.pvirtech.smarteducation.base.bean

import java.io.Serializable

/**
 * author:Teck
 * date: 2021/7/21 15:18
 * description:
 */
data class Menu(
    var flowID: String,
    var roleID: String,
    var roleName: String,
    var note: String,
    var systemID: String,
    var menuID: String,
    var buttons: String,
    var menuName: String,
    var menuURL: String,
    var parentMenuID: String,
    var icon: String,
    var menuSort: String,
    var isDisplay: String,
    var menuType: String,
    var checked: String,
    var openMode: String,
) : Serializable
