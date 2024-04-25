package com.example.client1.service;

import com.example.client1.entity.AuthorEntity;
import com.example.client1.response.BaseResponse;
import com.example.client1.response.DataResponse;
import com.example.client1.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.awt.print.Book;
import java.lang.reflect.Type;
import java.util.Comparator;

public class AuthorService {

    @Getter
    public ObservableList<AuthorEntity> data = FXCollections.observableArrayList();
    private final HttpService http = new HttpService();
    JsonService service = new JsonService();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResponse<AuthorEntity>>() {
    }.getType();
    private Type listType = new TypeToken<ListResponse<AuthorEntity>>() {
    }.getType();

    public void getAll() {
        ListResponse<AuthorEntity> data = new ListResponse<>();
        data = service.getObject(http.get(prop.getAllAuthor()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            this.data.forEach(System.out::println);
            sort();
        }else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(AuthorEntity data) {
        String temp = http.post(prop.getSaveAuthor(), service.getJson(data));

        DataResponse<AuthorEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(AuthorEntity after, AuthorEntity before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateAuthor(), service.getJson(after));
        DataResponse<AuthorEntity> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(AuthorEntity data) {
        String temp = http.delete(prop.getDeleteAuthor(), data.getId());
        BaseResponse response = service.getObject(temp, BaseResponse.class);
        if(response.isSuccess()) {
            this.data.remove(data);
            sort();
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void findById(AuthorEntity data) {
        String temp = http.get(prop.getFineByIdAuthor()+data.getId());
        DataResponse<AuthorEntity>response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
        }else {
            throw new RuntimeException((response.getMessage()));
        }
    }

    private void sort() {
        data.sort(Comparator.comparing(AuthorEntity::getLastname));
    }
}
