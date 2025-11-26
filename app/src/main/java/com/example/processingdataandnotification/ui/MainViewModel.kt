package com.example.processingdataandnotification.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.processingdataandnotification.data.Course
import com.example.processingdataandnotification.data.CourseRepository
import com.example.processingdataandnotification.util.NotificationHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class UiState(
    val isLoading: Boolean = false,
    val courses: List<Course> = emptyList(),
    val errorMessage: String? = null
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CourseRepository(application)

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun loadCourses() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)

            try {
                // Jalankan di background
                val data = withContext(Dispatchers.IO) {
                    delay(2000)
                    repository.loadCoursesFromAssets()
                }

                _uiState.value = UiState(
                    isLoading = false,
                    courses = data
                )

                //  tampilkan notifikasi
                NotificationHelper.showNotification(
                    context = getApplication(),
                    title = "Proses selesai",
                    message = "Berhasil memuat ${data.size} mata kuliah dari JSON"
                )

            } catch (e: Exception) {
                _uiState.value = UiState(
                    isLoading = false,
                    errorMessage = e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
}
