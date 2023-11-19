package com.example.appprasdniki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar


class HolidayDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.holiday_details)

        val holidayImage = findViewById<ImageView>(R.id.holiday_image)
        val holidayName = findViewById<TextView>(R.id.holiday_name)
        val holidayDate = findViewById<TextView>(R.id.holiday_date)
        val holidayDescription = findViewById<TextView>(R.id.holiday_description)

        // Получение данных о празднике, переданных из предыдущей активности
        val holiday = intent.getSerializableExtra("holiday") as Holiday
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        // setSupportActionBar(toolbar)

        // Установка информации о празднике в элементы макета
        holidayImage.setImageResource(holiday.imageResource)
        holidayName.text = holiday.name
        holidayDate.text = holiday.date
        holidayDescription.text = holiday.description

        // Показать кнопку возврата
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Добавить вашу собственную кнопку
        toolbar.findViewById<ImageView>(R.id.myCustomBackButton).setOnClickListener {
            // Действие, выполняемое при нажатии на кнопку
            finish() // закрыть текущую активность и вернуться на предыдущую
        }
    }
}