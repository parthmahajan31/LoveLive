package com.love.lovelive.utils

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2

import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

object CommonMethods {

//    private var mSocket: Socket? = null
//
//
//    @SuppressLint("ServiceCast")
//    fun showNotification(
//        context: Context,
//        pendingIntent: PendingIntent?,
//        notification: RemoteMessage,
//        NotificationId: Int
//    ) {
//        val notificationBuilder = NotificationCompat.Builder(context, "auslan")
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle(notification.data["title"])
//            .setContentText(notification.data["body"])
//            .setContentIntent(pendingIntent)
//            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//            .setColor(ContextCompat.getColor(context, R.color.purple_200))
//            .setAutoCancel(true)
//
//        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel =
//                NotificationChannel("equipro", "equipro", NotificationManager.IMPORTANCE_HIGH)
//            channel.importance = NotificationManager.IMPORTANCE_HIGH
//            notificationManager.createNotificationChannel(channel)
//        }
//        notificationManager.notify(NotificationId, notificationBuilder.build())
//    }
//
//    fun currentDeviceZone(): String {
//        val sdf = SimpleDateFormat("ZZZZZ")
//        val currentDate = sdf.format(Date())
//        println(" C DATE is  $currentDate")
//        return currentDate
//    }
//
//    @Suppress("DEPRECATION")
//    fun isConnected(): Boolean {
//        val cm = MyApplication.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork = cm.activeNetworkInfo
//        return activeNetwork != null && activeNetwork.isConnected
//    }
//
//     fun connectSocket() {
//         try {
//             val okHttpClient: OkHttpClient = OkHttpClient.Builder()
//                 .addInterceptor(SocketAuthentication()).build()
//             IO.setDefaultOkHttpWebSocketFactory(okHttpClient)
//             IO.setDefaultOkHttpCallFactory(okHttpClient)
//
//             val opts = IO.Options()
//             opts.callFactory = okHttpClient
//             opts.webSocketFactory = okHttpClient
//
//             mSocket = IO.socket("http://taxfiling.alcax.com:9000")
//             Log.e("socket", "id $mSocket")
//         } catch (e: Exception) {
//             e.printStackTrace()
//             Log.e("socket", "failed")
//         }
//         mSocket?.connect()
//    }
//
//    private fun getSocketMessages(activity: Activity) {
//        val joinMessage = Emitter.Listener {
//            try {
//                activity.runOnUiThread {
//                    val data = Gson().toJson((it[0] as JSONObject))
//                    Timber.e("MessageData->${data}")
//                    *
//                     * Here you can get the messages
//
//                }
//            }
//            catch (e:Exception){
//                Timber.e(e.toString())
//            }
//
//        }
//
//
//        mSocket?.on(Socket.EVENT_CONNECT) { _ ->
//            Timber.e("socket---------EVENT_CONNECT")
//
//            mSocket?.apply {
//                on("sendChatToTaxfileClient", joinMessage)
//            }
//        }
//        mSocket?.on(Socket.EVENT_CONNECT_ERROR) { args ->
//            Timber.e("socket--- EVENT_CONNECT_ERROR ${args?.contentToString()}")
//        }
//        mSocket?.connect()
//
//
//    }
//
//    fun sendSocketMessage(message:String){
//        if (mSocket != null){
//            mSocket?.emit("sendChatToServer", message)
//        }
//    }
//
//    fun socketDisconnect(){
//        if (mSocket != null){
//            mSocket?.disconnect()
//        }
//
//    }
//
//
//    *
//     * Stripe
//
//
//    fun stripeResult(context: Context,cardInputWidget:CardInputWidget) {
//            StripePayment()
//                .init(context)
//                .clientKey("ENTER_YOUR_STRIPE_PUBLISH_KEY")
//                .cardInputWidget(cardInputWidget)
//                .saveCard(true)
//                .onSuccess { token, paymentMethod ->
//                    Timber.e("STRIPE TOKEN ${Gson().toJson(token)}")
//                    Timber.e("STRIPE PAYMENT METHOD ${Gson().toJson(paymentMethod)}")
//                }
//                .onFailure {
//                    Timber.e("STRIPE failure $it")
//                }
//                .build()
//        }
//
    @Suppress("detekt.UnsafeCast")
    fun <T> MutableLiveData<T>.toLiveData() = this as LiveData<T>
//
//    *
//     * Loader
//
//
//    private lateinit var progressDialog: Dialog
//    fun showProgress(context: Context) {
//        progressDialog = Dialog(context)
//        progressDialog.setTitle("Please wait...")
//        progressDialog.setContentView(R.layout.spinkit)
//        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        val dialogWindow = progressDialog.window
//        dialogWindow!!.setGravity(Gravity.CENTER)
//        progressDialog.setCancelable(false)
//        progressDialog.show()
//    }
//
//    fun dismissProgress() {
//        if (progressDialog != null && progressDialog.isShowing) {
//            progressDialog.dismiss()
//        }
//    }
//
//    fun requestImageFragment(fragment: Fragment){
//        ImagePicker.with(fragment)
//            .crop()	    			//Crop image(Optional), Check Customization for more option
//            .compress(1024)			//Final image size will be less than 1 MB(Optional)
//            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//            .start()
//    }
//
//
//    fun requestImageActivity(activity: Activity){
//        ImagePicker.with(activity)
//            .crop()	    			//Crop image(Optional), Check Customization for more option
//            .compress(1024)			//Final image size will be less than 1 MB(Optional)
//            .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
//            .start()
//    }
//
//    fun getResultImagePath(context: Fragment,getImageResult:(path:String)->Unit){
//            context.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//                val resultCode = result.resultCode
//                val data = result.data
//
//                if (resultCode == Activity.RESULT_OK) {
//                    //Image Uri will not be null for RESULT_OK
//                    val fileUri = data?.data!!
//
//                    getImageResult.invoke((fileUri.path.toString()))
//                } else if (resultCode == ImagePicker.RESULT_ERROR) {
//                    Toast.makeText(context.requireActivity(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(context.requireActivity(), "Task Cancelled", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
//
//

}