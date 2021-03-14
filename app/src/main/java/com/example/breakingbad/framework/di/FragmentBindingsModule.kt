package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.presentation.screens.actordetail.ui.fragment.ActorDetailFragment
import com.example.breakingbad.presentation.screens.deaths.ui.fragment.DeathFragment
import com.example.breakingbad.presentation.screens.episodes.ui.fragment.EpisodeFragment
import com.example.breakingbad.presentation.screens.home.ui.fragment.HomeFragment
import com.example.breakingbad.presentation.screens.quotes.ui.fragment.QuoteFragment

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
    abstract fun quoteFragment(): QuoteFragment

    @ContributesAndroidInjector
    abstract fun deathFragment(): DeathFragment

    @ContributesAndroidInjector
    abstract fun episodesFragment(): EpisodeFragment

}
