package com.example.diceroller
import java.sql.*


class TestMysqlConnect {

}

fun main() {


    val jdbcUrl = "jdbc:mysql://<sql url>/TestAndroidStudio"
    //val jdbcUrl = "jdbc:mysql://http://192.168.0.11:3306/testandroidstudio"

    // get the connection
    //Class.forName("com.mysql.jdbc.Driver")
    val connection = DriverManager.getConnection(jdbcUrl, "root", "")
    //val connection = DriverManager.getConnection(jdbcUrl, "root", "221934420a")

    // prints true if the connection is valid
    println(connection.isValid(0))

    // the query is only prepared not executed
    val query = connection.prepareStatement("SELECT * FROM account")

    // the query is executed and results are fetched
    val result = query.executeQuery()

    // an empty list for holding the results
    //val users = mutableListOf<User>()
    println(result)

    while(result.next()){

        // getting the value of the id column
        val uid = result.getInt("uid")

        // getting the value of the name column
        val id = result.getString("id")
        val password = result.getString("password")
        val email = result.getString("email")
        println("uid:${uid} id:${id} password:${password} email:${email}")

        /*
        constructing a User object and
        putting data into the list
         */
        //users.add(User(id, name))
    }
}

// the model class
