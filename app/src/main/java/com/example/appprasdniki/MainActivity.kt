package com.example.appprasdniki
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.preference.ListPreference
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity() {
    private lateinit var linearLayout: LinearLayout
    private lateinit var linearLayoutRoot: LinearLayout
    private var isDark: Boolean = false
    private lateinit var holidayList: List<Holiday>
    @SuppressLint("StringFormatInvalid", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkDarkMode()

        linearLayout = findViewById(R.id.linearLayout)
        //val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val holidayList: List<Holiday> = listOf(
            Holiday(
                getString(R.string.holiday_new_year),
                "01.01.2023",
                getString(R.string.holiday_description_new_year),
                R.drawable.new_year_image
            ),
            Holiday(
                getString(R.string.holiday_independence_day),
               "04.07.2023",
                getString(R.string.holiday_description_independence_day),
                R.drawable.independence_day_image
            ),
            Holiday(
                getString(R.string.holiday_valentines_day),
                "14.03.2023",
                getString(R.string.holiday_description_valentines_day),
                R.drawable.dayofsvval
            ),
            Holiday(
                getString(R.string.holiday_chusok),
                "29.09.2023",
                getString(R.string.holiday_description_chusok),
                R.drawable.chuseok
            ),
            Holiday(
                getString(R.string.holiday_march_8th),
                "08.03.2024",
                getString(R.string.holiday_description_march_8th),
                R.drawable.eightmarta
            ),
            Holiday(
                getString(R.string.holiday_easter),
                "16.04.2023",
                getString(R.string.holiday_description_easter),
                R.drawable.pasha
            ),
            Holiday(
                getString(R.string.holiday_space_day),
                "12.04.2023",
                getString(R.string.holiday_description_space_day),
                R.drawable.kosmos
            ),
            Holiday(
                getString(R.string.holiday_science_fiction_day),
                "02.01.2024",
                getString(R.string.holiday_description_science_fiction_day),
                R.drawable.science
            ),
            Holiday(
                getString(R.string.holiday_day_of_love),
                "08.07.2024",
                getString(R.string.holiday_description_day_of_love),
                R.drawable.love
            ),
            Holiday(
                getString(R.string.holiday_mothers_day),
                "26.11.2023",
                getString(R.string.holiday_description_mothers_day),
                R.drawable.mother
            ),
            Holiday(
                getString(R.string.holiday_fathers_day),
                "15.10.2023",
                getString(R.string.holiday_description_fathers_day),
                R.drawable.father
            ),
        )

        for (holiday in holidayList) {
            addHolidayToLayout(holiday)
        }

    }

    private fun checkDarkMode() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        isDark = sharedPreferences.getBoolean("dark_mode", false)
        if (isDark) {
            findViewById<LinearLayout>(R.id.linearLayoutRoot).setBackgroundColor(Color.parseColor("#000000"))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu) // Разметка меню в res/menu/main_menu.xml
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }
            // Другие пункты меню, если есть
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun addHolidayToLayout(holiday: Holiday) {
        val inflater = LayoutInflater.from(this)
        val holidayView = inflater.inflate(R.layout.activity_main, linearLayout, false)

        val holidayNameTextView: TextView = holidayView.findViewById(R.id.holiday_name)
        val holidayDateTextView: TextView = holidayView.findViewById(R.id.holiday_date)

        // Установка данных праздника в TextView
        holidayNameTextView.text = holiday.name
        holidayDateTextView.text = holiday.date.toString()

        //Log.i("MainActivity", "Added holiday to layout: ${holiday.name}")

        // Установка слушателя кликов для элемента праздника
        holidayView.setOnClickListener {
            // Обработка нажатия на элемент списка
            //Log.i("MainActivity", "Clicked on Holiday: ${holiday.name}")


            // Создание интента для запуска новой активности
            val intent = Intent(this, HolidayDetailsActivity::class.java).apply {
                putExtra("holidayName", holiday.name)
                putExtra("holidayDate", holiday.date)
                putExtra("holidayDescription", holiday.description)
                putExtra("holidayImage", holiday.imageResource)
            }
            // Выводим информацию в логи для проверки
            // Log.i("MainActivity", "Holiday Name: ${holiday.name}")
            //Log.i("MainActivity", "Holiday Date: ${holiday.date}")
            //Log.i("MainActivity", "Holiday Description: ${holiday.description}")
            //Log.i("MainActivity", "Holiday Image Resource: ${holiday.imageResource}")

            // Запуск новой активности
            startActivity(intent)
        }

        // Добавление представления праздника в линейный макет
        linearLayout.addView(holidayView)

    }

/*    fun getHolidayList(): List<Holiday> {
        return holidayList
    }*/




}




