package data.repository

import data.api.MonETSApi
import data.securepreferences.SecurePreferences
import di.Inject

class LoginRepository @Inject constructor(
    private val securePrefs: SecurePreferences,
    private val monETSApi: MonETSApi
) {

}