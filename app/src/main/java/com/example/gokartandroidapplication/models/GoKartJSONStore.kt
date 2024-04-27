package com.example.gokartandroidapplication.models


import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.example.gokartandroidapplication.helpers.*
import com.example.gokartandroidapplication.utils.Utilities
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*
import com.example.gokartandroidapplication.utils.Utilities.isValidListIndex


const val JSON_FILE = "gokart.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<GoKartModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class GoKartJSONStore(private val context: Context) : GoKartStorage {

    var gokarts = mutableListOf<GoKartModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<GoKartModel> {
        logAll()
        return gokarts
    }


    /*
    // Check if the index is a valid index for the karts list
    fun isValidIndex(index: Int): Boolean {
        return Utilities.isValidListIndex(index, gokarts)
    }

    // Find a kart by its index in the karts list
    fun findKart(index: Int): GoKartModel? {
        return if (isValidIndex(index)) {
            gokarts[index]
        } else null
    }

    fun formatListString(kartsToFormat: List<GoKartModel>): String =
        kartsToFormat
            .joinToString(separator = "\n") { kart ->
                gokarts.indexOf(kart).toString() + ": " + kart.toString()
            }
    override fun searchByName(searchString: String) =
        formatListString(gokarts.filter { it.name.contains(searchString, ignoreCase = true) })


    */


    override fun findByName(name:String) : GoKartModel? {
        val foundGoKart: GoKartModel? = gokarts.find { it.name == name }
        return foundGoKart
    }



    override fun findById(id:Long) : GoKartModel? {
        val foundGoKart: GoKartModel? = gokarts.find { it.id == id }
        return foundGoKart
    }


    override fun create(gokart: GoKartModel) {
        gokart.id = generateRandomId()
        gokarts.add(gokart)
        serialize()
    }


    override fun update(gokart: GoKartModel) {
        val gokartsList = findAll() as ArrayList<GoKartModel>
        val foundGoKart: GoKartModel? = gokartsList.find { p -> p.id == gokart.id }
        if (foundGoKart != null) {
            foundGoKart.name = gokart.name
            foundGoKart.gender = gokart.gender
            foundGoKart.carModel = gokart.carModel
            foundGoKart.image = gokart.image
            foundGoKart.lat = gokart.lat
            foundGoKart.lng = gokart.lng
            foundGoKart.zoom = gokart.zoom
        }
        serialize()
    }

    override fun delete(gokart: GoKartModel) {
        gokarts.remove(gokart)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(gokarts, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        gokarts = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        gokarts.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}