package com.mycompany.my.challenge

import com.mycompany.my.challenge.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ChallengeApp : DaggerApplication() {


    companion object {
        lateinit var instance: ChallengeApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}