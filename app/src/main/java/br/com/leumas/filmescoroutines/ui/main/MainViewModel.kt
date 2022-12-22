package br.com.leumas.filmescoroutines.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.leumas.filmescoroutines.data.repository.MainRepository
import br.com.leumas.filmescoroutines.data.repository.MainRepositoryImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: MainRepository
) : ViewModel() {

    val filmesLiveData = MutableLiveData<List<Filme>>()

    fun getFilmes() {
        repository.getFilmes { filmes ->
            filmesLiveData.postValue(filmes)
        }
    }

    //Using CoroutineScope to do not implement a suspend function inside of our ViewModel
    //Using launch because this is the entry point
    fun getFilmesCoroutines() {
        CoroutineScope(Dispatchers.Main).launch {
            // with await(), the suspend function repository.getFilmesCoroutines()
            // execute in a diferent suspend thread and the main thread continue right after it finish the requisition.
            val filmes = withContext(Dispatchers.Default) {
                repository.getFilmesCoroutines()
            }
            // here i can use the filmes variable
            filmesLiveData.value = filmes
        }
    }

    class MainViewModelFactory(private val repository: MainRepositoryImp) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}
