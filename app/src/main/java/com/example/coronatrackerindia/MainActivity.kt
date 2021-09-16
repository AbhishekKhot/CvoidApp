package com.example.coronatrackerindia

import android.icu.number.IntegerWidth
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.coronatrackerindia.api.ApiInterfac
import com.example.coronatrackerindia.api.CountryData
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val DATE_FORMAT = "dd/MM/yy hh:mm"

    lateinit var list: List<CountryData>
    companion object {
        const val BASE_URL_API = "https://corona.lmao.ninja/v2/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiInterfac::class.java)
        val countryRequest = service.listCountries()

        countryRequest.enqueue(object: Callback<List<CountryData>> {
            override fun onResponse(call: Call<List<CountryData>>, response: retrofit2.Response<List<CountryData>>, ) {
              list= response.body()!!
               for(i in 0 until list.size){
                   if(list[i].country.equals("India")){
                       idIndiaConfirm.text=java.text.NumberFormat.getInstance().format(Integer.parseInt(list.get(i).cases))
                       idIndiaActive.text=java.text.NumberFormat.getInstance().format(Integer.parseInt(list.get(i).active))
                       idIndiaRecovered.text=java.text.NumberFormat.getInstance().format(Integer.parseInt(list.get(i).recovered))
                       idIndiaDeaths.text=java.text.NumberFormat.getInstance().format(Integer.parseInt(list.get(i).deaths))
                       todayConfirmed.text=java.text.NumberFormat.getInstance().format(Integer.parseInt(list.get(i).todayCases))
                       todayRecovered.text=java.text.NumberFormat.getInstance().format(Integer.parseInt(list.get(i).todayDeaths))
                       todayDeaths.text=java.text.NumberFormat.getInstance().format(Integer.parseInt(list.get(i).todayDeaths))
                       totalTests.text=java.text.NumberFormat.getInstance().format(Integer.parseInt(list.get(i).tests))
                       val updateddate=millisToDate(list.get(i).updated)
                       date.text= updateddate
                   }
               }
            }
            override fun onFailure(call: Call<List<CountryData>>, t: Throwable) {
               Toast.makeText(this@MainActivity,"error occurred $t",Toast.LENGTH_LONG).show()
            }
        })
    }
    fun millisToDate(millis: Long) : String {
        return SimpleDateFormat(DATE_FORMAT, Locale.US).format(Date(millis))
    }
}