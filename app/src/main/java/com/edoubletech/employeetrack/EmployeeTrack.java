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

import com.edoubletech.employeetrack.dagger.AppComponent;
import com.edoubletech.employeetrack.dagger.AppModule;
import com.edoubletech.employeetrack.dagger.DaggerAppComponent;

public class EmployeeTrack extends Application {
    
    private static AppComponent appComponent;
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
    
    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
