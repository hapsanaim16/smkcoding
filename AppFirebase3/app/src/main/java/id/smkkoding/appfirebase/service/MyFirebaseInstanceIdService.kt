package id.smkkoding.appfirebase.service

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceIdService : FirebaseInstanceIdService(){
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        sendTokenToServer()
    }
    private fun sendTokenToServer() {
        Log.d("Token", FirebaseInstanceId.getInstance().token)
    }
}
