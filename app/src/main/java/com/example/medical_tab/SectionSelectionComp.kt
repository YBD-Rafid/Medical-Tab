package com.example.medical_tab

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.res.Configuration
import com.example.medical_tab.ui.theme.PrimaryColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SectionSelectionSection(
    sections: List<String>,
    selectedSection: String?,
    onSectionSelected: (String) -> Unit
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
                text = "Select Section",
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
                sections.chunked(itemsPerRow).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowItems.forEach { sectionName ->
                            Button(
                                onClick = { onSectionSelected(sectionName) },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(buttonHeight),
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
                                contentPadding = PaddingValues(horizontal = 4.dp)
                            ) {
                                Text(
                                    sectionName,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = if (isLandscape) 18.sp else 20.sp,
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
