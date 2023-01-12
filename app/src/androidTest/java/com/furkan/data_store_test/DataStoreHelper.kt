package com.furkan.data_store_test

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.platform.app.InstrumentationRegistry
import com.data_store_test.DataStoreRepository

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