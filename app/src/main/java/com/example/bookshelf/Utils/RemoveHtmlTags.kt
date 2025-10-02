package com.example.bookshelf.Utils

fun cleanHtmlTags(rawHtml: String): String {
    return rawHtml
        .replace(Regex("<br\\s*/?>", RegexOption.IGNORE_CASE), "\n") // Replace <br> with new lines
        .replace(Regex("<.*?>"), "") // Remove all other HTML tags
        .trim()
}
