package com.example.breakingbad.framework.base

import android.content.Context
import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    open fun initialise() {
        initialiseViewBinding()
        initialiseView()
        initialiseViewModel()
        observeLiveData()
    }

    abstract fun initialiseViewBinding()

    //abstract fun getRootView(): View

    abstract fun initialiseViewModel()

    abstract fun getToolbar(): Toolbar?

    abstract fun observeLiveData()

    open fun initialiseView() {

    }


}