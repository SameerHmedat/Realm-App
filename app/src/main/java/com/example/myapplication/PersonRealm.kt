package com.example.myapplication

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PersonRealm(
    @PrimaryKey
    var id: Int = 0,
    var name: String = "",
    var email: String = ""

) : RealmObject()