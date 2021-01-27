package com.example.stockbittest.ui

import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.stockbittest.R
import com.example.stockbittest.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupSpannableText()

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun setupToolbar() {
        binding.toolbarTop.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_chevron_left_24)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarTop)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = null
    }

    private fun setupSpannableText() {
        val belumPunyaAkunText = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml("Belum punya akun? <font color='#3eab6a'>Daftar sekarang</font>", Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml("Belum punya akun? <font color='#3eab6a'>Daftar sekarang</font>")
        }
        binding.textBelumPunyaAkun.text = belumPunyaAkunText
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_login, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                requireActivity().finish()
                true
            }
            R.id.menu_contact_support -> {
                Toast.makeText(requireContext(), "Contact Support", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}