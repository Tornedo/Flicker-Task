package com.coding.flickertask.di.module

import com.coding.flickertask.presentation.viewmodel.PhotoListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class PhotoListViewModelModule {
	companion object{
		@ExperimentalCoroutinesApi
		val modules = module {
			viewModel { PhotoListViewModel(get(),get()) }
		}
	}
}
