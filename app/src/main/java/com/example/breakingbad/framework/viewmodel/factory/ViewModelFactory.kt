package com.example.breakingbad.framework.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.example.breakingbad.presentation.screens.deaths.viewmodel.DeathViewModel
import com.example.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.example.breakingbad.presentation.screens.quotes.viewmodel.QuoteViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ActorDetailViewModel::class)
    internal abstract fun actorDetailViewModel(viewModel: ActorDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuoteViewModel::class)
    internal abstract fun quoteViewModel(viewModel: QuoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeathViewModel::class)
    internal abstract fun deathViewModel(viewModel: DeathViewModel): ViewModel
}