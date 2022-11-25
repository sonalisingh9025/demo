package com.finalatixassignment.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.finalatixassignment.R
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

object AppUtility {

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            } else {
                toast(context,context.getString(R.string.error_network))
                return false
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            } else {
                toast(context,context.getString(R.string.error_network))
                return false
            }
        }
        return false
    }

     fun toast(context: Context, s:String){
        Toast.makeText(context,s,Toast.LENGTH_SHORT).show()
    }


    fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        if (activity.currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
    }

    fun errorHandle(response: Response<Any>): String {

        val error = response.errorBody()?.string()
        var message = ""
        error.let {
            try {
                message = (JSONObject(it.toString()).get("message").toString())
            } catch (e: JSONException) {
                message = ("Internal Server error")
                Log.e("Exception", e.toString())
            }
        }
        return message

    }
}

