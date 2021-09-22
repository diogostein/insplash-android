package com.codelabs.insplash.app

import com.codelabs.insplash.app.models.Topic

object Const {

    object GridViewPaging {
        const val PAGE_SIZE = 29
        const val DISTANCE_TO_LOAD_MORE = 1
    }

    object Seed {
        val topics = listOf(
            Topic(slug = "wallpapers", title = "Wallpapers"),
            Topic(slug = "nature", title = "Nature"),
            Topic(slug = "people", title = "People"),
            Topic(slug = "animals", title = "Animals"),
            Topic(slug = "architecture", title = "Architecture"),
            Topic(slug = "business-work", title = "Business & Work"),
            Topic(slug = "fashion", title = "Fashion"),
            Topic(slug = "food-drink", title = "Food & Drink"),
            Topic(slug = "film", title = "Film"),
            Topic(slug = "travel", title = "Travel"),
            Topic(slug = "technology", title = "Technology"),
            Topic(slug = "arts-culture", title = "Arts & Culture"),
            Topic(slug = "history", title = "History"),
            Topic(slug = "family", title = "Family"),
            Topic(slug = "work", title = "Work"),
            Topic(slug = "friends", title = "Friends"),
            Topic(slug = "relationship", title = "Relationship"),
        )
    }

}
