package com.harismexis.breakingbad.framework.di.interactor.screens

import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActor
import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActors
import com.harismexis.breakingbad.interactors.actor.IrrGetRemoteActors
import com.harismexis.breakingbad.interactors.actor.IrrStoreActors
import com.harismexis.breakingbad.interactors.death.IrrGetLocalDeaths
import com.harismexis.breakingbad.interactors.death.IrrGetRemoteDeaths
import com.harismexis.breakingbad.interactors.death.IrrStoreDeaths
import com.harismexis.breakingbad.interactors.episode.IrrGetLocalEpisodes
import com.harismexis.breakingbad.interactors.episode.IrrGetRemoteEpisodes
import com.harismexis.breakingbad.interactors.episode.IrrStoreEpisodes
import com.harismexis.breakingbad.interactors.quote.IrrGetLocalQuotes
import com.harismexis.breakingbad.interactors.quote.IrrGetRemoteQuotes
import com.harismexis.breakingbad.interactors.quote.IrrStoreQuotes
import com.harismexis.breakingbad.presentation.screens.actordetail.interactors.ActorDetailInteractors
import com.harismexis.breakingbad.presentation.screens.deaths.interactors.DeathInteractors
import com.harismexis.breakingbad.presentation.screens.episodes.interactors.EpisodeInteractors
import com.harismexis.breakingbad.presentation.screens.home.interactors.HomeInteractors
import com.harismexis.breakingbad.presentation.screens.quotes.interactors.QuoteInteractors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScreensInteractorModule {

    @Provides
    @Singleton
    fun provideHomeInteractors(
        irrGetLocalActors: IrrGetLocalActors,
        irrGetRemoteActors: IrrGetRemoteActors,
        irrStoreActors: IrrStoreActors
    ): HomeInteractors {
        return HomeInteractors(
            irrGetLocalActors,
            irrGetRemoteActors,
            irrStoreActors
        )
    }

    @Provides
    @Singleton
    fun provideActorDetailInteractors(
        irrGetLocalActor: IrrGetLocalActor
    ): ActorDetailInteractors {
        return ActorDetailInteractors(irrGetLocalActor)
    }

    @Provides
    @Singleton
    fun provideQuoteInteractors(
        irrGetRemoteQuotes: IrrGetRemoteQuotes,
        irrGetLocalQuotes: IrrGetLocalQuotes,
        irrStoreQuotes: IrrStoreQuotes
    ): QuoteInteractors {
        return QuoteInteractors(
            irrGetRemoteQuotes,
            irrGetLocalQuotes,
            irrStoreQuotes
        )
    }

    @Provides
    @Singleton
    fun provideEpisodeInteractors(
        irrGetRemoteEpisodes: IrrGetRemoteEpisodes,
        irrGetLocalEpisodes: IrrGetLocalEpisodes,
        irrStoreEpisodes: IrrStoreEpisodes
    ): EpisodeInteractors {
        return EpisodeInteractors(
            irrGetRemoteEpisodes,
            irrGetLocalEpisodes,
            irrStoreEpisodes
        )
    }

    @Provides
    @Singleton
    fun provideDeathInteractors(
        irrGetRemoteDeaths: IrrGetRemoteDeaths,
        irrGetLocalDeaths: IrrGetLocalDeaths,
        irrStoreDeaths: IrrStoreDeaths
    ): DeathInteractors {
        return DeathInteractors(
            irrGetRemoteDeaths,
            irrGetLocalDeaths,
            irrStoreDeaths
        )
    }

}