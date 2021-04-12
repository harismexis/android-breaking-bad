package com.harismexis.breakingbad.setup.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.harismexis.breakingbad.setup.viewmodel.MockActorDetailVmProvider
import com.harismexis.breakingbad.setup.viewmodel.MockHomeVmProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class InstrumentedViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        provideMockVmMap()[modelClass]?.get() as T
}

fun provideMockVmMap(): MutableMap<Class<out ViewModel>, Provider<ViewModel>> {
    val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>> = mutableMapOf()
    viewModels[HomeViewModel::class.java] = Provider { MockHomeVmProvider.mockHomeViewModel }
    viewModels[ActorDetailViewModel::class.java] = Provider { MockActorDetailVmProvider.mockActorDetailViewModel }
    return viewModels
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
abstract class InstrumentedViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: InstrumentedViewModelFactory)
            : ViewModelProvider.Factory
}