package com.example.bookshelf.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

// Define your custom colors. In a full M3 app, these would be part of your ColorScheme.
val MyGreen = Color(0xFF32CD32) // Example: A shade of lime green
val MyWhite = Color(0xFFFFFFFF) // White for the background of the text field
val MyDarkBackground = Color(0xFF000000) // Your app's dark background color (for context)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppM3TextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String, // Label that appears inside the text field
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null // Optional error message to display below the field
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = MyGreen) }, // Label text color
        trailingIcon = trailingIcon,
        modifier = modifier
            .fillMaxWidth() // Makes the text field take full width
            .height(56.dp) // Fixed height, adjust as necessary
            // This background modifier applies to the whole TextField container
            .background(color = MyWhite, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp), // Shape for the border/outline of the TextField
        colors = TextFieldDefaults.colors( // Corrected: Explicitly name arguments
            // Make all container colors transparent since we're using a background modifier
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent, // Also set error container to transparent

            // Remove the default M3 indicator lines (for Filled TextField variant)
            focusedIndicatorColor = MyGreen,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = MaterialTheme.colorScheme.error,

            // Input text and label colors
            focusedTextColor = MyGreen,
            unfocusedTextColor = MyGreen,
            disabledTextColor = MyGreen.copy(alpha = 0.6f),
            errorTextColor = MaterialTheme.colorScheme.error,

            focusedLabelColor = MyGreen,
            unfocusedLabelColor = MyGreen,
            disabledLabelColor = MyGreen.copy(alpha = 0.6f),
            errorLabelColor = MaterialTheme.colorScheme.error,

            cursorColor = MyGreen,
            errorCursorColor = MaterialTheme.colorScheme.error,

            // If you had leading/trailing icons or placeholder, you'd set their colors here too:
            focusedTrailingIconColor = MyGreen,
            unfocusedTrailingIconColor = MyGreen,
            focusedPlaceholderColor = MyGreen.copy(alpha = 0.6f)
        ),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        singleLine = true,
        isError = isError,
        // Optional: placeholder
        // placeholder = { Text("Enter text here", color = MyGreen.copy(alpha = 0.6f)) }
    )

    // Display error message if isError is true and errorMessage is provided
    if (isError && errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}