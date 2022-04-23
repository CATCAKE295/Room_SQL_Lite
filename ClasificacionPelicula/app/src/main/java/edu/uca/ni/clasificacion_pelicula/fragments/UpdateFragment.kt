package edu.uca.ni.clasificacion_pelicula.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.uca.ni.clasificacion_pelicula.R
import edu.uca.ni.clasificacion_pelicula.databinding.FragmentUpdateBinding
import edu.uca.ni.clasificacion_pelicula.model.Clasificacion
import edu.uca.ni.clasificacion_pelicula.viewmodel.ClasificacionViewModel


class UpdateFragment : Fragment() {

    private lateinit var mclasificacionViewModel: ClasificacionViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mclasificacionViewModel = ViewModelProvider(this).get(ClasificacionViewModel::class.java)

        binding.etAbreviacionupdate.setText(args.currentClasificacion.abreviacion)
        binding.etClasificacionupdate.setText(args.currentClasificacion.n_clasificacion)

        binding.btnActualizar.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun updateItem() {
        val uAbreviacion = binding.etAbreviacionupdate.text.toString()
        val uClasificacion = binding.etClasificacionupdate.text.toString()
        if (inputCheck(uAbreviacion,uClasificacion)){

            val upClasificacion = Clasificacion(args.currentClasificacion.id,uClasificacion,uAbreviacion)

            mclasificacionViewModel.updateClasificacion(upClasificacion)

            Toast.makeText(requireContext(),"Actualizado Correctamente", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)


        }else{

            Toast.makeText(requireContext(),"LLene todos los campos", Toast.LENGTH_LONG).show()

        }

    }

    private fun inputCheck(uAbreviacionn: String,uClasificacion: String ): Boolean {

        return  !(TextUtils.isEmpty(uAbreviacionn) && TextUtils.isEmpty(uClasificacion))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_icon){
            deleteClasificacion()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteClasificacion() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mclasificacionViewModel.deleteClasificacion(args.currentClasificacion)

            Toast.makeText(requireContext(),"Borrado con exito la clasificacion ${args.currentClasificacion.n_clasificacion}", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
        builder.setNegativeButton("No"){_,_->

        }
        builder.setTitle("Borrar ${args.currentClasificacion.n_clasificacion}?")
        builder.setMessage("Estas seguro de querer borrar ${args.currentClasificacion.n_clasificacion}?")
        builder.create().show()
    }

}