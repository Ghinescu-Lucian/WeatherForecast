//package com.example.weatherapp.ui.profile.biometric
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.core.content.ContextCompat
//import androidx.biometric.BiometricPrompt
//
//import androidx.compose.runtime.*
//
//@RequiresApi(Build.VERSION_CODES.P)
//@Composable
//fun BiometricAuthScreen() {
//    var isBiometricPromptVisible by remember { mutableStateOf(false) }
//
//    Column(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Button(
//            onClick = { isBiometricPromptVisible = true },
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Text("Authenticate with Biometric")
//        }
//
//        if (isBiometricPromptVisible) {
//            BiometricAuthentication(
//                onDismiss = { isBiometricPromptVisible = false }
//            )
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.P)
//@Composable
//fun BiometricAuthentication(onDismiss: () -> Unit) {
//    val context = LocalContext.current
//
//    DisposableEffect(Unit) {
//        val promptInfo = BiometricPrompt.PromptInfo.Builder()
//            .setTitle("Biometric Authentication")
//            .setSubtitle("Authenticate using your fingerprint, face, or iris.")
//            .setConfirmationRequired(false)
//            .setNegativeButtonText("Cancel")
//            .build()
//
//        val biometricPrompt = BiometricPrompt(
//            context,
//            ContextCompat.getMainExecutor(context),
//            object : BiometricPrompt.AuthenticationCallback() {
//                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                    super.onAuthenticationSucceeded(result)
//                    // Biometric authentication succeeded, you can proceed.
//                    onDismiss()
//                }
//
//                override fun onAuthenticationFailed() {
//                    super.onAuthenticationFailed()
//                    // Biometric authentication failed.
//                }
//            }
//        )
//
//        biometricPrompt.authenticate(promptInfo)
//        onDispose {
//            biometricPrompt.cancelAuthentication()
//        }
//    }
//}