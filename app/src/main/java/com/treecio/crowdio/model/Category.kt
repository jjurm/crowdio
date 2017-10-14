package com.treecio.crowdio.model

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.treecio.crowdio.util.type.DrawableResource
import com.treecio.crowdio.util.type.StringResource

enum class Category(
        @StringRes val title: StringResource,
        @DrawableRes val icon: DrawableResource
) {

    music(0, 0)

}
