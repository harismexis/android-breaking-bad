package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.presentation.screens.actordetail.ui.fragment.ActorDetailFragment
import com.example.breakingbad.presentation.screens.deaths.ui.fragment.DeathsFragment
import com.example.breakingbad.presentation.screens.episodes.ui.fragment.EpisodesFragment
import com.example.breakingbad.presentation.screens.home.ui.fragment.HomeFragment
import com.example.breakingbad.presentation.screens.quotes.ui.fragment.QuotesFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {

    @ContributesAndroidInjector
    abstract fun baseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun actorDetailFragment(): ActorDetailFragment

    @ContributesAndroidInjector
    abstract fun quoteFragment(): QuotesFragment

    @ContributesAndroidInjector
    abstract fun deathFragment(): DeathsFragment

    @ContributesAndroidInjector
    abstract fun episodesFragment(): EpisodesFragment

}
