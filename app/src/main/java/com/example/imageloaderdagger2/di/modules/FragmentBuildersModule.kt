package dev.ronnie.imageloaderdagger2.di.modules



import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.ronnie.imageloaderdagger2.presentation.fragments.FragmentFullScreen
import dev.ronnie.imageloaderdagger2.presentation.fragments.FragmentPictures


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributePictureFragment(): FragmentPictures

    @ContributesAndroidInjector
    abstract fun contributeFullScreenFragment(): FragmentFullScreen

}
