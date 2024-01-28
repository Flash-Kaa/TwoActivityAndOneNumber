package com.flasshka.twoactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.flasshka.twoactivity.ui.theme.TwoActivityTheme
import com.flasshka.twoactivity.view.ComposeView
import com.flasshka.twoactivity.viewmodel.NumberSingletonViewModel
import timber.log.Timber

class SquareOfNumberActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("lifecycle: create square of number activity")

        val viewModel: NumberSingletonViewModel = NumberSingletonViewModel.factory()
        val thisActivity = this

        setContent {
            TwoActivityTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    ComposeView.WriterNumber(viewModel.getSquare())
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    ComposeView.ButtonToActivity(
                        currentActivity = thisActivity,
                        nextActivity = MainActivity::class.java
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.i("lifecycle: start square of number activity")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("lifecycle: resume square of number activity")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("lifecycle: restart square of number activity")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("lifecycle: pause square of number activity")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("lifecycle: stop square of number activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("lifecycle: destroy square of number activity")
    }
}