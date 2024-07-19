package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityCalculateBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode


class CalculateBmi : AppCompatActivity() {

    companion object{
        private const val METRIC_UNIT_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNIT_VIEW     ="US_UNIT_VIEW"
    }

    private var currentVisibleView:String = METRIC_UNIT_VIEW

    private var binding : ActivityCalculateBmiBinding? = null
    private var toolBmi: Toolbar? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculateBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        toolBmi = findViewById(R.id.toolbarBMI)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title ="CALCULATE BMI"
        }
          toolBmi?.setNavigationOnClickListener { onBackPressed() }


        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId:Int ->
            if (checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsUnitsView()
                binding?.btnCalculate?.setOnClickListener {
                    calculateUnits()
                }
            }
        }



        binding?.btnCalculate?.setOnClickListener {
            if (validateMetricUnits()){
                val heightValue :Float = binding?.etMetricUnitHeight?.text.toString().toFloat() /100
                val weightValue :Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue*heightValue)
                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@CalculateBmi,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNIT_VIEW
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE

        binding?.tilMetricUnitWeightInPounds?.visibility = View.INVISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.INVISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility= View.INVISIBLE

        binding?.etMetricUnitHeight?.text?.clear()
        binding?.etMetricUnitWeight?.text?.clear()
        binding?.llDisplayBmiResult?.visibility = View.GONE
    }

    private fun makeVisibleUsUnitsView(){
        currentVisibleView = US_UNIT_VIEW

        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE

        binding?.tilMetricUnitWeightInPounds?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightInch?.visibility= View.VISIBLE

        binding?.etMetricUnitHeight?.text?.clear()
        binding?.etMetricUnitWeight?.text?.clear()
        binding?.llDisplayBmiResult?.visibility = View.GONE
    }

    private fun displayBMIResult(bmi :Float){
        val bmiLabel:String
        val bmiDescription:String

        if (bmi.compareTo(15f) <=0){
            bmiLabel = "Very severely underweight"
            bmiDescription ="Oops! you really need to take better care of yourself! Eat more! "
        }else if (bmi.compareTo(15f) <=0 && bmi.compareTo(16f) <=0 ){
            bmiLabel = "Severely underweight"
            bmiDescription ="Oops! you really need to take better care of yourself! Eat more! "
        }else if (bmi.compareTo(16f) <=0 && bmi.compareTo(18.5f) <=0 ){
            bmiLabel = "Underweight"
            bmiDescription ="Oops! you really need to take better care of yourself! Eat more! "
        }else if (bmi.compareTo(18.5f) <=0 && bmi.compareTo(25f) <=0 ){
            bmiLabel = "Normal"
            bmiDescription ="Congratulation! you are in a good shape "
        }else if (bmi.compareTo(25f) <=0 && bmi.compareTo(30f) <=0 ){
            bmiLabel = "Overweight"
            bmiDescription ="Oops! you really need to take better care of yourself! Workout!"
        }else if (bmi.compareTo(30f) <=0 && bmi.compareTo(35f) <=0 ){
            bmiLabel = "Obese Class | ( Moderately Class ) "
            bmiDescription ="Oops! you really need to take better care of yourself! Workout!"
        }else if (bmi.compareTo(35f) <=0 && bmi.compareTo(40f) <=0 ){
            bmiLabel = "Obese Class | ( Severely Class )"
            bmiDescription ="Oops! you are in a very dangerous condition! Act now!"
        }else {
            bmiLabel = "Obese Class | ( Very Severely Class )"
            bmiDescription ="Oops! you are in a very dangerous condition! Act now!"
        }


        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBmiResult?.visibility = View.VISIBLE
        binding?.tvBmiValue?.text = bmiValue
        binding?.BmiType?.text =bmiLabel
        binding?.tvBmiDescription?.text =bmiDescription
    }

    private fun calculateUnits(){
        if (currentVisibleView == METRIC_UNIT_VIEW){
            if (validateMetricUnits()){
                val heightValue :Float = binding?.etMetricUnitHeight?.text.toString().toFloat() /100
                val weightValue :Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue*heightValue)
                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@CalculateBmi,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }

        }else{
            if (validateUsUnits()){
                val usUnitHeightValueFeet:String =binding?.etUsMetricUnitHeightFeet?.text.toString()
                val usUnitHeightValueInch:String =binding?.etUsMetricUnitHeightInch?.text.toString()

                val usUnitWeightValue:Float   =binding?.etMetricUnitPounds?.text.toString().toFloat()
                val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12

                val bmi = 703 *(usUnitWeightValue / ( heightValue * heightValue))
                displayBMIResult(bmi)
            }else {
                Toast.makeText(this@CalculateBmi,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateMetricUnits() : Boolean {
        var isValid = true
        if (binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }else if (binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }



    private fun validateUsUnits() : Boolean {
        var isValid = true

        when{
            binding?.etMetricUnitPounds?.text.toString().isEmpty() ->{
                isValid = false
            }
            binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty() -> {
                isValid = false }
            binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty() -> {
                isValid = false  }

        }

        return isValid

    }





}





