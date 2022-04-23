package edu.uca.ni.clasificacion_pelicula.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import edu.uca.ni.clasificacion_pelicula.databinding.ItemListBinding
import edu.uca.ni.clasificacion_pelicula.fragments.ListFragmentDirections
import edu.uca.ni.clasificacion_pelicula.model.Clasificacion

class ListAdapter:RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var clasificacionList = emptyList<Clasificacion>()

    class ListViewHolder(val binding: ItemListBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = clasificacionList[position]

        holder.binding.txtAbreviacion.text = item.abreviacion.toString()
        holder.binding.txtClasificacion.text = item.n_clasificacion.toString()

        holder.binding.listLayout.setOnClickListener { view: View ->
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            findNavController(view).navigate(action)
        }
    }

    override fun getItemCount(): Int = clasificacionList.size

    fun setData(clasificacion: List<Clasificacion>){
        this.clasificacionList = clasificacion
        notifyDataSetChanged()
    }
}