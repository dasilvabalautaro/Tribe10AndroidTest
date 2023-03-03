package com.globalhiddenodds.tribe10androidtest.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.globalhiddenodds.tribe10androidtest.ENTRY_SCREEN
import com.globalhiddenodds.tribe10androidtest.SPLASH_SCREEN
import com.globalhiddenodds.tribe10androidtest.data.Person
import com.globalhiddenodds.tribe10androidtest.data.PersonRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    private val personRepository: PersonRepository
): BaseViewModel() {
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {
            if (personRepository.getCount() > 0) openAndPopUp(ENTRY_SCREEN, SPLASH_SCREEN)
            else createAnonymousAccount(openAndPopUp)
        }
    }

    private suspend fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        personRepository.insertPerson(Person(id = 0, name = "Anonymous"))
        openAndPopUp(ENTRY_SCREEN, SPLASH_SCREEN)
    }
}