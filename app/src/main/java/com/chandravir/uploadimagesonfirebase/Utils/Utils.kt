package com.chandravir.uploadimagesonfirebase.Utils

import android.media.Image
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

fun uploadImage(uri: Uri, folderName: String, callback:(String?)->Unit){

    var imageURL : String? = null

    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageURL = it.toString()
                callback(imageURL)
            }
        }

}