package kg.geekstudio.data.providers

import android.content.SharedPreferences
import kg.geekstudio.data.providers.PrefsKeys.SAVE_GENDER_BTN
import kg.geekstudio.data.providers.PrefsKeys.SAVE_SPECIES_BTN
import kg.geekstudio.data.providers.PrefsKeys.SAVE_STATUS_BTN

class PreferencesButton(private val sharedPreferences: SharedPreferences) {

    val checkRadioButton =
        arrayListOf(checkedRadioButtonStatus, checkedRadioButtonSpecies, checkedRadioButtonGender)

    var checkedRadioButtonStatus: String?
        get() = sharedPreferences.getString(SAVE_STATUS_BTN, "")
        set(value) = sharedPreferences.edit().putString(SAVE_STATUS_BTN, value).apply()

    var checkedRadioButtonSpecies: String?
        get() = sharedPreferences.getString(SAVE_SPECIES_BTN, "")
        set(value) = sharedPreferences.edit().putString(SAVE_SPECIES_BTN, value).apply()

    var checkedRadioButtonGender: String?
        get() = sharedPreferences.getString(SAVE_GENDER_BTN, "")
        set(value) = sharedPreferences.edit().putString(SAVE_GENDER_BTN, value).apply()

    fun resetFilter() {
        checkedRadioButtonStatus = ""
        checkedRadioButtonSpecies = ""
        checkedRadioButtonGender = ""
    }

}
