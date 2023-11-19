package com.example.appprasdniki

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HolidayAdapter(context: Context, holidays: List<Holiday>) : ArrayAdapter<Holiday>(context, 0, holidays) {

    private class ViewHolder {
        lateinit var holidayNameTextView: TextView
        lateinit var holidayDateTextView: TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView // Используйте переменную для представления элемента списка
        var viewHolder: ViewHolder? = null

        if (listItemView == null) {
            // Если представление не существует, создайте новое и инициализируйте ViewHolder
            listItemView = LayoutInflater.from(context).inflate(R.layout.holiday_item, parent, false)

            viewHolder = ViewHolder()
            viewHolder.holidayNameTextView = listItemView.findViewById(R.id.holiday_name)
            viewHolder.holidayDateTextView = listItemView.findViewById(R.id.holiday_date)

            // Сохраняем ViewHolder в качестве тега представления
            listItemView.tag = viewHolder
        } else {
            // Если представление уже существует, используем сохраненный ViewHolder
            viewHolder = listItemView.tag as? ViewHolder
        }

        // Получаем данные о празднике для текущей позиции
        val currentHoliday = getItem(position)

        // Устанавливаем значения для отображения в соответствующие элементы представления
        viewHolder?.holidayNameTextView?.text = currentHoliday?.name ?: ""
        viewHolder?.holidayDateTextView?.text = currentHoliday?.date ?: ""

        // Возвращаем заполненное представление элемента списка
        return listItemView!!
    }
}



