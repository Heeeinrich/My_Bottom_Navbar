package com.example.mybottomnavbar

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mybottomnavbar.databinding.FragmentOrderBinding
import java.util.Calendar
import java.util.Locale

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnEdtType.setOnClickListener {
                val action = OrderFragmentDirections.actionOrderFragmentToTypeFragment()
                findNavController().navigate(action)
            }

            findNavController().currentBackStackEntry?.savedStateHandle?.let { handle ->
                handle.getLiveData<String>("type").observe(viewLifecycleOwner) { res ->
                    btnEdtType.setText(res)
                }
            }

            btnBuy.setOnClickListener {
                val selectedType = btnEdtType.text.toString()
                val selectedDate = btnDatePicker.text.toString()
                val selectedTime = btnTimePicker.text.toString()

                Toast.makeText(requireContext(), "Tiket dengan tipe $selectedType \nberhasil dipesan pada $selectedDate $selectedTime", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }

            // Show the DatePickerDialog when clicking a button
            btnDatePicker.setOnClickListener {
                showCalendar()
            }

            // Show the TimePickerDialog when clicking a button
            btnTimePicker.setOnClickListener {
                showTimePicker()
            }
        }
    }

    // Method to show DatePickerDialog
    private fun showCalendar() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create and show the DatePickerDialog
        DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            binding.btnDatePicker.text = selectedDate
            Toast.makeText(requireContext(), "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
        }, year, month, day).show()
    }

    // Method to show TimePickerDialog
    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Create and show the TimePickerDialog
        TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            val selectedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
            binding.btnTimePicker.text = selectedTime
            Toast.makeText(requireContext(), "Selected Time: $selectedTime", Toast.LENGTH_SHORT).show()
        }, hour, minute, true).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
