package com.example.medical_tab

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medical_tab.model.SectionLineModel
import com.example.medical_tab.ui.theme.PrimaryColor

@Composable
fun LineSelectionSection(
    sectionName: String,
    lines: List<SectionLineModel>,
    selectedLineId: String?,
    modifier: Modifier = Modifier,
    onLineSelected: (String) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth().height(280.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Select Line ($sectionName)",
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            val itemsPerRow = 5

            // Using Column with chunked to enforce exactly 6 per row
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                lines.chunked(itemsPerRow).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowItems.forEach { line ->
                            Button(
                                onClick = { onLineSelected(line.LineId) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedLineId == line.LineId)
                                        Color(0xFF4CAF50)
                                    else
                                        Color(0xFFE8F5E9),
                                    contentColor = if (selectedLineId == line.LineId)
                                        Color.White
                                    else
                                        Color(0xFF4CAF50)
                                ),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Text(
                                    text = line.LineName.replace("Line ", "").trim(),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    maxLines = 1
                                )
                            }
                        }
                        // Add empty spacers to maintain exactly 6 columns
                        repeat(itemsPerRow - rowItems.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}