package com.example.store.features.userprofile.helpcenter.faq

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.store.features.userprofile.helpcenter.faq.components.QuestionCard
import com.example.store.features.userprofile.helpcenter.faq.components.QuestionCategory
import com.example.store.features.userprofile.helpcenter.faq.components.QuestionCategorySelector
import com.example.store.features.userprofile.helpcenter.faq.components.questions

@Composable
fun FaqContent(modifier: Modifier = Modifier) {

    var currentCategory by remember { mutableStateOf(QuestionCategory.Services) }

    Column(modifier = modifier) {
        QuestionCategorySelector(
            modifier = Modifier,
            currentCategory = currentCategory,
            onCategoryClick = { currentCategory = it }
        )
        Spacer(modifier = Modifier.height(18.dp))
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            questions.filter { it.category == currentCategory }.forEach { question ->
                QuestionCard(
                    helpQuestion = question
                )
            }
        }
    }
}
