package com.treecio.crowdio.model

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.treecio.crowdio.R
import com.treecio.crowdio.util.type.ColorResource
import com.treecio.crowdio.util.type.DrawableResource
import com.treecio.crowdio.util.type.StringResource

enum class Category(
        @StringRes val title: StringResource,
        @DrawableRes val icon: DrawableResource,
        @ColorRes val color: ColorResource
) {

    art(R.string.category_art, R.drawable.icon_art, R.color.colorCategoryArt),
    dancing(R.string.category_dancing, R.drawable.icon_dancing, R.color.colorCategoryDancing),
    vocal(R.string.category_vocal, R.drawable.icon_vocal, R.color.colorCategoryVocal),
    magic(R.string.category_magic, R.drawable.icon_magic, R.color.colorCategoryMagic),
    music(R.string.category_music, R.drawable.icon_music, R.color.colorCategoryMusic),
    theatre(R.string.category_theatre, R.drawable.icon_theatre, R.color.colorCategoryTheatre),
    other(R.string.category_other, R.drawable.icon_other, R.color.colorCategoryOther);

}
