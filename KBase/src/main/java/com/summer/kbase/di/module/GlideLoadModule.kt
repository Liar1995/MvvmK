package com.summer.kbase.di.module

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * Created by sunmeng on 2018/3/13.
 * Email:sunmeng995@gmail.com
 * Description:
 */
@GlideModule
class GlideLoadModule : AppGlideModule(){
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}