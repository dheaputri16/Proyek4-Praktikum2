package com.example.hanyarunrun.viewmodel

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.hanyarunrun.data.AppDatabase
import com.example.hanyarunrun.data.DataEntity
import com.example.hanyarunrun.data.DataRepository
import com.example.hanyarunrun.data.ProfileEntity
import com.example.hanyarunrun.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).dataDao()
    val dataList: LiveData<List<DataEntity>> = dao.getAll()
    private val profileDao = AppDatabase.getDatabase(application).profileDao()
    val profile: LiveData<ProfileEntity?> = profileDao.getProfile()
    private val repository = DataRepository(application)

    fun fetchDataFromApi() {
        viewModelScope.launch {
            repository.fetchAndSaveData()
        }
    }
    fun updateProfile(name: String, id: String, email: String) {
        viewModelScope.launch {
            val newProfile = ProfileEntity(
                studentName = name,
                studentId = id,
                studentEmail = email
            )
            profileDao.insertOrUpdate(newProfile)
        }
    }

    fun insertData(
        kodeProvinsi: String,
        namaProvinsi: String,
        kodeKabupatenKota: String,
        namaKabupatenKota: String,
        total: String,
        satuan: String,
        tahun: String
    ) {
        viewModelScope.launch {
            val totalValue = total.toDoubleOrNull() ?: 0.0
            val tahunValue = tahun.toIntOrNull() ?: 0
            dao.insert(
                DataEntity(
                    kodeProvinsi = kodeProvinsi,
                    namaProvinsi = namaProvinsi,
                    kodeKabupatenKota = kodeKabupatenKota,
                    namaKabupatenKota = namaKabupatenKota,
                    total = totalValue,
                    satuan = satuan,
                    tahun = tahunValue
                )
            )
        }
    }

    fun updateData(data: DataEntity) {
        viewModelScope.launch {
            dao.update(data)
        }
    }

    fun deleteData(data: DataEntity) {
        viewModelScope.launch {
            dao.delete(data)
        }
    }

    suspend fun getDataById(id: Int): DataEntity? {
        return withContext(Dispatchers.IO) {
            dao.getById(id)
        }
    }
}

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    val profile: LiveData<ProfileEntity?> = repository.getProfile()

    fun updateProfile(studentName: String, studentId: String, studentEmail: String) {
        viewModelScope.launch {
            repository.updateProfile(studentName, studentId, studentEmail)
        }
    }
}

class ProfileViewModelFactory(private val repository: ProfileRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class DataViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DataViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}





