package com.alltogether.alltogetherandroid.repository

import com.alltogether.alltogetherandroid.dto.*
import io.reactivex.Single
import okhttp3.ResponseBody

interface ServerRepository {
    fun checkExistUser(id : Int) : Single<checkExistUser>
//    fun postUserInfo(response : Map<String,String>) : Single<ResponseBody>
//    fun postUserInfo(response : userInfo) : Single<ResponseBody>
    fun postParentInfo(response : parentInfo) : Single<ResponseBody>
    fun postSupporterInfo(response : supporterInfo) : Single<ResponseBody>
    fun filterSupporter(region : String, major : String) : Single<supporterSearchResult>
    fun getChildInfo(id : Int) : Single<getChildInfoResult>
    fun addSupporter(childId: Int, supporterId: Int) : Single<addSupporterResult>
    fun currentSupporter(childId: Int) : Single<supporterSearchResult>
    fun getAllCheckList(childId: Int) : Single<getCheckListResult>
    fun addCheckList(childId: Int, date: String, content: String) : Single<getCheckListResult>
    fun addDone(childId: Int, itemId: Int, done: Boolean) : Single<getCheckListResult>
}