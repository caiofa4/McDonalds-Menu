package com.example.mcdonaldsmenu.ui

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<V : BaseView>(private val uiContext: CoroutineContext = Dispatchers.Main.immediate) : LifecycleObserver,
    CoroutineScope {

    protected var currentView: V? = null
    private val job = SupervisorJob()

    // Check if the view is attached.
    // This checking is only necessary when returning from an asynchronous call
    // return true if a view is attached to this presenter. false otherwise.

    protected val isViewAttached: Boolean get() = currentView != null

    fun attachView(view: V?) {
        if (currentView != null) {
            currentView = null
        }
        currentView = view
    }

    private fun detachView() {
        job.cancel()
        currentView = null
    }

    // cleanup
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cleanYourSelf() {
        detachView()
    }

    override val coroutineContext: CoroutineContext
        get() = uiContext + job
}
