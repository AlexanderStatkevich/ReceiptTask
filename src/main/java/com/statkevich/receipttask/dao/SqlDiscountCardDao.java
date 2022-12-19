package com.statkevich.receipttask.dao;

import com.statkevich.receipttask.dao.api.DiscountCardDao;
import com.statkevich.receipttask.domain.DiscountCard;
import com.statkevich.receipttask.exceptions.DataAccessException;
import com.statkevich.receipttask.util.DataSourceHolder;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlDiscountCardDao implements DiscountCardDao {
    private static final String READ_BY_NUMBER_QUERY = """
            SELECT card_number,discount
            from discount_cards
            where card_number=?;""";
    DataSource dataSource = DataSourceHolder.getDataSource();

    @Override
    public DiscountCard get(String cardNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_NUMBER_QUERY);
             ResultSet resultSet = readByIdInternal(preparedStatement, cardNumber)) {
            resultSet.next();
            return buildEntity(resultSet);
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readById method :" + e);
        }
    }

    protected ResultSet readByIdInternal(PreparedStatement preparedStatement, String cardNumber) throws SQLException {
        preparedStatement.setString(1, cardNumber);
        return preparedStatement.executeQuery();
    }

    protected DiscountCard buildEntity(ResultSet resultSet) throws SQLException {
        String cardNumber = resultSet.getString("card_number");
        BigDecimal discount = resultSet.getBigDecimal("discount");

        return new DiscountCard(cardNumber,discount);
    }
}
