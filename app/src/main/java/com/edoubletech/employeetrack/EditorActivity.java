/*
 *  Copyright (C) 2018 Eton Otieno Oboch
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.edoubletech.employeetrack;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edoubletech.employeetrack.data.Employee;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity {
    
    @BindView(R.id.employee_name_text_view)
    EditText employeeName;
    @BindView(R.id.employee_age_text_view)
    EditText employeeAge;
    @BindView(R.id.employee_role_text_view)
    EditText employeeRole;
    @BindView(R.id.save_button)
    Button saveButton;
    
    EditorActivityViewModel viewModel;
    
    public static final String EXTRA_NAME = "com.edoubletech.employeetrack.EXTRA_NAME";
    public static final String EXTRA_AGE = "com.edoubletech.employeetrack.EXTRA_AGE";
    public static final String EXTRA_ROLE = "com.edoubletech.employeetrack.EXTRA_ROLE";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        ButterKnife.bind(this);
        
        viewModel = ViewModelProviders.of(this).get(EditorActivityViewModel.class);
        
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = employeeName.getText().toString().trim();
                String stringAge = employeeAge.getText().toString().trim();
                int age = Integer.parseInt(stringAge);
                String role = employeeRole.getText().toString().trim();
                
                if (name.isEmpty() && stringAge.isEmpty() && role.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must provide valid details",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Employee employee = new Employee(name, role, age);
                    viewModel.insertEmployee(employee);
                    Intent replyIntent = new Intent(EditorActivity.this, MainActivity.class);
                    startActivity(replyIntent);
                }
            }
        });
    }
    
    
}
