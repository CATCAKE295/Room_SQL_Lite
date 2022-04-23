package edu.uca.ni.clasificacion_pelicula.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import edu.uca.ni.clasificacion_pelicula.data.ClasificacionDatabase
import edu.uca.ni.clasificacion_pelicula.model.Clasificacion
import edu.uca.ni.clasificacion_pelicula.repository.ClasificacionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClasificacionViewModel(application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<Clasificacion>>
    private val repository: ClasificacionRepository

    init {
        val clasificacionDao = ClasificacionDatabase.getDataBase(application).clasificacionDao()
        repository = ClasificacionRepository(clasificacionDao)
        readAllData = repository.readAllData
    }

    fun addClasificacion(clasificacion: Clasificacion){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClasificacion(clasificacion)
        }
    }

    fun updateClasificacion(clasificacion: Clasificacion){
        viewModelScope.launch(Dispatchers.IO) {

            repository.updateClasificacion(clasificacion)

        }
    }

    fun deleteClasificacion(clasificacion: Clasificacion){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteClasificacion(clasificacion)
        }
    }

    fun  deleteAllClasificacion(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllClasificacion()
        }
    }
}