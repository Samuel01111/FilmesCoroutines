package br.com.leumas.filmescoroutines.data.repository

import br.com.leumas.filmescoroutines.ui.main.Filme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainRepositoryImp: MainRepository {

    override fun getFilmes(callback: (filmes: List<Filme>) -> Unit) {
        Thread(Runnable {
            Thread.sleep(2000)
            callback.invoke(
                listOf(
                    Filme(1, "Titulo 1"),
                    Filme(2, "Titulo 2")
                )
            )
        }).start()
    }

    //creating it in a diferent thread.
    override suspend fun getFilmesCoroutines(): List<Filme> {
        return withContext(Dispatchers.Default) {
            delay(3000)
            listOf(
                Filme(1, "Titulo 1"),
                Filme(2, "Titulo 2")
            )
        }
    }
}
