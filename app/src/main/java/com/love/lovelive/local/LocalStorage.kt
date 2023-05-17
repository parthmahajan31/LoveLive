
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.love.lovelive.delegate.MyApplication
import com.love.lovelive.retrofit.EndPoints

@SuppressLint("StaticFieldLeak")
object LocalStorage {

    val context = MyApplication.application

    private var preferencesRemember: SharedPreferences =
        context.getSharedPreferences("love_live_sharePreference_remember", Context.MODE_PRIVATE)

        //    method call for saved preferences, either String or boolean
        fun storeDataLocal(context: Context, key: String?, value: String?) {
            Log.d("storeDataLocal", "storeDataLocal() called with:, key = $key, value = $value")
            val sharedPreferences =
                context.getSharedPreferences(EndPoints.LOVE_LIVE, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

    fun saveToken(token: String,context: Context) {
        val sharedPreferences =
            context.getSharedPreferences(EndPoints.LOVE_LIVE, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(context: Context): String? {
        val sharedPreferences =
            context.getSharedPreferences(EndPoints.LOVE_LIVE, Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", "")
    }




        //    method call for saved preferences, either String or boolean
        fun storeDeviceToken(context: Context, key: String?, value: String?) {

            val sharedPreferences =
                context.getSharedPreferences(EndPoints.DEVICE_TOKEN_PREF, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

        //   method for get preferences
        fun getLocalData(context: Context, key: String): String? {
            val prefs = context.getSharedPreferences(EndPoints.LOVE_LIVE, Context.MODE_PRIVATE)
            return prefs.getString(key, null)
        }

        //   method for get preferences
        fun getDeviceToken(context: Context, key: String): String? {
            val prefs = context.getSharedPreferences(EndPoints.DEVICE_TOKEN_PREF, Context.MODE_PRIVATE)
            if(prefs!=null){
                return prefs.getString(key, null)
            }
            return ""
        }

        //    Clear shared preferences
        fun clearAllData(name: String?, context: Context) {
            val sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
        }

    fun saveString(context: Context,key: String, value: String) {
        val preferences = context.getSharedPreferences(EndPoints.LOVE_LIVE, Context.MODE_PRIVATE)
        preferences.edit().putString(key, value).apply()
    }

    fun getStringValue(context: Context,key: String): String {
        val preferences = context.getSharedPreferences(EndPoints.LOVE_LIVE, Context.MODE_PRIVATE)
        return preferences.getString(key, "").toString()
    }

    fun saveStringRemember(key: String, value: String) {
        preferencesRemember.edit().putString(key, value).apply()
    }

    fun getStringValueRemember(key: String): String {
        return preferencesRemember.getString(key, "").toString()
    }
}