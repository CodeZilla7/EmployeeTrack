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

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.edoubletech.employeetrack.data.Employee;

@Database(entities = {Employee.class}, version = 1, exportSchema = false)
public abstract class EmployeeDatabase extends RoomDatabase {
    
    private static EmployeeDatabase sInstance;
    
    public abstract EmployeeDao employeeDao();
    
    public static EmployeeDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (EmployeeDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeDatabase.class,
                            "employee_database")
                            .build();
                }
            }
        }
        return sInstance;
    }
}
