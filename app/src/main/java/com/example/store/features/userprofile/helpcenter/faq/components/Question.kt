package com.example.store.features.userprofile.helpcenter.faq.components

enum class QuestionCategory(val description: String) {
    Services("Serviços"),
    General("Geral"),
    Account("Conta")
}

data class Question(
    val question: String,
    val answer: String,
    val category: QuestionCategory
)

val helpQuestionTest = Question(
    question = "How do I return a product?",
    answer = "Lorem Ipsum is simply dummy text of the printing and n" +
            "typesetting industry. Lorem Ipsum has been the industry's ",
    category = QuestionCategory.Services
)
val questions = listOf(
    helpQuestionTest,
    helpQuestionTest,
    helpQuestionTest,
    helpQuestionTest,
    helpQuestionTest,
    helpQuestionTest,
)