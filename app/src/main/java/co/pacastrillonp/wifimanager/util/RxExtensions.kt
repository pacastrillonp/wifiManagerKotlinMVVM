package co.pacastrillonp.wifimanager.util

import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

// Observable

/** Observes on main thread. */
fun <T> Observable<T>.subscribeOnUi(consumer: (Any: T) -> Unit): Disposable {
    return observeOnMainThread().subscribe { consumer(it) }
}

fun <T> Observable<T>.subscribeOnUi(disposable: CompositeDisposable): Disposable {
    return observeOnMainThread().subscribe().addTo(disposable)
}

/** Observes on main thread and add to disposable. */
fun <T> Observable<T>.subscribeOnUi(disposable: CompositeDisposable, consumer: (Any: T) -> Unit): Disposable {
    return subscribeOnUi(consumer).addTo(disposable)
}

/** Modifies the [Observable] to perform its emissions and notifications on the [AndroidSchedulers.mainThread]. */
fun <T> Observable<T>.observeOnMainThread(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread())

// Single

/** Observes on main thread. */
fun <T> Single<T>.subscribeOnUi(consumer: (Any: T) -> Unit): Disposable {
    return observeOnMainThread()
        .subscribe { value -> consumer(value) }
}

fun <T> Single<T>.subscribeOnUi(): Disposable {
    return observeOnMainThread()
        .subscribe()
}

/** Observes on main thread and add to disposable. */
fun <T> Single<T>.subscribeOnUi(disposable: CompositeDisposable, consumer: (Any: T) -> Unit): Disposable {
    return subscribeOnUi(consumer).addTo(disposable)
}

/** Modifies the [Single] to perform its emissions and notifications on the [AndroidSchedulers.mainThread]. */
fun <T> Single<T>.observeOnMainThread(): Single<T> =
    observeOn(AndroidSchedulers.mainThread())

// Completable

/** Observes on main thread. */
fun Completable.subscribeOnUi(): Disposable {
    return observeOnMainThread()
        .subscribe()
}

/** Modifies the [Completable] to perform its emissions and notifications on the [AndroidSchedulers.mainThread]. */
fun Completable.observeOnMainThread(): Completable =
    observeOn(AndroidSchedulers.mainThread())

// UI

data class Reactive<Base> (val base: Base)

val Button.rx: Reactive<Button> get() = Reactive(this)

val Reactive<Button>.isClickable: Observer<Boolean>
    get() {
        val realBase = this.base
        return object : Observer<Boolean> {

            override fun onNext(b: Boolean) {
                realBase.isEnabled = b
            }
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }

val Reactive<Button>.tap: Observable<View>
    get() {
        return Observable.create { emitter ->
            emitter.setCancellable {
                this.base.setOnClickListener(null)
                emitter.onComplete()
            }
            this.base.setOnClickListener(emitter::onNext)
        }
    }

val ProgressBar.rx: Reactive<ProgressBar> get() = Reactive(this)

val Reactive<ProgressBar>.isVisible: Observer<Boolean>
    get() {
        val realBase = this.base
        return object : Observer<Boolean> {

            override fun onNext(b: Boolean) {
                realBase.isVisible = b
            }
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }

val SwipeRefreshLayout.rx: Reactive<SwipeRefreshLayout> get() = Reactive(this)

val Reactive<SwipeRefreshLayout>.isRefreshing: Observer<Boolean>
    get() {
        val realBase = this.base
        return object : Observer<Boolean> {

            override fun onNext(b: Boolean) {
                realBase.isRefreshing = b
            }
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }
    }