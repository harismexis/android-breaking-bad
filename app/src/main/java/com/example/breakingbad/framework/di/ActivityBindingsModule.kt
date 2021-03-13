package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.base.BaseActivity
import com.example.breakingbad.presentation.bbcharacterdetail.ui.BBActorDetailActivity
import com.example.breakingbad.presentation.home.ui.activity.HomeActivity

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun itemDetailActivity(): BBActorDetailActivity

}
