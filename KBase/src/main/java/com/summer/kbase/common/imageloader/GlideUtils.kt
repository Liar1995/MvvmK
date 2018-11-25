package com.summer.kbase.common.imageloader

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.summer.kbase.R
import com.summer.kbase.common.LoggerUtils
import com.summer.kbase.di.module.GlideApp


/**
 * Created by sunmeng on 2018/3/13.
 * Email:sunmeng995@gmail.com
 * Description:Glide封装
 */
object GlideUtils {

    /**
     * 指定options对象加载
     * */
    fun loadByOptions(context: Context, url: String, imageView: ImageView, options: RequestOptions?) {
        if (options == null) {
            val commonOptions = RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
            GlideApp.with(context).load(url).apply(commonOptions).centerCrop().into(imageView)
        } else {
            GlideApp.with(context).load(url).apply(options).centerCrop().into(imageView)
        }
    }

    /**
     * 加载普通图片（裁剪）
     * */
    fun loadNormalImage(context: Context, url: String, imageView: ImageView) {
        val commonOptions = RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
        GlideApp.with(context).load(url).apply(commonOptions).centerCrop().into(imageView)
    }

    /**
     * 加载普通图片（拉伸）
     * */
    fun loadFitXYImage(context: Context, url: String, imageView: ImageView) {
        val commonOptions = RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
        GlideApp.with(context).load(url).apply(commonOptions).into(imageView)
    }

    /**
     * 指定宽高加载
     * */
    fun loadThumbnailImage(context: Context, url: String, imageView: ImageView, width: Int, height: Int) {
        val commonOptions = RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image).override(width, height)
        GlideApp.with(context).load(url).apply(commonOptions).centerCrop().into(imageView)
    }

    /**
     * 加载原图
     * */
    fun loadOriginalImage(context: Context, url: String, imageView: ImageView) {
        val commonOptions = RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image).override(Target.SIZE_ORIGINAL)
        GlideApp.with(context).load(url).apply(commonOptions).centerCrop().into(imageView)
    }

    /**
     * 加载gif
     * */
    fun loadGifImage(context: Context, imageView: ImageView, drawableId: Int) {
        val commonOptions = RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
        GlideApp.with(context).asGif().load(drawableId).apply(commonOptions).centerCrop().into(imageView)
    }

    /**
     * 通过res加载
     * */
    fun loadLocalImage(context: Context, imageView: ImageView, drawableId: Int) {
        val commonOptions = RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
        GlideApp.with(context).load(drawableId).apply(commonOptions).centerCrop().into(imageView)
    }

    /**
     * centerInside
     * */
    fun loadImageScaleType(context: Context, url: String, imageView: ImageView) {
        val commonOptions = RequestOptions().placeholder(R.drawable.placeholder_image).error(R.drawable.placeholder_image)
        GlideApp.with(context).load(url).apply(commonOptions).centerInside().into(imageView)
    }

}