package com.example.jsonfeed.framework.di

import com.example.jsonfeed.framework.base.BaseActivity
import com.example.jsonfeed.presentation.detail.ui.ItemDetailActivity
import com.example.jsonfeed.presentation.home.ui.HomeActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun itemDetailActivity(): ItemDetailActivity

}
