package com.example.medical_tab
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medical_tab.api.RetrofitClient
import com.example.medical_tab.repository.MedicalRepository
import com.example.medical_tab.ui.AppNavigation
import com.example.medical_tab.ui.theme.EmployeeIDAppTheme
import com.example.medical_tab.ui.theme.PrimaryColor

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT,
            )
        )
        val repository = MedicalRepository(RetrofitClient.apiService)
        setContent {
            EmployeeIDAppTheme {
                AppNavigation(repository)
            }
        }
    }
}





@Composable
fun SubmitButtonSection(
    isLoading: Boolean = false,
    enabled: Boolean = true,
    onSubmit: () -> Unit
) {
    Button(
        onClick = onSubmit,
        enabled = enabled && !isLoading,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFF4CAF50).copy(alpha = 0.6f),
            disabledContentColor = Color.White.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                "SUBMIT",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}


@Composable
fun HeaderSection(onMenuClick: () -> Unit) {


    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryColor
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Y-Care",
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                ),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IDCardInputSection(
    idCardText: String,
    selectedPriority: String,
    modifier: Modifier = Modifier,
    onIdCardChange: (String) -> Unit,
    onPriorityChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val priorities = listOf("High", "Medium", "Low")

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.Top
            ) {
                // ID Card Input
                Column(modifier = Modifier.weight(3.5f).fillMaxHeight()) {
                    Text(
                        text = "Enter ID Card Number",
                        style = MaterialTheme.typography.titleMedium,
                        color = PrimaryColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = idCardText,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        onValueChange = { newValue ->
                            val filtered = newValue.filter { it.isDigit() }
                            onIdCardChange(filtered)
                        },
                        label = {
                            Text("ID Card Number", fontSize = 16.sp)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
                // Priority Dropdown
                Column(modifier = Modifier.weight(1.5f).fillMaxHeight()) {
                    Text(
                        text = "Priority",
                        style = MaterialTheme.typography.titleMedium,
                        color = PrimaryColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.fillMaxSize()
                    ) {
                        OutlinedTextField(
                            value = selectedPriority,
                            onValueChange = {},
                            readOnly = true,
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            ),

                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            shape = RoundedCornerShape(8.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryColor,
                                unfocusedBorderColor = Color.Gray,
                                focusedLabelColor = Color.Black,
                                unfocusedLabelColor = Color.Black
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            priorities.forEach { priority ->
                                DropdownMenuItem(
                                    text = { 
                                        Text(
                                            text = priority,
                                            color = Color.Black,
                                            fontWeight = FontWeight.Bold
                                        ) 
                                    },
                                    onClick = {
                                        onPriorityChange(priority)
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

