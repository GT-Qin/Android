package com.pvirtech.smarteducation.base.bean

import java.io.Serializable

/**
 * author:Teck
 * date: 2021/7/21 14:54
 * description:
 */
data class Teacher(
    var postDesc: String,
    var roleID: String,
    var roleName: String,
    var deptID: String,
    var parentDeptID: String,
    var deptType: String,
    var deptName: String,
    var accountStatus: String,
    var accountLevel: String,
    var userCode: String,
    var userName: String,
    var idCardType: String,
    var idCardTypeName: String,
    var userIDCard: String,
    var sex: String,
    var sexName: String,
    var email: String,
    var userStatus: String,
    var systemID: Any,
    var callbackUrl: String,
    var userStatusName: String,
    var companyID: String,
    var companyChineseName: String,
    var loginToken: String,
    var headImg: String,
    var ip: String,
    var mainPostID: String,
    var mainPostName: String,
    var postRankID: String,
    var rankCode: String,
    var postLevelID: String,
    var levelCode: String,
    var postID: String,
    var postName: String,
    var passWord: String,
    var menuList: List<Menu>
) : Serializable

