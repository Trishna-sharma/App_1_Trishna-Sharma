package com.example.bmicalculator
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn=findViewById<Button>(R.id.alrt_btn)
        alrt_btn.setOnClickListener(){
        val dialogbinding=layoutInflater.inflate(R.layout.custom_dialog,null)
            val myDialog=Dialog(this)
            myDialog.setContentView(dialogbinding)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()

          val yesbtn=dialogbinding.findViewById<Button>(R.id.alert_yes)
            yesbtn.setOnClickListener(){
                myDialog.dismiss()
            }
        }

        button_calculate.setOnClickListener(){
            if(Height.text.isNotEmpty()&&Weight.text.isNotEmpty()){
                val height=(Height.text.toString()).toInt()
                val weight=(Weight.text.toString()).toInt()
                val BMI=calculateBMI(height,weight)
                bmi.text="Your BMI is "+ BMI.toString()
                bmi.visibility=View.VISIBLE

                if(BMI < 18.5){
                    status.text="UNDER WEIGHT"
                }else if(BMI >= 18.5 && BMI < 24.9){
                    status.text="HEALTHY"
                }else if(BMI >= 24.9 && BMI < 30 ){
                    status.text="OVER WEIGHT"
                }else if(BMI >= 30 ){
                    status.text="SUFFERING FROM OBESITY"
                }
                else{
                    Toast.makeText(this,"Please Enter the valid height and weight",Toast.LENGTH_SHORT).show()
                }
            }
                ReCalculate.setOnClickListener(){
                    ResetEverything()
                }
                ReCalculate.visibility=View.VISIBLE
                status.visibility=View.VISIBLE
            }
        }
            //reset everything
            private fun ResetEverything (){
                button_calculate.visibility=View.VISIBLE
                ReCalculate.visibility=View.VISIBLE
                Height.text.clear()
                Weight.text.clear()
                status.text=" "
                bmi.text=" "
            }
            // BMI Calculation
            private fun calculateBMI(height:Int,weight:Int):Float{
                val Height_metre=height.toFloat()/100
                val BMI=weight.toFloat()/(Height_metre * Height_metre)
                return BMI
            }
}