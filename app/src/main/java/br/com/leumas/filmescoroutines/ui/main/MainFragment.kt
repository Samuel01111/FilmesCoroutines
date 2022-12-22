package br.com.leumas.filmescoroutines.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.leumas.filmescoroutines.R
import br.com.leumas.filmescoroutines.data.repository.MainRepositoryImp
import br.com.leumas.filmescoroutines.ui.adapter.FilmesFragmentAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(MainRepositoryImp())
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        viewModel.filmesLiveData.observe(viewLifecycleOwner) { filmes ->
            recyclerViewFilmes.adapter = FilmesFragmentAdapter(this, filmes)
            recyclerViewFilmes.layoutManager = layoutManager
        }

        viewModel.getFilmesCoroutines()
    }
}
