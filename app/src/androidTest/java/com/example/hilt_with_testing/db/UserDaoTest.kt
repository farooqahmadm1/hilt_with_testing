package com.example.hilt_with_testing.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.hilt_with_testing.modal.UserResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: HiltTestingDatabase
    private lateinit var dao: UserDao
    private lateinit var context: Context
    private lateinit var userList : List<UserResponse>

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, HiltTestingDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.UserDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertUserItem() = runBlockingTest {
        val userItem = UserResponse(1,"farooq","Sincere@april.biz","hildegard.org")
        dao.insert(userItem)
        val job = launch {
            dao.getUsers().collect {
                userList = it
            }
        }
        job.cancel()
        assertThat(userList[0]).isEqualTo(userItem)
    }


    @Test
    fun deleteUserItem() = runBlockingTest {
        val userItem = UserResponse(1,"farooq","Sincere@april.biz","hildegard.org")
        dao.insert(userItem)
        dao.delete(userItem)
        val job = launch {
            dao.getUsers().collect {
                userList = it
            }
        }
        job.cancel()
        assertThat(userList.size).isEqualTo(0)
    }

    @Test
    fun updateUserItem() = runBlockingTest {
        val userItem = UserResponse(1,"farooq","Sincere@april.biz","hildegard.org")
        dao.insert(userItem)
        userItem.name = "Ali"
        dao.update(userItem)
        val job = launch {
            dao.getUsers().collect {
                userList = it
            }
        }
        job.cancel()
        assertThat(userItem.name).isEqualTo(userList[0].name)
    }
}