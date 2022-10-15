package com.example.myapplication

import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import io.realm.kotlin.executeTransactionAwait
import kotlinx.coroutines.Dispatchers

open class Operations() {

    suspend fun insertData(name: String, email: String) {

        val realm = Realm.getDefaultInstance()

        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val nextId: Int

            val currentIdNum: Number? = realmTransaction
                .where(PersonRealm::class.java)
                .max("id")

            nextId = if (currentIdNum?.toString() == null) {
                1
            } else {
                currentIdNum.toInt() + 1
            }

            val person = PersonRealm(id = nextId, name = name, email = email)
            realmTransaction.insertOrUpdate(person)
        }
        realm.close()
        //realmTransaction.where(PersonRealm::class.java).findAll()
    }


    fun retrieveDataPersonObject(): ArrayList<Person> {

        val realm = Realm.getDefaultInstance()
        val people = ArrayList<Person>()

        people.addAll(realm
            .where(PersonRealm::class.java)
//            .sort("name", Sort.ASCENDING)
            .sort("id", Sort.ASCENDING)
            .findAll()
            //return Realm Result object
            .map { person ->
                Person(
                    id = person.id,
                    name = person.name,
                    email = person.email
                )
            }
        )
        realm.close()

        return people
    }

    suspend fun removeData(personId: Int) {

        val realm = Realm.getDefaultInstance()

        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val dataToRemove = realmTransaction
                .where(PersonRealm::class.java)
                .equalTo("id", personId)
                .findFirst()

            dataToRemove?.deleteFromRealm()
        }
        realm.close()
    }

    suspend fun updateData(personId: Int, name: String, email: String) {

        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAwait(Dispatchers.IO) { realmTransaction ->
            val dataToUpdate = realmTransaction
                .where(PersonRealm::class.java)
                .equalTo("id", personId)
                .findFirst()
            dataToUpdate?.id?.let {
                if (it == personId) {
                    dataToUpdate.name = name
                    dataToUpdate.email = email
                    realmTransaction.insertOrUpdate(dataToUpdate)
                } else {
                    Log.d("Cannot Modify", "Cannot Modify Id")
                }
            }
        }
        realm.close()
    }

     fun retrieveDataRealmObject(): RealmResults<PersonRealm>? {

        val realm = Realm.getDefaultInstance()

        return realm
            .where(PersonRealm::class.java)
            .sort("id", Sort.ASCENDING)
            .findAll()
    }
}


