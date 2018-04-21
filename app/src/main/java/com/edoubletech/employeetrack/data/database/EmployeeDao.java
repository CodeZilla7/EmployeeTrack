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

package com.edoubletech.employeetrack.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.edoubletech.employeetrack.data.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    
    @Query("SELECT * FROM employee")
    LiveData<List<Employee>> getListOfEmployees();
    
    @Query("DELETE FROM employee WHERE employeeId = :employeeId")
    void deleteSingleEmployee(int employeeId);
    
    @Query("SELECT * FROM employee WHERE employeeId = :employeeId")
    LiveData<Employee> getAnEmployee(int employeeId);
    
    @Insert
    void insertEmployee(Employee employees);
    
    @Query("UPDATE employee SET employeeName = :name, employeeRole = :role,  photoPath = :path, " +
            "employeeAge = :age")
    void updateEmployee(String name, String role, String path, int age);
    
    @Update
    void update(Employee employee);
    
    @Delete
    void deleteEmployee(Employee employee);
}
