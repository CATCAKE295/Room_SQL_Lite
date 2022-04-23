package edu.uca.ni.clasificacion_pelicula.repository

import androidx.lifecycle.LiveData
import edu.uca.ni.clasificacion_pelicula.data.ClasificacionDao
import edu.uca.ni.clasificacion_pelicula.model.Clasificacion

class ClasificacionRepository(private val clasificacionDao: ClasificacionDao) {


    val readAllData: LiveData<List<Clasificacion>> = clasificacionDao.readAllData()

    suspend fun addClasificacion(clasificacion: Clasificacion){
        clasificacionDao.addClasificacion(clasificacion)
    }

    suspend fun  updateClasificacion(clasificacion: Clasificacion){
        clasificacionDao.updateClasificacion(clasificacion)
    }

    suspend fun deleteClasificacion(clasificacion: Clasificacion){
        clasificacionDao.deleteClasificacion(clasificacion)

    }

    suspend fun deleteAllClasificacion(){
        clasificacionDao.deleteAllClasificacion()
    }
}