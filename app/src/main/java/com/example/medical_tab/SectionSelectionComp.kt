package com.example.medical_tab

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medical_tab.ui.theme.PrimaryColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SectionSelectionSection(
    sections: List<String>,
    selectedSection: String?,
    onSectionSelected: (String) -> Unit
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
                text = "Select Section",
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                sections.forEach { sectionName ->
                    Button(
                        onClick = { onSectionSelected(sectionName) },
                        modifier = Modifier.widthIn(min = 80.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedSection == sectionName)
                                PrimaryColor
                            else
                                Color(0xFFE3F2FD),
                            contentColor = if (selectedSection == sectionName)
                                Color.White
                            else
                                PrimaryColor
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            sectionName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
