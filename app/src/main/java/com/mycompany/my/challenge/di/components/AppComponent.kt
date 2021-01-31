package com.mycompany.my.challenge.di.components

import android.app.Application
import com.mycompany.my.challenge.ChallengeApp
import com.mycompany.my.challenge.di.modules.AppModule
import com.mycompany.my.challenge.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MainActivityModule::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<ChallengeApp> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}