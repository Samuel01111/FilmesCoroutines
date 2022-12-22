package br.com.leumas.filmescoroutines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.leumas.filmescoroutines.R
import br.com.leumas.filmescoroutines.ui.main.Filme
import kotlinx.android.synthetic.main.row_layout_movie.view.*

class FilmesFragmentAdapter(
    private val fragment: Fragment,
    private val listOfFilmes: List<Filme>
): RecyclerView.Adapter<FilmesFragmentAdapter.FilmesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmesViewHolder {
        val view = LayoutInflater.from(fragment.context).inflate(R.layout.row_layout_movie, parent, false)
        return FilmesViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmesViewHolder, position: Int) {
        val filme = listOfFilmes[position]

        holder.bindView(filme)
    }

    override fun getItemCount(): Int {
        return listOfFilmes.size
    }

    class FilmesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(filme: Filme) {
            val id = itemView.textview_id
            val title = itemView.textview_title

            id.text = filme.id.toString()
            title.text = filme.titulo
        }
    }
}
