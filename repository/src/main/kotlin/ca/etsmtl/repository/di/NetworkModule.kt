package ca.etsmtl.repository.di

import ca.etsmtl.repository.data.api.SignetsApi
import ca.etsmtl.repository.util.ApplicationJsonAdapterFactory
import ca.etsmtl.repository.util.LiveDataCallAdapterFactory
import ca.etsmtl.repository.util.SignetsTrust
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Sonphil on 28-02-18.
 */
@Module
internal open class NetworkModule {

    companion object {
        val instance = NetworkModule()
        private const val SIGNETS_URL = "https://signets-ens.etsmtl.ca/Secure/WebServices/SignetsMobile.asmx/"
    }

    @Singleton @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return SignetsTrust().client
    }

    @Singleton @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()
    }

    @Singleton @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(SIGNETS_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    @Singleton @Provides
    fun provideSignetsApi(retrofit: Retrofit): SignetsApi {
        return retrofit.create(SignetsApi::class.java)
    }
}
