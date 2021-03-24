package com.harismexis.breakingbad.setup.runner

import android.app.Application
import android.content.Context

import androidx.test.runner.AndroidJUnitRunner

import com.harismexis.breakingbad.setup.application.InstrumentedMainApplication

open class InstrumentedRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, InstrumentedMainApplication::class.java.name, context)
    }
}