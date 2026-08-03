package com.example.medical_tab
import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medical_tab.api.RetrofitClient
import com.example.medical_tab.model.SectionLineModel
import com.example.medical_tab.repository.MedicalRepository
import com.example.medical_tab.ui.theme.EmployeeIDAppTheme
import com.example.medical_tab.ui.theme.PrimaryColor
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val repository = MedicalRepository(RetrofitClient.apiService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmployeeIDAppTheme {
                EmployeeIDApp(repository)
            }
        }
    }
}

@Composable
fun EmployeeIDApp(repository: MedicalRepository) {
    var idCardText by remember { mutableStateOf("") }
    var sectionLines by remember { mutableStateOf<List<SectionLineModel>>(emptyList()) }
    var selectedSectionName by remember { mutableStateOf<String?>(null) }
    var selectedLineId by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Fetch data from API (Live)
    LaunchedEffect(Unit) {
        repository.getSectionLines().onSuccess {
            sectionLines = it
        }.onFailure {
            scope.launch {
                snackbarHostState.showSnackbar("Failed to load lines: ${it.message}")
            }
        }
    }

    val sections = sectionLines.map { it.SectionName }.distinct()
    val filteredLines = sectionLines.filter { it.SectionName == selectedSectionName }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HeaderSection()

                IDCardInputSection(
                    idCardText = idCardText,
                    onIdCardChange = { idCardText = it }
                )

                if (sections.isNotEmpty()) {
                    SectionSelectionSection(
                        sections = sections,
                        selectedSection = selectedSectionName,
                        onSectionSelected = {
                            selectedSectionName = it
                            selectedLineId = null
                        }
                    )
                }

                if (selectedSectionName != null) {
                    LineSelectionSection(
                        sectionName = selectedSectionName!!,
                        lines = filteredLines,
                        selectedLineId = selectedLineId,
                        onLineSelected = { selectedLineId = it }
                    )
                }

                if (selectedLineId != null) {
                    SubmitButtonSection(
                        onSubmit = {
                            val section = selectedSectionName ?: "Unknown"
                            val line = selectedLineId ?: "Unknown"
                            val idCard = idCardText
                            scope.launch {
                                repository.submitMedicalInfo(idCard, line).onSuccess { isSuccess ->
                                    if (isSuccess) {
                                        snackbarHostState.showSnackbar(
                                            message = "Successfully submitted",
                                            duration = SnackbarDuration.Short
                                        )
                                    } else {
                                        snackbarHostState.showSnackbar(
                                            message = "Submission rejected by server. Please verify your information.",
                                            duration = SnackbarDuration.Long
                                        )
                                    }
                                }.onFailure {
                                    snackbarHostState.showSnackbar(
                                        message = "Failed to submit: ${it.message ?: "Unknown error"}",
                                        duration = SnackbarDuration.Long
                                    )
                                }
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryColor
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = "Medical Gate Pass & Appointment System",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

@Composable
fun IDCardInputSection(
    idCardText: String,
    onIdCardChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Enter ID Card Number",
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryColor,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = idCardText,
                onValueChange = { newValue ->
                    // Only allow digits 0-9
                    val filtered = newValue.filter { it.isDigit() }
                    onIdCardChange(filtered)
                },
                label = { Text("ID Card Number") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
fun SubmitButtonSection(
    onSubmit: () -> Unit
) {
    Button(
        onClick = onSubmit,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            "SUBMIT",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}
