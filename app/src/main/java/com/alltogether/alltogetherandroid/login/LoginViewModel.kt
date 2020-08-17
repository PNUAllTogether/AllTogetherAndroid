package com.alltogether.alltogetherandroid.login

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.alltogether.alltogetherandroid.base.BaseViewModel
import com.alltogether.alltogetherandroid.dto.userInfo
import com.alltogether.alltogetherandroid.dto.userType
import com.alltogether.alltogetherandroid.repository.NaverOAuthRepository
import com.alltogether.alltogetherandroid.repository.ServerRepository
import com.alltogether.alltogetherandroid.utils.SingleLiveEvent
import com.google.gson.Gson
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import io.reactivex.functions.Consumer

class LoginViewModel(private val loginModule : OAuthLogin,
                     private val naverOAuthRepository: NaverOAuthRepository,
                     private val serverRepository: ServerRepository
    ) : BaseViewModel() {

    private val _isLoginFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val isLoginFinished: LiveData<Any> get() = _isLoginFinished
    private val _isLoginFailed: SingleLiveEvent<Any> = SingleLiveEvent()
    val isLoginFailed: LiveData<Any> get() = _isLoginFailed
    private val _isPostFinished: SingleLiveEvent<Any> = SingleLiveEvent()
    val isPostFinished: LiveData<Any> get() = _isPostFinished

    fun getToken(activity : Activity) {
        loginModule.startOauthLoginActivity(activity, LoginHandler(activity, loginModule))
        //loginModule.logoutAndDeleteToken(activity)
    }

    fun checkExist(id : Int) {
        apiCall(serverRepository.checkExistUser(id),
        onSuccess = Consumer {it ->
            if(it.response == "VALID") {
                //회원가입 가능(DB에 회원정보가 없는 경우) -> 회원가입으로 이동

            }
            else {
                //회원가입 필요 X -> 로그인 진행

            }
        })
    }

    fun getUserInfo(accessToken : String){
        apiCall(naverOAuthRepository.getUserInfo(accessToken),
            onSuccess = Consumer { it ->
                Log.e("test user info name",it.response.name)
                Log.e("test user info email",it.response.email)
                Log.e("test user info image",it.response.profile_image)
                Log.e("test user info id", it.response.id.toString())



//                val response =  userInfo(it.response.email,it.response.id,it.response.name,it.response.profile_image, userType.parent)
//                val params = HashMap<String, String>()
//                params["data"] = Gson().toJson(response)

                /* 서버 전송 테스트
                Log.e("response", Gson().toJson(response))
                apiCall(serverRepository.postUserInfo(response),
                    onSuccess = Consumer {
                        Log.e("서버 전송 완료",it.string())
                        _isPostFinished.call()
                    },
                    onError = Consumer {
                        Log.e("ERROR","ERROR : Post to Server ERROR")
                        _isLoginFailed.call()
                    }, indicator = false)
                 */
            },
            onError = Consumer {
                _isLoginFailed.call()
                Log.e("ERROR","ERROR : Get User Info ERROR")
            }, indicator = false)
    }

    inner class LoginHandler(private val context: Context,
                             private val module : OAuthLogin) : OAuthLoginHandler() {
        override fun run(success: Boolean) {
            if (success) {
                val accessToken = module.getAccessToken(context)
                val refreshToken = module.getRefreshToken(context)
                val expiresAt = module.getExpiresAt(context)
                Log.e("Access Token", module.getAccessToken(context))
                Log.e("Refresh Token", module.getRefreshToken(context))
                Log.e("Expires At", expiresAt.toString())
                getUserInfo(accessToken)
                //_isLoginFinished.call()
            } else {
                val errorCode: String =
                    module.getLastErrorCode(context).getCode()
                val errorDesc: String = module.getLastErrorDesc(context)
                Toast.makeText(
                    context, "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}