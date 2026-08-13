package com.example.medical_tab.ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.medical_tab.HeaderSection
import com.example.medical_tab.IDCardInputSection
import com.example.medical_tab.LineSelectionSection
import com.example.medical_tab.SectionSelectionSection
import com.example.medical_tab.SubmitButtonSection
import com.example.medical_tab.model.SectionLineModel
import com.example.medical_tab.repository.MedicalRepository
import kotlinx.coroutines.launch

@Composable
fun EmployeeIDApp(
    repository: MedicalRepository,
    onMenuClick: () -> Unit
) {
    var idCardText by remember { mutableStateOf("") }
    var sectionLines by remember { mutableStateOf<List<SectionLineModel>>(emptyList()) }
    var selectedSectionName by remember { mutableStateOf<String?>(null) }
    var selectedLineId by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val focusManager = LocalFocusManager.current

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

    val sections = sectionLines.asSequence().map { it.SectionName }.distinct().toList()
    val filteredLines = sectionLines.filter { it.SectionName == selectedSectionName }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color(0xFFF5F5F5),
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures {
                focusManager.clearFocus()
            }
        }
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
                HeaderSection(onMenuClick = onMenuClick)

                IDCardInputSection(
                    idCardText = idCardText
                )
                { idCardText = it }

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
                        isLoading = isLoading,
                        onSubmit = {
                            val line = selectedLineId ?: "Unknown"
                            val idCard = idCardText
                            if (idCard.isBlank()) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Please enter your ID card number",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                                return@SubmitButtonSection
                            }

                            scope.launch {
                                isLoading = true
                                repository.submitMedicalInfo(idCard, line).onSuccess { isSuccess ->
                                    isLoading = false
                                    if (isSuccess) {
                                        idCardText = ""
                                        selectedSectionName = null
                                        selectedLineId = null
                                        focusManager.clearFocus()
                                        // Show success toast
                                        snackbarHostState.showSnackbar(
                                            message = "Successfully submitted",
                                            duration = SnackbarDuration.Short
                                        )

                                        // Reset all data immediately


                                    } else {
                                        snackbarHostState.showSnackbar(
                                            message = "Submission rejected by server",
                                            duration = SnackbarDuration.Long
                                        )
                                    }
                                }.onFailure {
                                    isLoading = false
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