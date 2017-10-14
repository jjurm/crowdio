package com.treecio.crowdio.model

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.google.api.client.util.NullValue
import com.google.api.client.util.Value
import com.treecio.crowdio.R
import com.treecio.crowdio.util.type.ColorResource
import com.treecio.crowdio.util.type.DrawableResource
import com.treecio.crowdio.util.type.StringResource

enum class Category(
        @StringRes val title: StringResource,
        @DrawableRes val icon: DrawableResource,
        @DrawableRes val coloredIcon: DrawableResource,
        @ColorRes val color: ColorResource
) {

    @Value("art") art(R.string.category_art, R.drawable.icon_art, R.drawable.colored_art, R.color.colorCategoryArt),
    @Value("dancing") dancing(R.string.category_dancing, R.drawable.icon_dancing, R.drawable.colored_dancing, R.color.colorCategoryDancing),
    @Value("vocal") vocal(R.string.category_vocal, R.drawable.icon_vocal, R.drawable.colored_vocal, R.color.colorCategoryVocal),
    @Value("magic") magic(R.string.category_magic, R.drawable.icon_magic, R.drawable.colored_magic, R.color.colorCategoryMagic),
    @Value("music") music(R.string.category_music, R.drawable.icon_music, R.drawable.colored_music, R.color.colorCategoryMusic),
    @Value("theatre") theatre(R.string.category_theatre, R.drawable.icon_theatre, R.drawable.colored_theatre, R.color.colorCategoryTheatre),
    @Value("other") @NullValue other(R.string.category_other, R.drawable.icon_other, R.drawable.colored_other, R.color.colorCategoryOther);

}
