package com.alltogether.alltogetherandroid.di

import com.alltogether.alltogetherandroid.ActivityViewModel
import com.alltogether.alltogetherandroid.ui.login.LoginViewModel
import com.alltogether.alltogetherandroid.ui.main.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel {
        ActivityViewModel()
    }
    viewModel {
        MainViewModel()
    }
    viewModel {
        FeedViewModel()
    }
    viewModel {
        SupporterSearchViewModel(get())
    }
    viewModel {
        CommunityViewModel()
    }
    viewModel {
        AlarmViewModel()
    }
    viewModel {
        GiftViewModel()
    }

}