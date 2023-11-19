package com.example.appprasdniki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView



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
        // setSupportActionBar(toolbar)

        // Установка информации о празднике в элементы макета
        holidayImage.setImageResource(holiday.imageResource)
        holidayName.text = holiday.name
        holidayDate.text = holiday.date
        holidayDescription.text = holiday.description

        // Показать кнопку возврата
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Действие, выполняемое при нажатии на кнопку возврата
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}