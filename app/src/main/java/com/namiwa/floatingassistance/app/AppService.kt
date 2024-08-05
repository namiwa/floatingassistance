package com.namiwa.floatingassistance.app

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi

class AppService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
       return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        handleFloatingWidget()
        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun handleFloatingWidget() {
        Log.i("AppService", "started floating widget")

        // https://stackoverflow.com/a/56335967/13941170
        if (!Settings.canDrawOverlays(applicationContext)) {
            Log.i("AppService", "requesting permission")
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + applicationContext.packageName))
            intent.setPackage(applicationContext.packageName)
            startService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}