package com.nationale.account.nnTask.common.domain.vo;

public interface RestClientService {
    <T> T getDataFromApi(String endpoint);
}
