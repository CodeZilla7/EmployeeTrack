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

package com.edoubletech.employeetrack.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.edoubletech.employeetrack.ui.main.MainActivityViewModel;
import com.edoubletech.employeetrack.data.DataRepository;
import com.edoubletech.employeetrack.ui.editor.EditorActivityViewModel;

import javax.inject.Inject;

public class Factory extends ViewModelProvider.NewInstanceFactory {
    
    DataRepository repository;
    
    @Inject
    public Factory(DataRepository repository) {
        this.repository = repository;
    }
    
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            return (T) new MainActivityViewModel(repository);
        } else if (modelClass.isAssignableFrom(EditorActivityViewModel.class)) {
            return (T) new EditorActivityViewModel(repository);
        } else throw new IllegalArgumentException("Wrong view model");
    }
}
