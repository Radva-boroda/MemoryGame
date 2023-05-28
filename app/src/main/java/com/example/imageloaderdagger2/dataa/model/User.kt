package com.example.imageloaderdagger2.dataa.model

class User {
    var id: String = ""
    var name: String = ""
    var secName: String = ""
    var email: String = ""

    constructor()

    constructor(id: String, name: String, secName: String, email: String) {
        this.id = id
        this.name = name
        this.secName = secName
        this.email = email
    }
}