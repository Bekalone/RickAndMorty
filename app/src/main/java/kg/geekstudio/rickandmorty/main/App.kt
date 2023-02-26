package kg.geekstudio.rickandmorty.main

import android.app.Application
import kg.geekstudio.rickandmorty.di.appModule
import kg.geekstudio.rickandmorty.di.dataModule
import kg.geekstudio.rickandmorty.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}