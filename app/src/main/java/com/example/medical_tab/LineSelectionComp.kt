package com.example.medical_tab

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.res.Configuration
import com.example.medical_tab.model.SectionLineModel
import com.example.medical_tab.ui.theme.PrimaryColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LineSelectionSection(
    sectionName: String,
    lines: List<SectionLineModel>,
    selectedLineId: String?,
    onLineSelected: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().heightIn(min = 180.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp)
        ) {
            Text(
                text = "Select Line ($sectionName)",
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            val configuration = LocalConfiguration.current
            val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            val itemsPerRow = if (isLandscape) 8 else 5
            val buttonHeight = if (isLandscape) 56.dp else 64.dp

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
                                    .height(buttonHeight),
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
                                contentPadding = PaddingValues(horizontal = 4.dp)
                            ) {
                                Text(
                                    line.LineName.replace("Line ", ""),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = if (isLandscape) 16.sp else 18.sp,
                                    maxLines = 1
                                )
                            }
                        }
                        // Add spacers to maintain grid alignment if row is not full
                        repeat(itemsPerRow - rowItems.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}
