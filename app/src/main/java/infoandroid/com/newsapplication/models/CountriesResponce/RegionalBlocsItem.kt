package infoandroid.com.newsapplication.models.CountriesResponce


import com.google.gson.annotations.SerializedName


data class RegionalBlocsItem(

	@field:SerializedName("otherNames")
	val otherNames: List<Any?>? = null,

	@field:SerializedName("acronym")
	val acronym: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("otherAcronyms")
	val otherAcronyms: List<Any?>? = null
)