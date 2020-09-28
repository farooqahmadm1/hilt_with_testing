package com.example.hilt_with_testing

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.matcher.ViewMatchers
import com.example.hilt_with_testing.hilt.AppModule
import com.example.hilt_with_testing.network.RestApiServices
import com.example.hilt_with_testing.ui.HomeFragment
import com.example.hilt_with_testing.utility.Constants
import com.example.hilt_with_testing.utility.MainFragmentFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@UninstallModules(AppModule::class)
@HiltAndroidTest
class MainTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @Before fun init(){ hiltRule.inject() }

    @Inject lateinit var something : String
    @Inject lateinit var fragmentFactory: MainFragmentFactory
    @Inject lateinit var restApiServices: RestApiServices

    @InstallIn(ApplicationComponent::class)
    @Module
    class TestAppModule{
        @Singleton
        @Provides
        fun provideSomething(): String{
            return "It's some TESTING String!"
        }

        @Provides
        fun provideRetrofit(client: OkHttpClient): RestApiServices = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RestApiServices::class.java)

        @Singleton
        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            val httpInterceptor = HttpLoggingInterceptor()
            httpInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(httpInterceptor)
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }.build()
        }
    }

    @Test
    fun test_some(){
        ViewMatchers.assertThat(something, Matchers.containsString("some"))
    }

    @Test
    fun mainActivityTest(){
        ActivityScenario.launch(MainActivity::class.java)
    }


    @Test
    fun homeFragmentTest(){
        launchFragmentInHiltContainer<HomeFragment> (fragmentFactory = fragmentFactory)
    }
}