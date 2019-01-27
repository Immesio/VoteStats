package com.example.sierra.votestatistic.Interfaces

import com.google.firebase.database.DataSnapshot

interface OnGetDataListener {
    fun onSuccess(dataSnapshot: DataSnapshot)
    fun onFailure()
}
