package com.example.businessdirectory

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.businessdirectory.databinding.ActivityAddCompanyBinding

class AddCompanyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCompanyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCompanyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveCompany()
        }
    }

    private fun saveCompany() {

        val categories = mutableListOf<String>()
        if (binding.cbServices.isChecked) categories.add("Services")
        if (binding.cbEntertainment.isChecked) categories.add("Entertainment")
        if (binding.cbIndustry.isChecked) categories.add("Industry")
        if (binding.cbEducation.isChecked) categories.add("Education")

        val params = HashMap<String, String>()
        params["name"] = binding.etName.text.toString()
        params["address"] = binding.etAddress.text.toString()
        params["latitude"] = binding.etLat.text.toString()
        params["longitude"] = binding.etLng.text.toString()
        params["email"] = binding.etEmail.text.toString()
        params["phone"] = binding.etPhone.text.toString()
        params["website"] = binding.etWebsite.text.toString()
        params["categories"] = categories.joinToString(",")

        val url = "http://10.0.2.2:80/business_api/add_company.php"

        val request = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                Toast.makeText(this, response, Toast.LENGTH_LONG).show()
                finish()
            },
            { error ->
                Toast.makeText(this, "Грешка: ${error.message}", Toast.LENGTH_LONG).show()
            }
        ) {

            override fun getParams(): MutableMap<String, String> {
                return params
            }

            override fun getHeaders(): MutableMap<String, String> {
                return hashMapOf(
                    "Content-Type" to "application/x-www-form-urlencoded"
                )
            }
        }

        Volley.newRequestQueue(this).add(request)
    }
}