package com.example.finflow.feature.transaction.presentation.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.finflow.R
import com.example.finflow.databinding.FragmentTransactionBinding
import com.example.finflow.databinding.TransactionCategoryItemBinding
import com.example.finflow.feature.transaction.domain.model.Category
import com.example.finflow.feature.transaction.domain.model.CategoryIcon
import com.example.finflow.feature.transaction.domain.model.CategoryType
import com.example.finflow.feature.transaction.domain.model.TransactionType
import com.example.finflow.feature.transaction.presentation.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionFragment : Fragment() {
    private var _binding: FragmentTransactionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TransactionViewModel by viewModels()


    private lateinit var firstCategory: TransactionCategoryItemBinding
    private lateinit var secondCategory: TransactionCategoryItemBinding
    private lateinit var thirdCategory: TransactionCategoryItemBinding
    private lateinit var fourthCategory: TransactionCategoryItemBinding
    lateinit var categoryItems: List<CategoryItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding()
        setCategoryItem()
        createCategoryItems()

        onclickListener()
        onUiChange()
        observeUiState()
        setupFragmentResultListeners()
    }

    private fun createCategoryItems() {
        categoryItems = listOf(
            CategoryItem(firstCategory, foodCategories),
            CategoryItem(secondCategory, transportCategory),
            CategoryItem(thirdCategory, salaryCategory),
            CategoryItem(fourthCategory, cafeCategory)
        )
    }


    private fun onclickListener() {
        firstCategory.cardCategoryItem.setOnClickListener {
            viewModel.onCategorySelected(foodCategories)
        }
        secondCategory.cardCategoryItem.setOnClickListener {
            viewModel.onCategorySelected(transportCategory)
        }
        thirdCategory.cardCategoryItem.setOnClickListener {
            viewModel.onCategorySelected(salaryCategory)
        }
        fourthCategory.cardCategoryItem.setOnClickListener {
            viewModel.onCategorySelected(cafeCategory)
        }


        binding.imgMoreCategory.setOnClickListener {
            showCategory()
        }
    }


    private fun showCategory() {
        val bottomSheet = BottomSheetFragment()
        bottomSheet.show(childFragmentManager, BottomSheetFragment::class.java.simpleName)
    }


    private fun viewBinding() {
        firstCategory = TransactionCategoryItemBinding.bind(binding.categoryFirst.root)
        secondCategory = TransactionCategoryItemBinding.bind(binding.categorySecond.root)
        thirdCategory = TransactionCategoryItemBinding.bind(binding.categoryThird.root)
        fourthCategory = TransactionCategoryItemBinding.bind(binding.categoryFourth.root)
    }

    private fun observeUiState() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    renderTransaction(state.transactionType)
                    binding.layTxtAmount.error = state.amountError
                    val display =
                        buildDisplayCategories(state.selectedCategory, state.pinnedCategories)
                    renderCategories(display,state.selectedCategory)
                }

            }
        }
    }

    private fun renderTransaction(type: TransactionType) {
        val context = requireContext()
        when (type) {
            TransactionType.INCOME -> {
                binding.btnIncome.backgroundTintList = null
                binding.btnIncome.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_income_selected)
                binding.btnIncome.setTextColor(ContextCompat.getColor(context, R.color.white))


                binding.btnExpense.setTextColor(ContextCompat.getColor(context, R.color.black))
                binding.btnExpense.backgroundTintList = null
                binding.btnExpense.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_transaction_type_deselected)
            }

            TransactionType.EXPENSE -> {
                binding.btnIncome.backgroundTintList = null
                binding.btnIncome.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_transaction_type_deselected)
                binding.btnIncome.setTextColor(ContextCompat.getColor(context, R.color.black))

                binding.btnExpense.backgroundTintList = null
                binding.btnExpense.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_expense_selected)
                binding.btnExpense.setTextColor(ContextCompat.getColor(context, R.color.white))

            }
        }
    }

    private fun renderSelectedCategory(category: Category?) {
        categoryItems.forEach { item ->
            val isSelected = item.category.id == category?.id
            updateCategoryItem(
                item.binding, isSelected
            )
        }

    }

    private fun updateCategoryItem(
        binding: TransactionCategoryItemBinding,
        selected: Boolean
    ) {
        if (selected)
            binding.cardCategoryItem.strokeColor = requireContext().getColor(R.color.income_green)
        else
            binding.cardCategoryItem.strokeColor =
                requireContext().getColor(R.color.category_stroke)
    }

    private fun onUiChange() {
        binding.edtAmount.doAfterTextChanged {
            viewModel.onAmountChanged(it.toString())
        }

        binding.btnIncome.setOnClickListener {
            viewModel.transactionTypeChanged(TransactionType.INCOME)
        }

        binding.btnExpense.setOnClickListener {
            viewModel.transactionTypeChanged(TransactionType.EXPENSE)
        }


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun setCategoryItem() {
        bindCategory(firstCategory, foodCategories)
        bindCategory(secondCategory, salaryCategory)
        bindCategory(thirdCategory, transportCategory)
        bindCategory(fourthCategory, cafeCategory)
    }


    fun bindCategory(
        b: TransactionCategoryItemBinding,
        category: Category
    ) {
        b.txtCategoryName.text = category.name
    }


    private fun setupFragmentResultListeners() {
        childFragmentManager.setFragmentResultListener(
            BottomSheetFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { p0, bundle ->
            val categoryId = bundle.getLong(BottomSheetFragment.RESULT_CATEGORY_ID)
            val selected = categoryItems.first { item ->
                item.category.id == categoryId
            }
            viewModel.onCategorySelected(selected.category)
        }
    }


    private fun buildDisplayCategories(
        selected: Category?,
        pinned: List<Category>
    ): List<Category> {
        val isPinned = pinned.any { it.id == selected?.id }
        if (selected == null) {

            return pinned.take(4)

        }
        if (isPinned) {

            return pinned.take(4)
        }

        return buildList {

            add(selected)

            addAll(

                pinned.filter {

                    it.id != selected.id

                }.take(3)

            )

        }

    }


    private fun renderCategories(
        display: List<Category>,
        selectedCategory: Category?
    ) {
        val bindings = listOf(
            firstCategory,
            secondCategory,
            thirdCategory,
            fourthCategory
        )
        bindings.forEachIndexed { index, binding ->
            val category = display[index]

            bindCategory(binding, category)
            updateCategoryItem(binding, category.id == selectedCategory?.id)
        }
    }


    private val foodCategories = Category(
        id = 1,
        name = "food",
        icon = CategoryIcon.FOOD,
        type = CategoryType.EXPENSE,
        isArchived = false,
        isSystem = true,
        isDefault = true,
    )
    private val transportCategory = Category(
        id = 2,
        name = "Transport",
        icon = CategoryIcon.TRANSPORT,
        type = CategoryType.EXPENSE,
        isArchived = false,
        isSystem = true,
        isDefault = true,
    )
    private val salaryCategory = Category(
        id = 3,
        name = "Salary",
        icon = CategoryIcon.SALARY,
        type = CategoryType.INCOME,
        isArchived = false,
        isSystem = true,
        isDefault = true,
    )
    private val cafeCategory = Category(
        id = 4,
        name = "cafe",
        icon = CategoryIcon.CAFE,
        type = CategoryType.EXPENSE,
        isArchived = false,
        isSystem = true,
        isDefault = true,
    )


}