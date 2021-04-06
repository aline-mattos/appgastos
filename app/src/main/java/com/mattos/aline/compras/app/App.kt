package com.mattos.aline.compras.app

import android.app.Application
import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.activity.contextaware.ContextAware
import androidx.activity.contextaware.OnContextAvailableListener
import java.text.SimpleDateFormat
import java.util.*

class App: Application(), ContextAware {

    var today: Date = Date()
    var dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)

    companion object {
        private var _instance: App? = null
        val instance: App
            get() = _instance ?: throw IllegalStateException("App not created")

        val displayMetrics : DisplayMetrics
            get() {
                val metrics = DisplayMetrics()
                (instance.getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(metrics)
                return metrics
            }

        val screenSize : Point
            get() {
                val size = Point()
                (instance.getContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(size)
                return size
            }
    }

    fun getContext(): Context = applicationContext

    override fun onCreate() {
        super.onCreate()
        _instance = this
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"))
    }

    override fun addOnContextAvailableListener(listener: OnContextAvailableListener) {}
    override fun peekAvailableContext(): Context? {
        return null
    }
    override fun removeOnContextAvailableListener(listener: OnContextAvailableListener) {}
}