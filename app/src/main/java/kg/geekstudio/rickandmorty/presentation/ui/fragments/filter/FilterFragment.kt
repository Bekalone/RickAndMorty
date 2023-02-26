package kg.geekstudio.rickandmorty.presentation.ui.fragments.filter

import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kg.geekstudio.data.providers.PreferencesButton
import kg.geekstudio.rickandmorty.R
import kg.geekstudio.rickandmorty.databinding.FragmentFilterBinding
import kg.geekstudio.rickandmorty.presentation.base.BaseFragmentWithoutViewModel
import kg.geekstudio.rickandmorty.presentation.extensions.fragmentResult
import kg.geekstudio.rickandmorty.presentation.extensions.setOnCheckedChangeListenerReturnsTheTextOfTheRadioButton
import kg.geekstudio.rickandmorty.presentation.model.CharacterFilter
import org.koin.android.ext.android.inject


class FilterFragment : BaseFragmentWithoutViewModel<FragmentFilterBinding>(R.layout.fragment_filter) {

    override val binding by viewBinding(FragmentFilterBinding::bind)
    private val sharedPreferences by inject<PreferencesButton>()
    private val characterFilter = CharacterFilter()


    override fun setupListeners() = with(binding) {

        loadCheckedRadioButton(
            radioBtnAlive, radioBtnDead, radioBtnUnknownStatus,
            radioBtnHuman, radioBtnHumanoid, radioBtnAlien,
            radioBtnMale, radioBtnFemale, radioBtnUnknownGender
        )

        radioGroupStatus.setOnCheckedChangeListenerReturnsTheTextOfTheRadioButton(radioBtnAlive,
            radioBtnDead,
            radioBtnUnknownStatus) { text ->
            characterFilter.status = text
            sharedPreferences.checkedRadioButtonStatus = text
        }

        radioGroupSpecies.setOnCheckedChangeListenerReturnsTheTextOfTheRadioButton(radioBtnHuman,
            radioBtnHumanoid,
            radioBtnAlien) { text ->
            characterFilter.species = text
            sharedPreferences.checkedRadioButtonSpecies = text
        }

        radioGroupGender.setOnCheckedChangeListenerReturnsTheTextOfTheRadioButton(radioBtnMale,
            radioBtnFemale,
            radioBtnUnknownGender) { text ->
            characterFilter.gender = text
            sharedPreferences.checkedRadioButtonGender = text
        }

        btnOk.setOnClickListener {
            fragmentResult("filter", characterFilter)
            findNavController().popBackStack()
        }
        btnReset.setOnClickListener {
            sharedPreferences.resetFilter()
            fragmentResult("filter", CharacterFilter(null, null, null))
            findNavController().popBackStack()
        }
    }

    private fun loadCheckedRadioButton(vararg radioButton: RadioButton) {
        sharedPreferences.checkRadioButton.map { radioButtonText ->
            for (button in radioButton)
                if (button.text == radioButtonText) button.isChecked = true
        }
    }
}