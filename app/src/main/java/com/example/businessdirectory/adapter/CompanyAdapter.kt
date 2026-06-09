package com.example.businessdirectory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.businessdirectory.databinding.ItemCompanyBinding
import com.example.businessdirectory.model.Company

class CompanyAdapter(
    private var companies: List<Company> = emptyList()
) : RecyclerView.Adapter<CompanyAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCompanyBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCompanyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val company = companies[position]
        with(holder.binding) {
            tvName.text = company.name
            tvAddress.text = company.address
            tvPhone.text = company.phone
            tvWebsite.text = company.website
            // Подоцна можеш да додаваш слика со Glide
        }
    }

    override fun getItemCount(): Int = companies.size

    // За филтрирање
    fun updateData(newCompanies: List<Company>) {
        companies = newCompanies
        notifyDataSetChanged()
    }
}