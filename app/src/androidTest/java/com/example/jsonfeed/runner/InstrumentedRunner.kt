package com.example.jsonfeed.runner

import android.app.Application
import android.content.Context

import androidx.test.runner.AndroidJUnitRunner

import com.example.jsonfeed.application.InstrumentedMainApplication

open class InstrumentedRunner : AndroidJUnitRunner() {

    override fun newApplication(
        classLoader: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(classLoader, InstrumentedMainApplication::class.java.name, context)
    }
}