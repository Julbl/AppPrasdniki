package com.example.appprasdniki

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import java.util.Locale
import java.text.SimpleDateFormat
import java.util.Date

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun applyTheme() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isDarkModeEnabled = sharedPreferences.getBoolean("dark_mode", false)

        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        private var holidayList: List<Holiday> = emptyList()
        interface SortOrderChangeListener {
            fun onSortOrderChanged(sortedList: List<Holiday>)
        }
        fun onSortOrderChanged(sortedList: List<Holiday>) {
            holidayList = sortedList
            // Вызовите метод обновления интерфейса, если это необходимо
        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            val switchPreference =
                findPreference<SwitchPreferenceCompat>("dark_mode")
            switchPreference?.setOnPreferenceChangeListener { _, newValue ->
                // Обработка изменения состояния SwitchPreference
                val isDarkModeEnabled = newValue as Boolean

                if (isDarkModeEnabled) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }

                // Перезапускаем SettingsActivity и очищаем стек активностей
                val intent = Intent(requireContext(), SettingsActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                requireActivity().finish()

                true
            }
            val languageListPreference = findPreference<ListPreference>("language")

            languageListPreference?.setOnPreferenceChangeListener { _, newValue ->
                val selectedLanguage = newValue as String
                setLocale(selectedLanguage)
                restartActivity()
                true
            }
            /*val sortPreference = findPreference<ListPreference>("sort_order")
            sortPreference?.setOnPreferenceChangeListener { _, newValue ->
                // Пользователь изменил настройку сортировки
                applySortOrder()
                true
            }*/
        }
/*        private fun applySortOrder() {
            val context = requireContext()
            val sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(context)
            val sortOrder = sharedPreferences.getString("sort_order", "default_sort_order") ?: "default_sort_order"
            val mainActivity = activity as? MainActivity // Try to cast the activity to MainActivity

            var unsortedList = mainActivity?.getHolidayList() ?: emptyList() // Use safe call operator to avoid NPE

            // Обработка изменения порядка сортировки
            when (sortOrder) {
                "date_asc" -> {
                    val sortedList = unsortedList.sortedBy { it.date }
                    mainActivity?.onSortOrderChanged(sortedList) // Uncomment this line
                }
                "date_desc" -> {
                    val sortedList = unsortedList.sortedByDescending { it.date }
                    mainActivity?.onSortOrderChanged(sortedList) // Uncomment this line
                }
                else -> {
                    // По умолчанию не требуется дополнительной сортировки
                }
            }
        }*/
        private fun restartActivity() {
            activity?.let {
                it.finish()
                it.startActivity(it.intent)
            }
        }
        private fun setLocale(languageCode: String) {
            val locale = Locale(languageCode)
            Locale.setDefault(locale)
            val configuration = Configuration()
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)

            val displayMetrics = resources.displayMetrics
            resources.updateConfiguration(configuration, displayMetrics)

            // Обновляем результат активити и закрываем фрагмент
            val resultIntent = Intent()
            requireActivity().setResult(Activity.RESULT_OK, resultIntent)
            requireActivity().finish()
        }

    }
}