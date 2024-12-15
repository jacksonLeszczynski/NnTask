package com.nationale.account.nnTask.common.domain.vo;

import java.util.List;

public interface Response <T>{
    T getResponseObject();
    ResponseCodeStatus getResponseStatus();
    List<String> getErrorMessages();
}
