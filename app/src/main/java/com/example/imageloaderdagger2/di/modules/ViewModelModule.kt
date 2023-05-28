package dev.ronnie.imageloaderdagger2.di.modules

import android.webkit.WebView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imageloaderdagger2.presentation.viewmodels.MemoryGameViewModel
import com.example.imageloaderdagger2.presentation.viewmodels.RegisterViewModel
import com.example.imageloaderdagger2.presentation.viewmodels.WebViewViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import dev.ronnie.imageloaderdagger2.presentation.viewmodels.ViewModelFactory
import kotlin.reflect.KClass

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(WebViewViewModel::class)
    abstract fun bindWebViewViewModel(webViewViewModel: WebViewViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MemoryGameViewModel::class)
    abstract fun bindMemoryGameViewModel(memoryGameViewModel: MemoryGameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
