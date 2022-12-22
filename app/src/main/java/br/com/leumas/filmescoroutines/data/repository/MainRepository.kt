package br.com.leumas.filmescoroutines.data.repository

import br.com.leumas.filmescoroutines.ui.main.Filme

interface MainRepository {

    fun getFilmes(callback: (filmes: List<Filme>) -> Unit)

    suspend fun getFilmesCoroutines(): List<Filme>
}
