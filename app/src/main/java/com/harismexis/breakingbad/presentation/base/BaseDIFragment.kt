package com.harismexis.breakingbad.presentation.base

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseDIFragment : BaseFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    open fun inject() {
        AndroidSupportInjection.inject(this)
    }

}