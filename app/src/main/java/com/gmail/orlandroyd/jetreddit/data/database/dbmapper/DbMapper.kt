package com.gmail.orlandroyd.jetreddit.data.database.dbmapper

import com.gmail.orlandroyd.jetreddit.domain.model.PostModel
import com.gmail.orlandroyd.jetreddit.data.database.model.PostDbModel

interface DbMapper {

    fun mapPost(dbPostDbModel: PostDbModel): PostModel

    fun mapDbPost(postModel: PostModel): PostDbModel
}