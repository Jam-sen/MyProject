package com.ys.callable.service;

import com.ys.callable.domain.ResValue;

import java.util.concurrent.Callable;

public interface CallableService {
    ResValue<Object> searchBigData();
}
