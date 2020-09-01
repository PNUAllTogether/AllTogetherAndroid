package com.alltogether.alltogetherandroid.repository

import com.alltogether.alltogetherandroid.api.ServerService
import com.alltogether.alltogetherandroid.dto.*
import io.reactivex.Single
import okhttp3.ResponseBody

class ServerRepositoryImpl constructor(private val serverService: ServerService) : ServerRepository {
    override fun checkExistUser(id: Int): Single<checkExistUser> {
        return serverService.checkExistUser(postInt(id))
    }

//    override fun postUserInfo(response: Map<String, String>): Single<ResponseBody> {
//        return serverService.postUserInfo(response)
//    }
override fun postParentInfo(response: ParentInfo): Single<ResponseBody> {
    return serverService.postParentInfo(response)
}

    override fun postSupporterInfo(response: SupporterInfo): Single<ResponseBody> {
        return serverService.postSupporterInfo(response)
    }

    override fun filterSupporter(region: String, major: String): Single<supporterSearchResult> {
        return serverService.filterSupporter(region, major)
    }

    override fun getChildInfo(id: Int): Single<getChildInfoResult> {
        return serverService.getChildInfo(postInt(id))
    }

    override fun addSupporter(childId: Int, supporterId: Int): Single<addSupporterResult> {
        return serverService.addSupporter(postAddSupporter(childId, supporterId))
    }

    override fun currentSupporter(childId: Int): Single<supporterSearchResult> {
        return serverService.currentSupporter(postChild(childId))
    }

    override fun getAllCheckList(childId: Int): Single<getCheckListResult> {
        return serverService.getAllCheckList(postChild(childId))
    }

    override fun addCheckList(childId: Int, date: String, content: String): Single<getCheckListResult> {
        return serverService.addCheckList(postCheckList(childId, date, content))
    }

    override fun addDone(childId: Int, itemId: Int, done: Boolean): Single<getCheckListResult> {
        return serverService.addDone(postDone(childId, itemId , done))
    }

    override fun postDelete(childId: Int, itemId: Int): Single<getCheckListResult> {
        return serverService.delete(com.alltogether.alltogetherandroid.dto.postDelete(childId, itemId))
    }
}