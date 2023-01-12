package com.furkan.data_store_test

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.data_store_test.IDataStoreRepository
import com.furkan.data_store_test.DataStoreHelper.Companion.MY_INT
import com.furkan.data_store_test.DataStoreHelper.Companion.MY_STRING
import com.furkan.data_store_test.DataStoreHelper.Companion.TEST_DATASTORE_NAME
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import java.io.File

val testDataStore = PreferenceDataStoreFactory.create {
    InstrumentationRegistry.getInstrumentation().targetContext.preferencesDataStoreFile(
        TEST_DATASTORE_NAME
    )
}

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DataStoreTest {

    private lateinit var repository : IDataStoreRepository

    @Before
    fun start(){
        repository = createDataStore(testDataStore)
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun saveStringDataStoreSuccess() = runTest {
        var myString = ""
        repository.saveString(MY_STRING, "TEST")

        val data = repository.readString(MY_STRING)

        val collectJob = launch(UnconfinedTestDispatcher()) {
            data.collect {
                myString = it
            }
        }
        Assert.assertEquals("TEST", myString)
        collectJob.cancel()
    }

    @Test
    fun saveIntDataStoreSuccess() = runTest {
        var myInt = 0
        repository.saveInt(MY_INT, 5)

        val data = repository.readInt(MY_INT)

        val collectJob = launch(UnconfinedTestDispatcher()) {
            data.collect {
                myInt = it
            }
        }
        Assert.assertEquals(5, myInt)
        collectJob.cancel()
    }

    @After
    fun end() {
        runTest {
            testDataStore.edit {
                it.clear()
                File(
                    ApplicationProvider.getApplicationContext<Context>().filesDir,
                    "datastore"
                ).deleteRecursively()
            }
        }
    }
}