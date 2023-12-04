package com.example.tpapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.i
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMyData()
    }

    private fun getMyData() {

        val rcl: RecyclerView = findViewById(R.id.rcl)

      rcl.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        rcl.layoutManager = linearLayoutManager


     //   val txt: TextView = findViewById(R.id.txt)

        //add retrofit builder
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
               val responseBody = response.body()!!
                myAdapter = MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()
                rcl.adapter = myAdapter

                  // val myStringBuilder = StringBuilder()
                  // val responseBody = response.body()!!

                /*   for (i in responseBody) {
                       myStringBuilder.append(i.id)
                       myStringBuilder.append("\n")
                   }
                   txt.text = myStringBuilder*/


            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                i("erreur api", t.message.toString())

            }
        }
        )
    }
}