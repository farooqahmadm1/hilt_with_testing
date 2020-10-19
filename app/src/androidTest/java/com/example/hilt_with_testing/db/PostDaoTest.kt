package com.example.hilt_with_testing.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.hilt_with_testing.modal.Post
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
@MediumTest
class PostDaoTest {

    private lateinit var context: Context
    private lateinit var database: HiltTestingDatabase
    private lateinit var dao: PostDao
    private lateinit var list :List<Post>

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, HiltTestingDatabase::class.java)
            .build()
        dao = database.postDao()
    }


    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertPostItem() = runBlockingTest {
        val postItem = Post(
            1,
            1,
            "ea molestias quasi exercitationem",
            "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus"
        )
        dao.insert(postItem)
        val job = launch {
            dao.getPosts().collect {
                list = it
            }
        }
        job.cancel()
        assertThat(list[0]).isEqualTo(postItem)
    }

    @Test
    fun updatePostItem() = runBlockingTest {
        val postItem = Post(
            1,
            1,
            "ea molestias quasi exercitationem",
            "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus"
        )
        dao.insert(postItem)
        postItem.userId = 3
        dao.update(postItem)
        val job = launch {
            dao.getPosts().collect {
                list = it
            }
        }
        job.cancel()
        assertThat(list[0].userId).isEqualTo(postItem.userId)
    }

    @Test
    fun deletePostItem() = runBlockingTest {
        val postItem = Post(
            1,
            1,
            "ea molestias quasi exercitationem",
            "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus"
        )
        dao.insert(postItem)
        dao.delete(postItem)
        val job = launch {
            dao.getPosts().collect {
                list = it
            }
        }
        job.cancel()
        assertThat(list.size).isEqualTo(0)
    }

    @Test
    fun getPosts() = runBlockingTest {
        val postItem1 = Post(
            1,
            1,
            "ea molestias quasi exercitationem",
            "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus"
        )
        val postItem2 = Post(
            2,
            1,
            "ea molestias quasi exercitationem",
            "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus"
        )
        val postItem3 = Post(
            3,
            1,
            "ea molestias quasi exercitationem",
            "et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus"
        )
        dao.insert(postItem1)
        dao.insert(postItem2)
        dao.insert(postItem3)
        val job = launch {
            dao.getPosts().collect {
                list = it
            }
        }
        job.cancel()
        assertThat(list.size).isEqualTo(3)
    }
}