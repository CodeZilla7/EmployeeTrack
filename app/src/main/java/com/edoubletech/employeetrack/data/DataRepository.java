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

package com.edoubletech.employeetrack.data;

import android.arch.lifecycle.LiveData;

import com.edoubletech.employeetrack.data.database.EmployeeDao;
import com.edoubletech.employeetrack.data.model.Employee;

import java.util.List;

import javax.inject.Inject;

public class DataRepository {

    private EmployeeDao mDao;

    @Inject
    public DataRepository(EmployeeDao dao) {
        this.mDao = dao;
    }

    public LiveData<List<Employee>> getListOfEmployees() {
        return mDao.getListOfEmployees();
    }

    public Employee getEmployeeById(int employeeId) {
        return mDao.getAnEmployee(employeeId);
    }

    public void insertEmployee(Employee employees) {
        mDao.insertEmployee(employees);
    }

    public void deleteEmployee(Employee employee) {
        mDao.deleteEmployee(employee);
    }

    public void update(Employee employee) {
        mDao.update(employee);
    }

    public void deleteEmployeeWithId(int id) {
        mDao.deleteSingleEmployee(id);
    }

}
