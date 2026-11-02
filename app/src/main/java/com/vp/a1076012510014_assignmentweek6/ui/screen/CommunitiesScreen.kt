package com.vp.a1076012510014_assignmentweek6.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vp.a1076012510014_assignmentweek6.model.Community
import com.vp.a1076012510014_assignmentweek6.ui.widgets.CommunityCard

@Composable
fun CommunitiesScreen(
    communities: List<Community>,
    onToggleJoin: (String) -> Unit,
    onCardClick: (Community) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F8FB))
            .statusBarsPadding(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                "Communities Directory",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
            Text("Pilih komunitas untuk membuka Club Projects", fontSize = 12.sp, color = Color.Gray)
        }

        items(communities, key = { it.id }) { c ->
            CommunityCard(
                community = c,
                onToggleJoin = { onToggleJoin(c.id) },
                onClick = { onCardClick(c) }
            )
        }
    }
}