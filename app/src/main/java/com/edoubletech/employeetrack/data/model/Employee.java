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

package com.edoubletech.employeetrack.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Employee {
    
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int employeeId;
    
    private String employeeName;
    private String employeeRole;
    private int employeeAge;
    
    public Employee(String employeeName, String employeeRole, int employeeAge) {
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeeAge = employeeAge;
    }
    
    @NonNull
    public int getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(@NonNull int employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public String getEmployeeRole() {
        return employeeRole;
    }
    
    public int getEmployeeAge() {
        return employeeAge;
    }
    
}
