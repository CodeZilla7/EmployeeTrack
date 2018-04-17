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

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.edoubletech.employeetrack.data.Employee;
import com.edoubletech.employeetrack.data.database.EmployeeDao;
import com.edoubletech.employeetrack.data.database.EmployeeDatabase;

import java.util.List;

public class DataRepository {
    
    private static DataRepository sInstance;
    private EmployeeDao mDao;
    private LiveData<List<Employee>> listOfEmployees;
    private AppExecutors mExecutors;
    
    public DataRepository(Application application) {
        EmployeeDatabase database = EmployeeDatabase.getInstance(application);
        this.mExecutors = AppExecutors.getInstance();
        this.mDao = database.employeeDao();
        this.listOfEmployees = mDao.getListOfEmployees();
    }
    
    public static DataRepository getInstance(Application application) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(application);
                }
            }
        }
        return sInstance;
    }
    
    public LiveData<List<Employee>> getListOfEmployees() {
        return listOfEmployees;
    }
    
    
    public void insertEmployee(final Employee... employees) {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDao.insertEmployee(employees);
            }
        });
    }
    
    public void deleteEmployee(final Employee employee) {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDao.deleteEmployee(employee);
            }
        });
    }
    
    public void deleteEmployeeWithId(final int id) {
        mExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDao.deleteSingleEmployee(id);
            }
        });
    }
    
}
