package com.example.dev.southbrmemes.model.utils


import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

import java.io.File

class PhotoProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    companion object {

        val CONTENT_PROVIDER_AUTHORITY = "com.exemple.dev.southbrmemes.model.Utils.PhotoProvider"

        fun getPhotoUri(file: File): Uri {
            val outputUri = Uri.fromFile(file)
            val builder = Uri.Builder()
                    .authority(CONTENT_PROVIDER_AUTHORITY)
                    .scheme("file")
                    .path(outputUri.path)
                    .query(outputUri.query)
                    .fragment(outputUri.fragment)

            return builder.build()
        }
    }
}
