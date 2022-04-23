package edu.uca.ni.clasificacion_pelicula.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.uca.ni.clasificacion_pelicula.R
import edu.uca.ni.clasificacion_pelicula.databinding.FragmentAddBinding
import edu.uca.ni.clasificacion_pelicula.model.Clasificacion
import edu.uca.ni.clasificacion_pelicula.viewmodel.ClasificacionViewModel


class AddFragment : Fragment() {

    private lateinit var mclasificacionViewModel: ClasificacionViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mclasificacionViewModel = ViewModelProvider(this).get(ClasificacionViewModel::class.java)

        binding.btnGuardar.setOnClickListener {
            insertToDataBase()
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun insertToDataBase() {
       val abreviacion = binding.etAbreviacion.text.toString()
        val clasificacion = binding.etClasificacion.text.toString()

        if (inputCheck(abreviacion,clasificacion)){

            val clasificacion = Clasificacion(0,clasificacion,abreviacion)

            mclasificacionViewModel.addClasificacion(clasificacion)

            Toast.makeText(requireContext(),"Agregado Correctamente",Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        } else{

            Toast.makeText(requireContext(),"LLene todos los campos",Toast.LENGTH_LONG).show()
        }
    }

    private fun  inputCheck(abreviacion: String, clasificacion:String): Boolean{

        return !(TextUtils.isEmpty(abreviacion) && TextUtils.isEmpty(clasificacion))

    }


}