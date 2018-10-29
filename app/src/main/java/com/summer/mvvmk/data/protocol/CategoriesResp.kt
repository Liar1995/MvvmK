package com.summer.mvvmk.data.protocol

/**
 * Created by sunmeng on 2018/10/29.
 * Email:sunmeng995@gmail.com
 * Description:
 */

data class CategoriesResp(
        val error: Boolean,
        val results: List<Results>
)

data class Results(
        val _id: String,
        val en_name: String,
        val name: String,
        val rank: Int
)