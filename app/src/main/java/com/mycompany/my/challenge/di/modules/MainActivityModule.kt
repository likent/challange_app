package com.mycompany.my.challenge.di.modules

import com.mycompany.my.challenge.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(
        modules = [MoviesFragmentModule::class]
    )
    internal abstract fun mainActivity(): MainActivity
}