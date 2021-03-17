package com.example.wnews.login

import android.content.SharedPreferences
import com.example.wnews.views.auth.AuthPresenter
import com.example.wnews.views.auth.AuthView
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestLoginPresenter {

    @Test
    fun testValidateUserMail(){
        val mockAuthView = mock(AuthView::class.java)
        val mockSharedPreferences = mock(SharedPreferences::class.java)

        val correctMail = "example@wolox.com"
        assertTrue(AuthPresenter(mockSharedPreferences, mockAuthView).validateUserMail(correctMail))

        val incorrectMail = "example@wolox"
        assertFalse(AuthPresenter(mockSharedPreferences, mockAuthView).validateUserMail(incorrectMail))


    }

    @Test
    fun testEmptyUserMail(){
        val mockAuthView = mock(AuthView::class.java)
        val mockSharedPreferences = mock(SharedPreferences::class.java)

        val emptyMail = ""
        assertTrue(AuthPresenter(mockSharedPreferences, mockAuthView).isUserMailEmpty(emptyMail))

        val noEmptyMail = "example@wolox.com"
        assertFalse(AuthPresenter(mockSharedPreferences, mockAuthView).isUserMailEmpty(noEmptyMail))

    }


    @Test
    fun testEmptyUserPassword(){
        val mockAuthView = mock(AuthView::class.java)
        val mockSharedPreferences = mock(SharedPreferences::class.java)

        val emptyPassword = ""
        assertTrue(AuthPresenter(mockSharedPreferences, mockAuthView).isUserPasswordEmpty(emptyPassword))

        val noEmptyPassword = "123456"
        assertFalse(AuthPresenter(mockSharedPreferences, mockAuthView).isUserPasswordEmpty(noEmptyPassword))

    }
}