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

package com.edoubletech.employeetrack.dagger;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.edoubletech.employeetrack.AppExecutors;
import com.edoubletech.employeetrack.Factory;
import com.edoubletech.employeetrack.data.DataRepository;
import com.edoubletech.employeetrack.data.database.EmployeeDao;
import com.edoubletech.employeetrack.data.database.EmployeeDatabase;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    
    private Context mContext;
    
    public AppModule(Context context) {
        this.mContext = context;
    }
    
    @Singleton
    @Provides
    AppExecutors provideAppExecutors() {
        return new AppExecutors(Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                new AppExecutors.MainThreadExecutor());
    }
    
    @Singleton
    @Provides
    DataRepository provideDataRepository(AppExecutors executors, EmployeeDao dao) {
        return new DataRepository(dao, executors);
    }
    
    @Singleton
    @Provides
    Factory provideFactory(DataRepository repository) {
        return new Factory(repository);
    }
    
    @Singleton
    @Provides
    EmployeeDatabase provideDatabase() {
        return Room.databaseBuilder(mContext.getApplicationContext(),
                EmployeeDatabase.class,
                "employee_database")
                .allowMainThreadQueries()
                .build();
    }
    
    @Singleton
    @Provides
    EmployeeDao provideEmployeeDao(EmployeeDatabase database) {
        return database.employeeDao();
    }
}
