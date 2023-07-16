package com.sample

import android.app.Application
import com.sample.practiceapp.network.RetrofitInstance
import com.sample.practiceapp.repository.TypiCodeRepository
import com.sample.practiceapp.repository.TypiCodeRepositoryImpl
import com.sample.practiceapp.viewmodel.PhotoViewModel
import com.sample.practiceapp.viewmodel.ToDoViewModel
import com.sample.practiceapp.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class PracticeApplication : Application() {

    private var listOfModules = module {

        single { RetrofitInstance.api }
        single<TypiCodeRepository> { TypiCodeRepositoryImpl(get()) }

        viewModel { ToDoViewModel(get()) }
        viewModel { UserViewModel(get()) }
        viewModel { PhotoViewModel(get()) }


    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // androidContext(this@PracticeApplication)
            modules(listOfModules)
        }
    }
}