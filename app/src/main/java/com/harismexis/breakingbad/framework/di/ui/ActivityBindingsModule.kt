package com.harismexis.breakingbad.framework.di.ui

import com.harismexis.breakingbad.framework.base.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

}
