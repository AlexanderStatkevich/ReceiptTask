package com.statkevich.receipttask.dao;

import com.statkevich.receipttask.dao.api.ProductDao;
import com.statkevich.receipttask.dao.singletonfactories.ProductDaoSingleton;
import com.statkevich.receipttask.exceptions.ProductNotExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemoryProductDaoTest {

    ProductDao productDao = ProductDaoSingleton.getInstance();

    @Test
    void throwsExceptionAfterFrontingInGet() {
        Exception exception = assertThrows(ProductNotExistException.class, () -> productDao.get(0L));
        assertEquals("Incorrect id input: "+ 0L, exception.getMessage());
    }
}

