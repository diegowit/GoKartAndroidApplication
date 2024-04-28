package com.example.gokartandroidapplication.models
import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import timber.log.Timber
import timber.log.Timber.i
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

abstract class GoKartFireStore : GoKartStorage {

    private val db = Firebase.firestore
    private val gokartDocuments = db.collection("gokarts")
   /* suspend override fun findAll(): List<GoKartModel> = suspendCoroutine { continuation ->
        val gokartsList = mutableListOf<GoKartModel>()

        gokartDocuments.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    gokartsList.add(
                        GoKartModel(
                            id = (document.data["id"] as? Long) ?: 0,
                            name = document.data["name"] as? String ?: "",
                            gender = document.data["gender"] as? String ?: "",
                            carModel = document.data["carModel"] as? String ?: "",
                            image = Uri.parse(document.data["image"] as? String ?: ""), // Parse the Uri as a String
                            lat = (document.data["lat"] as? Double) ?: 0.0,
                            lng = (document.data["lng"] as? Double) ?: 0.0,
                            zoom = (document.data["zoom"] as? Float) ?: 0f
                        )
                    )
                }
                continuation.resume(gokartsList)
            }
            .addOnFailureListener { exception ->
                Timber.i("Error getting documents $exception")
                continuation.resumeWithException(exception)
            }
    }
*/
    override fun findById(id: Long): GoKartModel? {
        //TODO not done yet
        return GoKartModel()
    }

    override fun create(gokart: GoKartModel) {

        val data = mutableMapOf<String, Any>().apply {
            // Add fields directly into the map
            put("id", generateRandomId())
            put("title", gokart.name)
            put("description", gokart.gender)
            put("description", gokart.carModel)
            put("image", gokart.image.toString()) // Store the Uri as a String
            put("lat", gokart.lat)
            put("lng", gokart.lng)
            put("zoom", gokart.zoom)
        }

        gokartDocuments
            .add(data)
            .addOnSuccessListener { documentReference ->
                i("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                i("Error adding document: $e")
            }
    }

  /*  suspend override fun update(gokart: GoKartModel) {
        // val gokartReference = databaseReference.child(gokart.id.toString())
        //gokartReference.setValue(gokart)
    }
*/
    override fun delete(gokart: GoKartModel) {
        //val gokartReference = databaseReference.child(gokart.id.toString())
        //gokartReference.removeValue()
    }
}
