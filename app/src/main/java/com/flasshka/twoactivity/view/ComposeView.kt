package com.flasshka.twoactivity.view

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flasshka.twoactivity.R


object ComposeView {
    private val numberBorder = BorderStroke(8.dp, Brush.verticalGradient(listOf(Color.Blue, Color.Red)))
    private val numberShape = RoundedCornerShape(20.dp)
    private val numberHorizontalPadding = 25.dp
    private val numberFontSize = 180.sp
    private val fontFamily = FontFamily(Font(R.font.roboto_black))

    private val buttonBottomPadding = 10.dp
    private val buttonWidth = 160.dp
    private val buttonHeight = 60.dp
    private val buttonText = "change"
    private val buttonTextSize = 28.sp

    @Composable
    fun WriterNumber(number: Int, modifier: Modifier = Modifier) {
        Text(
            text = number.toString(),
            modifier = modifier
                .border(numberBorder, numberShape)
                .padding(horizontal = numberHorizontalPadding),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = numberFontSize,
            fontFamily = fontFamily
        )
    }

    @Composable
    fun <T : ComponentActivity> ButtonToActivity(
        currentActivity: ComponentActivity,
        nextActivity: Class<T>
    ) {
        Button(
            modifier = Modifier
                .padding(bottom = buttonBottomPadding)
                .size(buttonWidth, buttonHeight),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inversePrimary
            ),
            onClick = { toNextActivity(currentActivity, nextActivity) }
        ) {
            Text(
                text = buttonText,
                fontSize = buttonTextSize,
                fontFamily = fontFamily,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }

    private fun <T : ComponentActivity> toNextActivity(
        currentActivity: ComponentActivity,
        nextActivity: Class<T>
    ) {
        val myIntent = Intent(currentActivity, nextActivity)
        currentActivity.startActivity(myIntent)
        currentActivity.finish()
    }
}