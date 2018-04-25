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

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.edoubletech.employeetrack.data.Employee;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements EmployeeAdapter.ListItemClickListener {
    
    public static final String EMPLOYEE_ID_EXTRA = "EXTRA_EMPLOYEE_ID";
    public static final String EXISTING_EMPLOYEE = "EXISTING EMPLOYEE CATEGORY";
    
    @BindView(R.id.main_activity_recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    
    @Inject
    Factory factory;
    
    MainActivityViewModel viewModel;
    EmployeeAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        
        ((EmployeeTrack) getApplication()).getAppComponent()
                .inject(this);
        
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel.class);
        
        mAdapter = new EmployeeAdapter(this, this);
        
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        
        mRecyclerView.setAdapter(mAdapter);
        
        viewModel.getListOfEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable List<Employee> employees) {
                mAdapter.setEmployees(employees);
            }
        });
        
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }
            
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Employee employee = (Employee) viewHolder.itemView.getTag();
                viewModel.deleteEmployee(employee);
            }
        }).attachToRecyclerView(mRecyclerView);
        
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }
    
    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(MainActivity.this, EditorActivity.class);
        intent.putExtra(EMPLOYEE_ID_EXTRA, clickedItemIndex);
        intent.addCategory(EXISTING_EMPLOYEE);
        startActivity(intent);
    }
}
