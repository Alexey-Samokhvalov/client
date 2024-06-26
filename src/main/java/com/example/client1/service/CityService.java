package com.example.client1.service;

import com.example.client1.entity.AuthorEntity;
import com.example.client1.entity.CityEntity;
import com.example.client1.response.BaseResponse;
import com.example.client1.response.DataResponse;
import com.example.client1.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class CityService {

    @Getter

    public ObservableList<CityEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<CityEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<CityEntity>>() {
    }.getType();

    public void getAll() {
        ListResponse<CityEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllCity()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
            sort();
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(CityEntity data) {
        String temp = http.post(prop.getSaveCity(), service.getJson(data));
        DataResponse<CityEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(CityEntity after, CityEntity before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateCity(), service.getJson(after));
        DataResponse<CityEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(CityEntity data) {
        String temp = http.delete(prop.getDeleteCity(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if(response.isSuccess()) {
            this.data.remove(data);
            sort();
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(CityEntity data) {
        String temp = http.get(prop.getFineByIdCity()+data.getId());
        DataResponse<CityEntity>response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        }else {
            throw new RuntimeException((response.getMessage()));
        }
    }

    private void sort() {
        data.sort(Comparator.comparing(CityEntity::getTitle));
    }
}
