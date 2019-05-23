package com.hauphvnjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.hauphvnjavaweb.dao.GenericDAO;
import com.hauphvnjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
			Class.forName(resourceBundle.getString("driverName"));
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameter) {
		List<T> resultList = new ArrayList<T>();
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(sql);
				setParemeter(preparedStatement, parameter);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					resultList.add(rowMapper.mapRow(resultSet));
				}
				return resultList;
			} catch (SQLException e) {
				return null;
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (Exception e2) {
					return null;
				}
			}
		}
		return null;// Can not connection
	}

	private void setParemeter(PreparedStatement preparedStatement, Object... parameter) {
		try {
			int paraLength = parameter.length;
			int index = 0;
			for (int i = 0; i < paraLength; i++) {
				index = i + 1;
				Object parameterObj = parameter[i];
				if (parameterObj instanceof Long) {
					preparedStatement.setLong(index, (Long) parameterObj);
				} else if (parameterObj instanceof String) {
					preparedStatement.setString(index, (String) parameterObj);
				}else if (parameterObj instanceof Timestamp) {
					preparedStatement.setTimestamp(index, (Timestamp)parameterObj);
				}else if (parameterObj instanceof Integer) {
					preparedStatement.setInt(index, (Integer)parameterObj);
				} 
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			setParemeter(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if(connection!= null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e2) {
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Long resultId = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParemeter(preparedStatement, parameters);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				resultId = resultSet.getLong(1);
				connection.commit();
			}
			return resultId;
		} catch (SQLException e) {
			if(connection!= null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e2) {
				return null;
			}
		}
	}

	@Override
	public int count(String sql, Object... parameters) {
		int items = 0;
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				preparedStatement = connection.prepareStatement(sql);
				setParemeter(preparedStatement, parameters);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					items = resultSet.getInt(1);
				}
				return items;
			} catch (SQLException e) {
				return 0;
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (Exception e2) {
					return 0;
				}
			}
		}
		return items;// Can not connection
	}
}
