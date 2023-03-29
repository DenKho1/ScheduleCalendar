package com.mobdeve.s13.kho.denise.schedulecalendar

import android.content.Context

class AccountDBHelper(context: Context?) {

}

private object AccountDBReferences{
    const val DATABASE_VERSION=1
    const val DATABASE_NAME="Chedulers.db"

    const val TABLE_NAME ="accounts"
    const val _ID = "id"
    const val COLUMN_USERNAME="username"
    const val COLUMN_PASSWORD="password"
    const val COLUMN_EMAIL="email"
    const val COLUMN_MOBILE="mobile"
    const val COLUMN_NAME_IMAGE_URI = "image_uri"

    const val CREATE_TABLE_STATEMENT=
        "CREATE TABLE IF NOT EXISTS" + TABLE_NAME+"("+
                _ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_USERNAME+ " TEXT, " +
                COLUMN_PASSWORD+ " TEXT, " +
                COLUMN_EMAIL+ " TEXT, "+
                COLUMN_MOBILE+" TEXT, " +
                COLUMN_NAME_IMAGE_URI + " TEXT)"

    const val DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS " + TABLE_NAME

}