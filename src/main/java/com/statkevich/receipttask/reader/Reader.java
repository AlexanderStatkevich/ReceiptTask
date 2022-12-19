package com.statkevich.receipttask.reader;

import com.statkevich.receipttask.dto.InputValueDto;

import java.util.List;

public interface Reader {

    InputValueDto read(List<String> orderList);

}