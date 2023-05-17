package com.love.lovelive.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import java.util.*


class MyLocation {
    var timer1: Timer? = null
    var lm: LocationManager? = null
    var locationResult: LocationResult? = null
    var gps_enabled = false
    var network_enabled = false
    fun getLocation(context: Context, result: LocationResult?): Boolean {
        //I use LocationResult callback class to pass location value from MyLocation to user code.
        locationResult = result
        if (lm == null) lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //exceptions will be thrown if provider is not permitted.
        try {
            gps_enabled = lm!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
        }
        try {
            network_enabled = lm!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
        }

        //don't start listeners if no provider is enabled
        if (!gps_enabled && !network_enabled) return false
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return false
        }
        if (gps_enabled) lm?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0,
            0f,
            locationListenerGps
        )
        if (network_enabled) lm?.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0,
            0f,
            locationListenerNetwork
        )
        timer1 = Timer()
        timer1?.schedule(GetLastLocation(context), 20000)
        return true
    }

    var locationListenerGps: LocationListener = object : LocationListener {
        override fun onLocationChanged(p0: Location) {
            timer1?.cancel()
            locationResult!!.gotLocation(p0)
            lm?.removeUpdates(this)
            lm?.removeUpdates(locationListenerNetwork)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }
    var locationListenerNetwork: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            timer1?.cancel()
            locationResult!!.gotLocation(location)
            lm?.removeUpdates(this)
            lm?.removeUpdates(locationListenerGps)
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }

    internal inner class GetLastLocation(val context: Context) : TimerTask() {
        override fun run() {
            lm?.removeUpdates(locationListenerGps)
            lm?.removeUpdates(locationListenerNetwork)
            var net_loc: Location? = null
            var gps_loc: Location? = null
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            if (gps_enabled) gps_loc = lm!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (network_enabled) net_loc =
                lm!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            //if there are both values use the latest one
            if (gps_loc != null && net_loc != null) {
                if (gps_loc.getTime() > net_loc.getTime()) locationResult!!.gotLocation(gps_loc) else locationResult!!.gotLocation(
                    net_loc
                )
                return
            }
            if (gps_loc != null) {
                locationResult!!.gotLocation(gps_loc)
                return
            }
            if (net_loc != null) {
                locationResult!!.gotLocation(net_loc)
                return
            }
            locationResult!!.gotLocation(null)
        }
    }

    abstract class LocationResult {
        abstract fun gotLocation(location: Location?)
    }
}