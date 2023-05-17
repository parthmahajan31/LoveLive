package com.love.lovelive.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.provider.Settings
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.love.lovelive.R
import com.love.lovelive.delegate.MyApplication
import com.tapadoo.alerter.Alerter
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.time.temporal.WeekFields
import java.util.*
import java.util.regex.Pattern


@Suppress("DEPRECATION")
fun isConnected(): Boolean {
    val cm =
        MyApplication.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun getDummyList(
    size: Int
): ArrayList<String> {
    val arrayList: ArrayList<String> = ArrayList()
    for (i in 0..size) {
        arrayList.add("$i")
    }
    return arrayList
}

fun deviceId(): String {
    return Settings.Secure.getString(
        MyApplication.application.contentResolver,
        Settings.Secure.ANDROID_ID
    )
}


fun showScreen(activityResult: ActivityResultLauncher<Intent>, intent: Intent) {
    activityResult.launch(intent)
}

fun convertToBitmap(drawable: Drawable, widthPixels: Int, heightPixels: Int): Bitmap? {
    val mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(mutableBitmap)
    drawable.setBounds(0, 0, widthPixels, heightPixels)
    drawable.draw(canvas)
    return mutableBitmap
}

fun Intent?.getFilePath(context: Context): String {
    return this?.data?.let { data ->
        RealPathUtil.getRealPath(
            MyApplication.application.applicationContext,
            data
        ) ?: ""
    } ?: ""
}

fun getPointOfView(view: View): Point? {
    val location = IntArray(2)
    view.getLocationInWindow(location)
    return Point(location[0], location[1])
}

fun Marker.loadIcon(context: Context, url: String?) {
    Glide.with(context)
        .asBitmap()
        .load(url)
        .error(R.drawable.rectangle) // to show a default icon in case of any errors
        .listener(object : RequestListener<Bitmap> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return resource?.let {
                    BitmapDescriptorFactory.fromBitmap(it)
                }?.let {
                    setIcon(it); true
                } ?: false
            }
        }).submit()
}

fun String.alert(activity: Activity) {
    Alerter.create(activity)
        .setText(this)
        .setDuration(4000)
        .setIcon(R.drawable.ic_sad_face)
        .setIconColorFilter(0)
        .setBackgroundResource(R.drawable.splash_bg)
        .show()
}
fun String.isEmailValid(): Boolean {
    return Pattern.compile(
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    ).matcher(this).matches()
}

private var progressDialog: Dialog? = null
fun showProgress(context: Context) {
//    if (progressDialog == null) {

//    Timber.e("Working")
    progressDialog = Dialog(context)
    progressDialog?.setTitle("Please wait...")
    progressDialog?.setContentView(R.layout.spinkit)
    progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    val dialogWindow = progressDialog?.window
    dialogWindow!!.setGravity(Gravity.CENTER)
    progressDialog?.setCancelable(false)
    progressDialog?.show()
//    }
}

fun dismissProgress() {
    if (progressDialog != null && progressDialog?.isShowing == true) {
//        Timber.e("working")
        progressDialog?.dismiss()
    }
}

fun View.show(){
    this.visibility= View.VISIBLE
}

fun View.hide(){
    this.visibility= View.GONE
}

fun daysOfWeekFromLocale(): Array<DayOfWeek> {
    val firstDayOfWeek = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        WeekFields.of(Locale.getDefault()).firstDayOfWeek
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    var daysOfWeek = DayOfWeek.values()
    // Order `daysOfWeek` array so that firstDayOfWeek is at index 0.
    // Only necessary if firstDayOfWeek != DayOfWeek.MONDAY which has ordinal 0.
    if (firstDayOfWeek != DayOfWeek.MONDAY) {
        val rhs = daysOfWeek.sliceArray(firstDayOfWeek.ordinal..daysOfWeek.indices.last)
        val lhs = daysOfWeek.sliceArray(0 until firstDayOfWeek.ordinal)
        daysOfWeek = rhs + lhs
    }
    return daysOfWeek
}

fun getFileName(activity: Activity, uri: Uri?): String {
    var filename: String?
    val cursor: Cursor? = activity.contentResolver.query(uri!!, null, null, null, null)
    if (cursor == null) filename = uri.path else {
        cursor.moveToFirst()
        val idx = cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)
        filename = cursor.getString(idx)
        cursor.close()
    }
    val name = filename!!.substring(0, filename.lastIndexOf("."))
    val extension = filename.substring(filename.lastIndexOf(".") + 1)
    return "$name.$extension"
}

fun getBitmapFromVectorDrawable(context: Context?, drawableId: Int): Bitmap? {
    var drawable = ContextCompat.getDrawable(context!!, drawableId)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawable = DrawableCompat.wrap(drawable!!).mutate()
    }
    val bitmap = Bitmap.createBitmap(
        drawable!!.intrinsicWidth,
        drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
    drawable.draw(canvas)
    return bitmap
}

