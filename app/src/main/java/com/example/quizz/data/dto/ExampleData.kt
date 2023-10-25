package com.example.quizz.data.dto

import com.google.gson.Gson

fun getData(): QuestionsDto {
    val json = "{\n" +
            "    \"questions\": [\n" +
            "        {\n" +
            "            \"questionText\": \"Who won the FIFA World Cup in 2018?\",\n" +
            "            \"answers\": [\n" +
            "                {\n" +
            "                    \"answerText\": \"Germany\",\n" +
            "                    \"isValid\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"France\",\n" +
            "                    \"isValid\": true\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"Brazil\",\n" +
            "                    \"isValid\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"Spain\",\n" +
            "                    \"isValid\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"questionText\": \"Which player has the most goals in the history of FIFA World Cup?\",\n" +
            "            \"answers\": [\n" +
            "                {\n" +
            "                    \"answerText\": \"Pele\",\n" +
            "                    \"isValid\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"Miroslav Klose\",\n" +
            "                    \"isValid\": true\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"Lionel Messi\",\n" +
            "                    \"isValid\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"Ronaldo\",\n" +
            "                    \"isValid\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"questionText\": \"Which country has won the most UEFA Champions League titles?\",\n" +
            "            \"answers\": [\n" +
            "                {\n" +
            "                    \"answerText\": \"Bayern Munich\",\n" +
            "                    \"isValid\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"AC Milan\",\n" +
            "                    \"isValid\": false\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"Real Madrid\",\n" +
            "                    \"isValid\": true\n" +
            "                },\n" +
            "                {\n" +
            "                    \"answerText\": \"Barcelona\",\n" +
            "                    \"isValid\": false\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}"

    val gson = Gson()

    return gson.fromJson(json, QuestionsDto::class.java)
}