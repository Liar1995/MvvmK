package com.summer.kbase.binding.viewadapter.spinner;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.summer.kbase.binding.command.BindingCommand;


public class ViewAdapter {
    /**
     * 双向的SpinnerViewAdapter, 可以监听选中的条目,也可以回显选中的值
     *
     * @param spinner        控件本身
     * @param itemDatas      下拉条目的集合
     * @param valueReply     回显的value
     * @param bindingCommand 条目点击的监听
     */
    @BindingAdapter(value = {"itemDatas", "valueReply", "onItemSelectedCommand"}, requireAll = false)
    public static void onItemSelectedCommand(final Spinner spinner, final List<IKeyAndValue> itemDatas, String valueReply, final BindingCommand<IKeyAndValue> bindingCommand) {
        if (itemDatas == null) {
            throw new NullPointerException("this itemDatas parameter is null");
        }
        List<String> lists = new ArrayList<>();
        for (IKeyAndValue iKeyAndValue : itemDatas) {
            lists.add(iKeyAndValue.getKey());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(spinner.getContext(), android.R.layout.simple_spinner_item, lists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IKeyAndValue iKeyAndValue = itemDatas.get(position);
                //将IKeyAndValue对象交给ViewModel
                bindingCommand.execute(iKeyAndValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //回显选中的值
        if (!TextUtils.isEmpty(valueReply)) {
            for (int i = 0; i < itemDatas.size(); i++) {
                IKeyAndValue iKeyAndValue = itemDatas.get(i);
                if (valueReply.equals(iKeyAndValue.getValue())) {
                    spinner.setSelection(i);
                    return;
                }
            }
        }
    }
}