@BindingAdapter(value = ["image"], requireAll = false)
fun ImageView.loadImage(path: String?) {
    path?.let {
        if (it.contains("http")) {
            Glide.with(this)
                .load(it.replace("file://", ""))
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .error(R.drawable.rectangle).into(this)
        } else {
            Glide.with(this).load(it).diskCacheStrategy(DiskCacheStrategy.DATA).placeholder(R.drawable.rectangle)
                .error(R.drawable.rectangle).into(this)
        }
    }
    if (path.isNullOrEmpty()) {
        this.setImageResource(R.drawable.rectangle)
    }
}

internal fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))

internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)

/** set view visible */
fun View.visible() {
    this.visibility = View.VISIBLE
}

/**
 * set View Gone
 */
fun View.gone() {
    this.visibility = View.GONE
}

/**
 * set View Invisible
 */
fun View.invisible() {
    this.visibility = View.INVISIBLE
}

 fun convertDate(date: String): String {
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat: DateFormat = SimpleDateFormat("MMM dd yyyy")
    val inputDateStr = date
    val newDate: Date = inputFormat.parse(inputDateStr)
    return outputFormat.format(newDate)
}

 fun convertTime(time:String):String{
   /* val sdf = SimpleDateFormat("hh:mm:ss")
    val sdfs = SimpleDateFormat("hh:mm a")
    val newTime = sdf.parse(time)
    return sdfs.format(newTime)*/
     Timber.e("Time->${time}")
     return try {
         val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
         val dateObj = sdf.parse(time)
         System.out.println(dateObj)
         println(SimpleDateFormat("K:mma").format(dateObj))
         SimpleDateFormat("hh:mm a").format(dateObj)
     } catch (e: ParseException) {
         e.printStackTrace()
         time
     }
}

fun convertDateAndTime(date: String):String{
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val dateObj = sdf.parse(date)
        System.out.println(dateObj)
        println(SimpleDateFormat("DD MMM yyyy hh:mm a").format(dateObj))
        SimpleDateFormat("dd MMM yyyy hh:mm a").format(dateObj)
    } catch (e: ParseException) {
        e.printStackTrace()
        date
    }
}

fun convertNormalDateAndTime(date: String):String{
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val dateObj = sdf.parse(date)
        System.out.println(dateObj)
        println(SimpleDateFormat("dd MMM yyyy hh:mm a").format(dateObj))
        SimpleDateFormat("dd MMM yyyy hh:mm a").format(dateObj)
    } catch (e: ParseException) {
        e.printStackTrace()
        date
    }
}

fun String.getDateWithServerTimeStamp(): Date? {
    val dateFormat = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
    try {
        return dateFormat.parse(this)
    } catch (e: ParseException) {
        return null
    }
}
fun Date.getStringTimeStampWithDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")
    return dateFormat.format(this)
}

fun convertToCustomFormat(dateStr: String?): String {
    val utc = TimeZone.getTimeZone("UTC")
    val sourceFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val destFormat = SimpleDateFormat("dd-MMM-yyyy")
    sourceFormat.timeZone = utc
    val convertedDate = sourceFormat.parse(dateStr)
    return destFormat.format(convertedDate)
}


fun getDocPath(context: Context, uri: Uri): String {
    context.contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
    val path = makeFileCopyInCacheDir(context, uri)
    return path.toString()
}

fun View.hideIf(flag: Boolean) {
    if (flag) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}


fun NavController.isOnBackStack(@IdRes id: Int): Boolean = try { getBackStackEntry(id); true } catch(e: Throwable) { false }


fun makeFileCopyInCacheDir(context: Context, contentUri: Uri): String? {
    val contentResolver = context.contentResolver
    try {
        val filePathColumn = arrayOf(
            //Base File
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.TITLE,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.SIZE,
            MediaStore.Files.FileColumns.DATE_ADDED,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            //Normal File
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.MIME_TYPE,
            MediaStore.MediaColumns.DISPLAY_NAME
        )
        //val contentUri = FileProvider.getUriForFile(context, "${BuildConfig.APPLICATION_ID}.provider", File(mediaUrl))
        val returnCursor =
            contentUri.let { contentResolver.query(it, filePathColumn, null, null, null) }
        if (returnCursor != null) {
            returnCursor.moveToFirst()
            val nameIndex = returnCursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
            val name = returnCursor.getString(nameIndex)
            val file = File(context.cacheDir, name)
            val inputStream = contentResolver.openInputStream(contentUri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable = inputStream!!.available()

            //int bufferSize = 1024;
            val bufferSize = Math.min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream.read(buffers).also { read = it } != -1) {
                outputStream.write(buffers, 0, read)
            }
            inputStream.close()
            outputStream.close()
            return file.absolutePath
        }
    } catch (ex: Exception) {
        Timber.e("Exception ${ex.message}")
    }
    return contentUri.let { RealPathUtil.getRealPath(context, it).toString() }
}







