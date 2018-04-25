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
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.edoubletech.employeetrack.data.Employee;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity {
    
    public static final int IMAGE_PICKER_REQUEST_CODE = 200;
    
    @BindView(R.id.employee_name_text_view)
    EditText employeeName;
    @BindView(R.id.employee_age_text_view)
    EditText employeeAge;
    @BindView(R.id.employee_role_text_view)
    EditText employeeRole;
    @BindView(R.id.save_button)
    Button saveButton;
    @BindView(R.id.image_picker_button)
    Button imagePicker;
    @BindView(R.id.employee_image)
    ImageView employee_image;
    
    @Inject
    Factory factory;
    
    Uri photoUri;
    EditorActivityViewModel viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
    
        ButterKnife.bind(this);
    
        ((EmployeeTrack) getApplication()).getAppComponent()
                .inject(this);
    
        Intent parentIntent = getIntent();
        
        viewModel = ViewModelProviders.of(this, factory).get(EditorActivityViewModel.class);
        
        if (parentIntent.hasCategory(MainActivity.EXISTING_EMPLOYEE)) {
            int id = parentIntent.getIntExtra(MainActivity.EMPLOYEE_ID_EXTRA, -1);
            Toast.makeText(this, String.valueOf(id), Toast.LENGTH_SHORT).show();
            Employee employee = viewModel.getEmployee(id);
            if (employee != null) {
                employeeRole.setText(employee.getEmployeeRole());
                employeeName.setText(employee.getEmployeeName());
                employeeAge.setText(String.valueOf(employee.getEmployeeAge()));
            }
        }
        
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();
            }
        });
        
        imagePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();
            }
        });
    }
    
    public void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Choose an image for the employee"),
                IMAGE_PICKER_REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE_PICKER_REQUEST_CODE:
                Log.v("REQUEST CODE", Integer.toString(IMAGE_PICKER_REQUEST_CODE));
                if (resultCode == RESULT_OK && data != null) {
                    employee_image.setVisibility(View.VISIBLE);
                    photoUri = data.getData();
                    Glide.with(EditorActivity.this).load(photoUri).into(employee_image);
                    break;
                }
        }
    }
    
    private void saveEmployee() {
        if (employeeName.getText().toString().isEmpty() || employeeAge.getText().toString
                ().isEmpty() || employeeRole.getText().toString().isEmpty()) {
            Toast.makeText(EditorActivity.this, "You must provide valid details",
                    Toast.LENGTH_SHORT).show();
        } else {
            String name = employeeName.getText().toString().trim();
            String stringAge = employeeAge.getText().toString().trim();
            int age = Integer.parseInt(stringAge);
            String role = employeeRole.getText().toString().trim();
            Employee employee = new Employee(name, role, age);
            viewModel.insertEmployee(employee);
            Intent replyIntent = new Intent(EditorActivity.this, MainActivity.class);
            startActivity(replyIntent);
            finish();
        }
    }
    
}