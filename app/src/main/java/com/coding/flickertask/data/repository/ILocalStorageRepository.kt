package com.coding.flickertask.data.repository

import com.coding.flickertask.data.model.FetchImageRequest

interface ILocalStorageRepository {

      fun savePhoneBook(phonebook: FetchImageRequest)

      fun getPhoneBook():String?
}