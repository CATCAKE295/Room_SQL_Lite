package edu.uca.ni.clasificacion_pelicula.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uca.ni.clasificacion_pelicula.R

import edu.uca.ni.clasificacion_pelicula.databinding.FragmentListBinding
import edu.uca.ni.clasificacion_pelicula.adapter.ListAdapter
import edu.uca.ni.clasificacion_pelicula.viewmodel.ClasificacionViewModel


class ListFragment : Fragment() {

    private lateinit var mclasificacionViewModel: ClasificacionViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mclasificacionViewModel = ViewModelProvider(this).get(ClasificacionViewModel::class.java)
        mclasificacionViewModel.readAllData.observe(viewLifecycleOwner, Observer { clasificacion ->
            adapter.setData(clasificacion)
        })

        setHasOptionsMenu(true)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_icon){
            deleteAllClasificacion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllClasificacion() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mclasificacionViewModel.deleteAllClasificacion()

            Toast.makeText(requireContext(),"Todo ha sido Borrado con exito", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Borrar Todo ?")
        builder.setMessage("Estas seguro de querer borrar todo ?")
        builder.create().show()
    }
}