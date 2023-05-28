package dev.ronnie.imageloaderdagger2.di.modules



import com.example.imageloaderdagger2.presentation.fragments.MemoryGameFragment
import com.example.imageloaderdagger2.presentation.fragments.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMemoryGameFragment(): MemoryGameFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

}
