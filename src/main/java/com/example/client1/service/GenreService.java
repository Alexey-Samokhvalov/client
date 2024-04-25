package com.example.client1.service;

import com.example.client1.entity.CityEntity;
import com.example.client1.entity.GenreEntity;
import com.example.client1.response.BaseResponse;
import com.example.client1.response.DataResponse;
import com.example.client1.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;

public class GenreService {

    @Getter

    public ObservableList<GenreEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<GenreEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<GenreEntity>>() {
    }.getType();

    public void getAll() {
        ListResponse<GenreEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllGenre()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
            sort();
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(GenreEntity data) {
        String temp = http.post(prop.getSaveGenre(), service.getJson(data));
        DataResponse<GenreEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(GenreEntity after, GenreEntity before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateGenre(), service.getJson(after));
        DataResponse<GenreEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(GenreEntity data) {
        String temp = http.delete(prop.getDeleteGenre(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if(response.isSuccess()) {
            this.data.remove(data);
            sort();
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(GenreEntity data) {
        String temp = http.get(prop.getFineByIdGenre()+data.getId());
        DataResponse<GenreEntity>response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        }else {
            throw new RuntimeException((response.getMessage()));
        }
    }
    private void sort() {
        data.sort(Comparator.comparing(GenreEntity::getTitle));
    }
}

