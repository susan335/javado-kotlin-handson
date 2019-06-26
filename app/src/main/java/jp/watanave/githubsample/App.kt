package jp.watanave.githubsample

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import jp.watanave.githubsample.data.Api
import jp.watanave.githubsample.data.GithubApi
import jp.watanave.githubsample.ui.main.MainViewModel
import jp.watanave.githubsample.util.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class App: Application() {

    open val viewModel: MainViewModel by lazy {
        MainViewModel(this.githubApi, this.dispatchers)
    }
    open val dispatchers: Dispatchers by lazy {
        Dispatchers()
    }
    open val githubApi: GithubApi by lazy {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val api = retrofit.create(Api::class.java)
        GithubApi(api)
    }

    companion object {
        lateinit var instance: App; private set
    }

    override fun onCreate() {
        super.onCreate()
        App.instance = this
    }
}
