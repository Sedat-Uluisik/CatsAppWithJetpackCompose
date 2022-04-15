package com.sedat.catsappwithjetpackcompose.Util

import androidx.room.TypeConverter
import com.sedat.catsappwithjetpackcompose.model.Image
import com.sedat.catsappwithjetpackcompose.model.Weight

class Converter {
    @TypeConverter
    fun fromImage(image: Image?): String{
        return image?.url ?: ""
    }

    @TypeConverter
    fun toImage(url: String): Image{
        return Image(0, "", url, 0)
    }

    @TypeConverter
    fun fromWeight(weight: Weight): String{
        return weight.metric.toString()
    }

    @TypeConverter
    fun toWeight(metric: String): Weight {
        return Weight("", metric)
    }
}