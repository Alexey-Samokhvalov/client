package com.example.client1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> extends BaseResponse {
    private T data;

    public DataResponse(boolean success, String message, T data) {
        super(success, message);
        this.data = data;
    }
}
