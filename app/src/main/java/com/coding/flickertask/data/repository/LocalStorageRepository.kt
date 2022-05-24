package com.coding.flickertask.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.coding.flickertask.data.model.FetchImageRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@ExperimentalCoroutinesApi
@FlowPreview

const val DataStore_NAME = "USER_HISTORY"

class LocalStorageRepository(private val context: Context) : ILocalStorageRepository {

    private val sharedPreference: SharedPreferences =
        context.getSharedPreferences(DataStore_NAME, Context.MODE_PRIVATE)

    companion object {
        const val SEARCH_QUERY = "QUERY_STRING"
    }

    override fun savePhoneBook(historyData: FetchImageRequest) {
        val editor = sharedPreference.edit()

        val previousValue = getPhoneBook()?.let {
            it.split(",")
        }?.distinct()
        /*
            Before storing the search query checking the search query already exist or not if exist then
            not saving the the search query in shared preference
         */
        if (previousValue != null) {
            if (!previousValue.contains(historyData.searchQuery))
                editor.putString(
                    SEARCH_QUERY.toString(),
                    historyData.searchQuery + "," + previousValue.joinToString(",")
                )
        } else {
            editor.putString(SEARCH_QUERY, historyData.searchQuery + ",")
        }
        editor.apply()
    }

    override fun getPhoneBook(): String? = sharedPreference.getString(SEARCH_QUERY.toString(), "")
}