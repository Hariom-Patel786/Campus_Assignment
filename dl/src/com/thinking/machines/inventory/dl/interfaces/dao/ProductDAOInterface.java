package com.thinking.machines.inventory.dl.interfaces.dao;
import java.util.*;
import com.thinking.machines.inventory.dl.exceptions.*;
import com.thinking.machines.inventory.dl.interfaces.dto.*;
public interface ProductDAOInterface
{
public void addProduct(ProductDTOInterface productDTO) throws DAOException;
public void updateProduct(ProductDTOInterface productDTO) throws DAOException;
public void delete(int code) throws DAOException;
public Set<ProductDTOInterface> getAll() throws DAOException;
public ProductDTOInterface getByProductId(int productId) throws DAOException;
public ProductDTOInterface getByProductName(String productName) throws DAOException;
public void addProductToStock(ProductDTOInterface productDTO) throws DAOException;
public void dispatchOrder(ProductDTOInterface productDTO) throws DAOException;
public void checkAndNotify(ProductDTOInterface productDTO) throws DAOException;
}