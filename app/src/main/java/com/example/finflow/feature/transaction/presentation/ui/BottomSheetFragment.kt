package com.example.finflow.feature.transaction.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finflow.databinding.FragmentBottomSheetBinding
import com.example.finflow.feature.transaction.domain.model.Category
import com.example.finflow.feature.transaction.domain.model.CategoryIcon
import com.example.finflow.feature.transaction.domain.model.CategoryType
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        const val REQUEST_KEY = "category_picker_request"
        const val RESULT_CATEGORY_ID = "result_category_id"
    }

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomSheetAdapter: BottomSheetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadMockCategories()
    }

    private fun loadMockCategories() {
        val categories = listOf(
            Category(
                id = 1,
                name = "food",
                icon = CategoryIcon.FOOD,
                type = CategoryType.EXPENSE,
                isArchived = false,
                isSystem = true,
                isDefault = true,
            ),
            Category(
                id = 2,
                name = "Transport",
                icon = CategoryIcon.TRANSPORT,
                type = CategoryType.EXPENSE,
                isArchived = false,
                isSystem = true,
                isDefault = true,
            ),
            Category(
                id = 3,
                name = "Salary",
                icon = CategoryIcon.SALARY,
                type = CategoryType.INCOME,
                isArchived = false,
                isSystem = true,
                isDefault = true,
            ),
            Category(
                id = 4,
                name = "cafe",
                icon = CategoryIcon.CAFE,
                type = CategoryType.EXPENSE,
                isArchived = false,
                isSystem = true,
                isDefault = true,
            )
        )
        bottomSheetAdapter.submitList(categories)
    }

    private fun setupRecyclerView() {
        bottomSheetAdapter = BottomSheetAdapter { category ->

            parentFragmentManager.setFragmentResult(
                REQUEST_KEY,
                bundleOf(
                    RESULT_CATEGORY_ID to category.id
                )
            )
            dismiss()

        }
        binding.recyclerCategory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bottomSheetAdapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}