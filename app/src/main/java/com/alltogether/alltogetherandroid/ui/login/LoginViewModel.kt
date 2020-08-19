package com.alltogether.alltogetherandroid.ui.login

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import com.alltogether.alltogetherandroid.base.BaseViewModel
import com.alltogether.alltogetherandroid.dto.parentInfo
import com.alltogether.alltogetherandroid.dto.userType
import com.alltogether.alltogetherandroid.repository.NaverOAuthRepository
import com.alltogether.alltogetherandroid.repository.ServerRepository
import com.alltogether.alltogetherandroid.utils.SingleLiveEvent
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
    private val _onSignupProceed: SingleLiveEvent<Any> = SingleLiveEvent()
    val onSignupProceed: LiveData<Any> get() = _onSignupProceed
    private val _onLoginProceed: SingleLiveEvent<Any> = SingleLiveEvent()
    val onLoginProceed: LiveData<Any> get() = _onLoginProceed
    private val _onChildNameError: SingleLiveEvent<String> = SingleLiveEvent()
    val onChildNameError: LiveData<String> get() = _onChildNameError
    private val _onChildAgeError: SingleLiveEvent<String> = SingleLiveEvent()
    val onChildAgeError: LiveData<String> get() = _onChildAgeError
    private val _onChildGoalError: SingleLiveEvent<String> = SingleLiveEvent()
    val onChildGoalError: LiveData<String> get() = _onChildGoalError
    private val _onChildCharacterError: SingleLiveEvent<String> = SingleLiveEvent()
    val onChildCharacterError: LiveData<String> get() = _onChildCharacterError

    lateinit var email: String
    var id: Int = 0
    lateinit var name: String
    lateinit var profileImage: String



    fun getToken(activity : Activity) {
        loginModule.startOauthLoginActivity(activity, LoginHandler(activity, loginModule))
    }

    fun checkValidParentInfo(name: String?, age: String?, type: String?, goal: String?, character: String?) {
        if(name == null || name.isEmpty()) {
            _onChildNameError.postValue("아동의 이름을 입력해주세요!")
        }
        else if(age == null || age.isEmpty()) {
            _onChildAgeError.postValue("아동의 나이를 입력해주세요!")
        }
        else if(!age.isDigitsOnly()) {
            _onChildAgeError.postValue("아동의 나이를 올바르게 입력해주세요!")
        }
        else if(goal == null || goal.isEmpty()) {
            _onChildGoalError.postValue("아동의 목표를 입력해주세요!")
        }
        else if(character == null || character.isEmpty()) {
            _onChildCharacterError.postValue("아동의 특성을 입력해주세요!")
        }
        else {
            postParentInfo(parentInfo(email, id, name, profileImage, userType.parent, type!!, age.toInt(), character, goal, name))
        }
    }

    fun postParentInfo(parentInfo: parentInfo) {
        apiCall(serverRepository.postParentInfo(parentInfo),
        onSuccess = Consumer { it ->
            _isPostFinished.call()
        },
        onError = Consumer {
            Log.e("LOG", "Post Parent Info Failed")
        },
        indicator = true)
    }

    fun checkExist(id : Int) {
        apiCall(serverRepository.checkExistUser(id),
        onSuccess = Consumer {it ->
            if(it.response == "VALID") {
                //회원가입 가능(DB에 회원정보가 없는 경우) -> 회원가입으로 이동
                _onSignupProceed.call()
            }
            else {
                //회원가입 필요 X -> 로그인 진행
                //로그인 성공하면 회원 id를 로컬에 저장하고 어플 실행 시 마다 로컬에 id가 있는지 체크

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
                email = it.response.email
                id = it.response.id
                profileImage = it.response.profile_image
                name = it.response.name
                checkExist(id)

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

    override fun onCleared() {
        Log.e("LoginViewModel","OnCleared")
        super.onCleared()
    }
}