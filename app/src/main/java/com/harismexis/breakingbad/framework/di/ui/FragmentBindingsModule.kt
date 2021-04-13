package com.harismexis.breakingbad.framework.di.ui

import com.harismexis.breakingbad.presentation.base.BaseFragment
import com.harismexis.breakingbad.presentation.screens.actordetail.ui.fragment.ActorDetailFragment
import com.harismexis.breakingbad.presentation.screens.deaths.ui.fragment.DeathsFragment
import com.harismexis.breakingbad.presentation.screens.episodes.ui.fragment.EpisodesFragment
import com.harismexis.breakingbad.presentation.screens.home.ui.fragment.HomeFragment
import com.harismexis.breakingbad.presentation.screens.player.PlayerFragment
import com.harismexis.breakingbad.presentation.screens.quotes.ui.fragment.QuotesFragment

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

    @ContributesAndroidInjector
    abstract fun playerFragment(): PlayerFragment

}
