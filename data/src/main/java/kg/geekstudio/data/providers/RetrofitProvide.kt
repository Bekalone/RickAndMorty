package kg.geekstudio.data.providers

import kg.geekstudio.data.BuildConfig
import kg.geekstudio.data.apiservice.CharacterApi
import kg.geekstudio.data.apiservice.EpisodeApi
import kg.geekstudio.data.apiservice.LocationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvide {

    fun provideCharacterApi(): CharacterApi {
        return provideRetrofit(provideClient()).create(CharacterApi::class.java)
    }
    fun provideLocationApi(): LocationApi {
        return provideRetrofit(provideClient()).create(LocationApi::class.java)
    }
    fun provideEpisodeApi(): EpisodeApi {
        return provideRetrofit(provideClient()).create(EpisodeApi::class.java)
    }

    private fun provideRetrofit(okkHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okkHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun provideClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
    }
}