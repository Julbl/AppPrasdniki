package com.example.appprasdniki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.content.Intent
import com.example.appprasdniki.HolidayAdapter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.holiday_list)
        val sectionHeader = findViewById<TextView>(R.id.section_header)

        // Предположим, у вас есть список праздников и соответствующий адаптер
        val holidayList: List<Holiday> = listOf(
            Holiday("New Year", "01.01.2023", "Description", R.drawable.new_year_image),
            Holiday("Independence Day", "04.07.2023", "Description", R.drawable.independence_day_image),
        )

        val holidayAdapter = HolidayAdapter(this, holidayList)


        listView.adapter = holidayAdapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedHoliday = holidayList[position] // Получение выбранного праздника из списка
            val intent = Intent(this, HolidayDetailsActivity::class.java)
            intent.putExtra("holiday", selectedHoliday) // Передача информации о выбранном празднике в новую активность
            startActivity(intent)
        }
    }

}