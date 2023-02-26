package kg.geekstudio.rickandmorty.di

import android.content.Context
import android.content.SharedPreferences
import kg.geekstudio.data.providers.RetrofitProvide
import kg.geekstudio.data.providers.PreferencesButton
import kg.geekstudio.data.repositories.CharacterRepositoryImpl
import kg.geekstudio.data.repositories.EpisodeRepositoryImpl
import kg.geekstudio.data.repositories.LocationRepositoryImpl
import kg.geekstudio.domain.repositories.CharacterRepository
import kg.geekstudio.domain.repositories.EpisodeRepository
import kg.geekstudio.domain.repositories.LocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<CharacterRepository> {
        CharacterRepositoryImpl(characterApi = get())
    }
    single<LocationRepository> {
        LocationRepositoryImpl(locationApi = get())
    }
    single<EpisodeRepository> {
        EpisodeRepositoryImpl(episodeApi = get())
    }

    single { RetrofitProvide().provideCharacterApi() }
    single { RetrofitProvide().provideLocationApi() }
    single { RetrofitProvide().provideEpisodeApi() }

    single<SharedPreferences> { androidContext().getSharedPreferences("filter", Context.MODE_PRIVATE) }

    /* в моем случае мне нужен factory т.к
    можно обойти логику приложения без viewModel */
    factory { provideSharedPreferences(get()) }
}

fun provideSharedPreferences(sharedPreferences: SharedPreferences): PreferencesButton {
    return PreferencesButton(sharedPreferences)
}



