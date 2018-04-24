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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edoubletech.employeetrack.data.Employee;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    
    private final ListItemClickListener mListener;
    private Context context;
    private List<Employee> mEmployees;
    
    public EmployeeAdapter(ListItemClickListener listItemClickListener, Context context) {
        this.context = context;
        this.mListener = listItemClickListener;
    }
    
    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.employee_list_item, parent, false);
        return new EmployeeViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        Employee employee = mEmployees.get(position);
        holder.employeeName.setText(employee.getEmployeeName());
        holder.employeeRole.setText(employee.getEmployeeRole());
        holder.itemView.setTag(employee);
    }
    
    @Override
    public int getItemCount() {
        return (mEmployees != null) ? mEmployees.size() : 0;
    }
    
    public void setEmployees(List<Employee> employees) {
        this.mEmployees = employees;
        notifyDataSetChanged();
    }
    
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
    
    class EmployeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.circleImageView)
        CircleImageView employeeImage;
        @BindView(R.id.name_text_view)
        TextView employeeName;
        @BindView(R.id.role_text_view)
        TextView employeeRole;
        
        EmployeeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition() + 1;
            mListener.onListItemClick(position);
        }
    }
}
