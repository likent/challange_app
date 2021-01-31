package com.mycompany.my.challenge.di.modules

import androidx.lifecycle.ViewModelProvider
import com.mycompany.my.challenge.factories.MovieViewModelFactory
import com.mycompany.my.challenge.ui.MovieFragment
import com.mycompany.my.challenge.viewmodels.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesFragmentModule {
    @ContributesAndroidInjector
    internal abstract fun mainFragment(): MovieFragment

    @Binds
    internal abstract fun bindViewModelFactory(factory: MovieViewModelFactory): ViewModelProvider.Factory
}