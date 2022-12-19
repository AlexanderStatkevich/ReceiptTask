package com.statkevich.receipttask.dao;

import com.statkevich.receipttask.dao.api.ProductDao;
import com.statkevich.receipttask.domain.CommonProduct;
import com.statkevich.receipttask.domain.SaleType;
import com.statkevich.receipttask.exceptions.DataAccessException;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SqlProductDao implements ProductDao {

    private static final String READ_BY_ID_QUERY = """
            SELECT id,name,price,sale_types
            from products
            where id=?;""";
    DataSource dataSource = DataSourceHolder.getDataSource();

    @Override
    public CommonProduct get(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID_QUERY);
             ResultSet resultSet = readByIdInternal(preparedStatement, id)) {
             resultSet.next();
            return buildEntity(resultSet);
        } catch (SQLException e) {
            throw new DataAccessException("SQLException readById method :" + e);
        }
    }

    protected ResultSet readByIdInternal(PreparedStatement preparedStatement, Long id) throws SQLException {
        preparedStatement.setLong(1, id);
        return preparedStatement.executeQuery();
    }

    protected CommonProduct buildEntity(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        BigDecimal price = resultSet.getBigDecimal("price");
        String[] saleTypesArray  = (String[])resultSet.getArray("sale_types").getArray();
        Set<SaleType> saleTypes = Arrays.stream(saleTypesArray)
                .map(SaleType::valueOf)
                .collect(Collectors.toSet());

        return CommonProduct.builder()
                .setId(id)
                .setName(name)
                .setPrice(price)
                .setSaleType(saleTypes)
                .build();
    }
}
