package model

import android.app.Application
import android.content.Context


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        fun getContext(): Context {
            return context
        }

        var application: Application? = null
            private set
        val context: Context
            get() = application!!.applicationContext
    }
}