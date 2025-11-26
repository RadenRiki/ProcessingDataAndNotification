package com.example.processingdataandnotification.data

import android.content.Context
import org.json.JSONArray

class CourseRepository(private val context: Context) {

    fun loadCoursesFromAssets(): List<Course> {
        // Baca file JSON
        val jsonString = context.assets.open("courses.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonArray = JSONArray(jsonString)
        val list = mutableListOf<Course>()

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val title = obj.getString("title")
            val lecturer = obj.getString("lecturer")
            list.add(Course(title, lecturer))
        }

        return list
    }
}
