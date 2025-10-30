package com.example.bookshelf.Presentation.BookShelfScreen.Components.EditBottomSheet

import com.example.bookshelf.Common.Filter

data class EditBottomSheetDropDownItem(
    val label : String,
    val type : String
)

val editBottomSheetDropDownItems = listOf<EditBottomSheetDropDownItem>(
    EditBottomSheetDropDownItem("Finished", Filter.FINISHED),
    EditBottomSheetDropDownItem("Currently Reading", Filter.CURRENTLY_READING),
    EditBottomSheetDropDownItem("Not Started", Filter.NOT_STARTED),
)