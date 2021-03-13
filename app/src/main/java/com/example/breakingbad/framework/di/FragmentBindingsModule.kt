package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.presentation.home.ui.fragment.HomeFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

//    @ContributesAndroidInjector
//    abstract fun itemDetailFragment(): ItemDetailFragment

}
