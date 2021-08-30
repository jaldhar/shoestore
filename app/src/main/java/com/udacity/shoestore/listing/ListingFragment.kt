package com.udacity.shoestore.listing

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.CardShoeBinding
import com.udacity.shoestore.databinding.FragmentListingBinding

/**
 * A fragment representing a list of Items.
 */
class ListingFragment : Fragment() {

    private lateinit var binding: FragmentListingBinding
    private val viewModel: ListingViewModel by activityViewModels()

    companion object {
        fun newInstance() = ListingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)
        binding.addShoeButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_listingFragment_to_detailsFragment)
        )

        viewModel.isEmpty.observe(viewLifecycleOwner, {
            binding.noShoesText.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.shoeList.observe(viewLifecycleOwner, { shoes ->
            shoes.forEach { shoe ->
                val cardShoeBinding: CardShoeBinding = DataBindingUtil.inflate(inflater, R.layout.card_shoe, container, false)
                cardShoeBinding.shoe = shoe
                binding.shoeList.addView(cardShoeBinding.root)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) ||
                super.onOptionsItemSelected(item)
    }
}
