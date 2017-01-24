package assignment.android.acadgild.nav;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import assignment.android.acadgild.R;
/**
 * Created by DivyaVipin on 1/16/2017.
 */

public class BMICalculator  extends AppCompatActivity{

    Button btnCalculate;
    EditText ediTextWeight;
    EditText editTextHeight;
    TextView txtViewHeight;
    TextView txtViewWeight;
    Float height,weight;
    TextView value;
    TextView message;
    Dialog d;
    Button btnOK,btnClear;
    float height_value;
    float  weight_value;
    float bmi_Value;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_calculator);
        btnCalculate=(Button)findViewById(R.id.bnCalculate);
        btnClear=(Button)findViewById(R.id.btnClear);
        ediTextWeight=(EditText)findViewById(R.id.editTextWeight);
        editTextHeight=(EditText)findViewById(R.id.editTextHeight);
        txtViewHeight=(TextView)findViewById(R.id.textViewHeight);
        txtViewWeight=(TextView)findViewById(R.id.textViewWeight);
        String heightValue=editTextHeight.getText().toString();
        try {
            height_value = Float.parseFloat(heightValue);
        }
        catch(NumberFormatException ex) {
            height_value = 0; // default ??
        }
        String weightValue=ediTextWeight.getText().toString();
      // float  weight_value=Float.parseFloat(weightValue);
        try {
            weight_value = Float.parseFloat(heightValue);
        }
        catch(NumberFormatException ex) {
            weight_value = 0; // default ??
        }
           bmi_Value=calculateBMI(height_value,weight_value);

        final String interpretationText=interpetBMI(bmi_Value);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d=new Dialog(BMICalculator.this);
                d.setTitle("BMI Result");
                d.setContentView(R.layout.bmi_dialog);
                value= (TextView) d.findViewById(R.id.textViewValue);
                message=(TextView) d.findViewById(R.id.textViewMessage);

                value.setText(""+bmi_Value);
                message.setText(interpretationText);
                btnOK=(Button)d.findViewById(R.id.btnOK);
                d.show();
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                    }
                });

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ediTextWeight.setText("");
                editTextHeight.setText("");

            }
        });

    }
    public float calculateBMI(float height,float weight)
    {

        return (float) (weight * 4.88 / (height * height));

    }
    public String interpetBMI(float bmiValue)
    {



            if (bmiValue < 16) {
                return "Severely underweight";
            } else if (bmiValue < 18.5) {

                return "Underweight";
            } else if (bmiValue < 25) {

                return "Normal";
            } else if (bmiValue < 30) {

                return "Overweight";
            } else {
                return "Obese";
            }

        }


}
