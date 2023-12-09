package com.example.appprasdniki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


class HolidayDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.holiday_details)


        val holidayImage = findViewById<ImageView>(R.id.holiday_image)
        val holidayName = findViewById<TextView>(R.id.holiday_name)
        val holidayDate = findViewById<TextView>(R.id.holiday_date)
        val holidayDescription = findViewById<TextView>(R.id.holiday_description)

        // Получение данных о празднике, переданных из предыдущей активности
        val name = intent.getStringExtra("holidayName")
        val date = intent.getStringExtra("holidayDate")
        val description = intent.getStringExtra("holidayDescription")
        val imageResource = intent.getIntExtra("holidayImage", 0)

        if (name != null && date != null && description != null) {
            // Установка информации о празднике в элементы макета
            holidayImage.setImageResource(imageResource)
            holidayName.text = name
            holidayDate.text = date
            holidayDescription.text = description

            // Показать кнопку возврата
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            // Если holiday == null, обработайте ситуацию, когда данные отсутствуют
            Toast.makeText(this, "Error: Holiday data is missing", Toast.LENGTH_SHORT).show()
            finish()
        }
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
