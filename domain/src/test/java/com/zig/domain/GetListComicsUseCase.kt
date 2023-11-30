package com.zig.domain

import com.zig.data.model.ComicModel
import com.zig.data.repository.ComicsRepository
import com.zig.domain.comics.GetListComicsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetListComicsUseCaseTest {
    private val repository: ComicsRepository = mockk()
    private val useCase = GetListComicsUseCase(repository)
    private val item: ComicModel = mockk()

    @Test
    fun `check if repository was called only once time`() = runBlocking {
        coEvery { repository.getListComics()} returns flowOf(item)

        useCase()

        coVerify(exactly = 1) {
            repository.getListComics()
        }
    }

    @Test
    fun `check if repository was return is the same object`() = runBlocking {
        coEvery { repository.getListComics() } returns flowOf(item)

        useCase().collect {
            Assert.assertSame(item, it)
        }
    }

    @Test
    fun `check if flow is cancelled`() = runBlocking {
        val job = launch {
            useCase().collect {}
        }
        job.cancelAndJoin()

        delay(100)

        coVerify(exactly = 0) {
            repository.getListComics()
        }
    }
}