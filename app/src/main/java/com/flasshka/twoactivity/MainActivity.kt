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
import com.flasshka.twoactivity.model.FileLoggingTree
import com.flasshka.twoactivity.model.PermissionUtils
import com.flasshka.twoactivity.ui.theme.TwoActivityTheme
import com.flasshka.twoactivity.view.ComposeView
import com.flasshka.twoactivity.viewmodel.NumberSingletonViewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private val keyNameForSavedInstance = "need_add_one"
    private val storageRequestCode = 101

    private val viewModel: NumberSingletonViewModel by lazy {
        NumberSingletonViewModel.factory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PermissionUtils.hasPermissions(this)) {
            PermissionUtils.requestPermissions(this, storageRequestCode)
        }

        if(Timber.forest().filterIsInstance<FileLoggingTree>().isEmpty()) {
            Timber.plant(FileLoggingTree(this))
        }

        Timber.i("lifecycle: create")
        addOneIfNeed(savedInstanceState)
        val thisActivity = this

        setContent {
            TwoActivityTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.Center
                ) {
                    ComposeView.WriterNumber(viewModel.getNumber())
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    ComposeView.ButtonToActivity(
                        currentActivity = thisActivity,
                        nextActivity = SquareOfNumberActivity::class.java
                    )
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(keyNameForSavedInstance, true)
    }

    private fun addOneIfNeed(savedInstanceState: Bundle?) {
        savedInstanceState?.let { bundle ->
            if(bundle.getBoolean(keyNameForSavedInstance)) {
                viewModel.addOne()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Timber.i("lifecycle: start")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("lifecycle: resume")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("lifecycle: restart")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("lifecycle: pause")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("lifecycle: stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("lifecycle: destroy")
    }
}