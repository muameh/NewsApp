package com.mbl.deneme_newsapi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.io.Serializable


@Entity(tableName = "favour_articles")
class ArticleForNavigation (@PrimaryKey(autoGenerate = true) val task_id:Int,
                            var articleURL : String = "null" ,
                            var articleTitle : String= "null",
                            var articleDescription : String= "null",
                            var articleDateTime : String= "null",
                            var articleSource : String= "null" ,
                            var articleUrloImage : String= "null" ) : Serializable {
}


