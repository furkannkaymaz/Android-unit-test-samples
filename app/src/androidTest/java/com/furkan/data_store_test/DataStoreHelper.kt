package com.furkan.data_store_test

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.furkan.test_codes.data_store_test.DataStoreRepository

class DataStoreHelper {
    companion object{
         const val TEST_DATASTORE_NAME: String = "test_datastore"
         val MY_STRING = stringPreferencesKey("MY_STRING")
         val MY_INT = intPreferencesKey("MY_INT")
    }
}

fun createDataStore(testDataStore: DataStore<Preferences>) : DataStoreRepository{

    return DataStoreRepository(testDataStore)
}