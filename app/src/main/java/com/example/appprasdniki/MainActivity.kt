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
            Holiday("Новый год", "01.01.2023", "Кстати, для жителей Российских городов Новый год является главным праздником зимы и отмечается 1 января. Однако и среди городских жителей есть исключения, которые не празднуют Новый год. Настоящий праздник для верующего – это Рождество Христово. А перед ним строгий Рождественский пост, который длится 40 дней. Начинается он 28 ноября и заканчивается только 6 января, вечером, с восходом первой звезды. Есть даже деревни, селения, где все жители Новый год не празднуют или празднуют его 13 января ( 1 января по Юлианскому стилю), после поста и Рождества.\n" +
                    "\n" +
                    "А теперь вернемся к истории празднования Нового года на Руси\n" +
                    "\n" +
                    "Празднование нового года на Руси имеет такую же сложную судьбу, как и сама ее история. Прежде всего, все изменения в праздновании нового года были связаны с самыми важными историческими событиями, затрагивавшими все государство и каждого человека в отдельности. Несомненно, что народная традиция даже после официально вводимых изменений в календаре еще долго сохраняла древние обычаи.", R.drawable.new_year_image),
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