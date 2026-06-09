package com.example.businessdirectory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.businessdirectory.adapter.CompanyAdapter
import com.example.businessdirectory.databinding.FragmentCompanyListBinding
import com.example.businessdirectory.model.Company
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CompanyListFragment : Fragment() {

    private var _binding: FragmentCompanyListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CompanyAdapter
    private var currentCategory: String = ""
    private var allCompanies: List<Company> = emptyList()

    companion object {
        fun newInstance(category: String): CompanyListFragment {
            val fragment = CompanyListFragment()
            val args = Bundle()
            args.putString("category", category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentCategory = arguments?.getString("category") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompanyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadCompanies()
    }

    private fun setupRecyclerView() {
        adapter = CompanyAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    fun filter(query: String) {
        val filteredList = if (query.isEmpty()) {
            allCompanies
        } else {
            allCompanies.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        adapter.updateData(filteredList)
    }

    private fun loadCompanies() {
        val url = "http://10.0.2.2:80/business_api/get_companies.php?category=$currentCategory"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val gson = Gson()
                    val type = object : TypeToken<List<Company>>() {}.type
                    allCompanies = gson.fromJson(response, type)
                    adapter.updateData(allCompanies)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Грешка при читање на податоци", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(requireContext(), "Грешка: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )

        Volley.newRequestQueue(requireContext()).add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}