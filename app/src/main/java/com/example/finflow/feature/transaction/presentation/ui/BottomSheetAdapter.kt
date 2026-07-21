package com.example.finflow.feature.transaction.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finflow.databinding.ItemCategoryPickerBinding
import com.example.finflow.feature.transaction.domain.model.Category


class BottomSheetAdapter(
    private val onCategoryClick: (Category) -> Unit
) : ListAdapter<Category, BottomSheetAdapter.CategoryViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding =
            ItemCategoryPickerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position), onCategoryClick)
    }


    class CategoryViewHolder(
        val binding: ItemCategoryPickerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            category: Category,
            onCategoryClick: (Category) -> Unit
        ) {
            binding.txtCategoryName.text = category.name
            binding.root.setOnClickListener {
                onCategoryClick(category)
            }
        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem == newItem
        }

    }
}