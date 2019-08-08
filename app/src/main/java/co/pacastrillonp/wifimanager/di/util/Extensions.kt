package co.pacastrillonp.wifimanager.di.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.*

// region Fragments

/**
 * Allows calls like
 *
 * `supportFragmentManager.inTransaction { add(...) }`
 */
inline fun androidx.fragment.app.FragmentManager.inTransaction(func: androidx.fragment.app.FragmentTransaction.() -> androidx.fragment.app.FragmentTransaction) {
    beginTransaction().func().commit()
}

// endregion
// region ViewModels

/**
 * For Actvities, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> androidx.fragment.app.FragmentActivity.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> androidx.fragment.app.Fragment.viewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the Activity.
 */
inline fun <reified VM : ViewModel> androidx.fragment.app.Fragment.activityViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(requireActivity(), provider).get(VM::class.java)

/**
 * Like [Fragment.viewModelProvider] for Fragments that want a [ViewModel] scoped to the parent
 * Fragment.
 */
inline fun <reified VM : ViewModel> androidx.fragment.app.Fragment.parentViewModelProvider(
    provider: ViewModelProvider.Factory
) =
    ViewModelProviders.of(parentFragment!!, provider).get(VM::class.java)

// endregion
// region LiveData

/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}


/** Uses `Transformations.switchMap` on a LiveData */
fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, body)
}

fun <T> MutableLiveData<T>.setValueIfNew(newValue: T) {
    if (this.value != newValue) value = newValue
}

fun <T> MutableLiveData<T>.postValueIfNew(newValue: T) {
    if (this.value != newValue) postValue(newValue)
}
// endregion
// region Kotlin

/**
 * Helper to force a when statement to assert all options are matched in a when statement.
 *
 * By default, Kotlin doesn't care if all branches are handled in a when statement. However, if you
 * use the when statement as an expression (with a value) it will force all cases to be handled.
 *
 * This helper is to make a lightweight way to say you meant to match all of them.
 *
 * Usage:
 *
 * ```
 * when(sealedObject) {
 *     is OneType -> //
 *     is AnotherType -> //
 * }.checkAllMatched
 */
val <T> T.checkAllMatched: T
    get() = this

/** Convenience for callbacks/listeners whose return value indicates an event was consumed. */
inline fun consume(f: () -> Unit): Boolean {
    f()
    return false
}

// endregion