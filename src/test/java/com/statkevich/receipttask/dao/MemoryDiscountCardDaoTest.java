package com.statkevich.receipttask.dao;

import com.statkevich.receipttask.dao.api.DiscountCardDao;
import com.statkevich.receipttask.dao.singletonfactories.DiscountCardDaoSingleton;
import com.statkevich.receipttask.exceptions.DiscountCardNotExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryDiscountCardDaoTest {

    DiscountCardDao memoryDiscountCardDao = DiscountCardDaoSingleton.getInstance();

    @Test
    void throwsExceptionAfterFrontingInGet() {
        Exception exception = assertThrows(DiscountCardNotExistException.class, () -> memoryDiscountCardDao.get(null));
        assertEquals("Incorrect card number input", exception.getMessage());
    }
}
    
