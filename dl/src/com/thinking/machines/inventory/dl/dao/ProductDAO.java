package com.thinking.machines.inventory.dl.dao;
import com.thinking.machines.inventory.dl.interfaces.dto.*;
import com.thinking.machines.inventory.dl.interfaces.dao.*;
import com.thinking.machines.inventory.dl.exceptions.*;
import com.thinking.machines.inventory.dl.dto.*;
import java.util.*;
import java.io.*;
public class ProductDAO implements ProductDAOInterface
{
private Map<Integer, ProductDTOInterface> idWiseProductMap;
private Map<String, ProductDTOInterface> nameWiseProductMap;
private Set<ProductDTOInterface> productSet;

public ProductDAO() throws DAOException
{
populateDataStructures();
}
private void populateDataStructures() throws DAOException
{
idWiseProductMap = new HashMap<>();
nameWiseProductMap = new HashMap<>();
productSet = new TreeSet<>();
}
public void addProduct(ProductDTOInterface productDTO) throws DAOException
{
if(productDTO == null) throw new DAOException("Product is null.");
if(idWiseProductMap.containsKey(productDTO.getProductId()))
throw new DAOException("Product ID already exists.");
if(nameWiseProductMap.containsKey(productDTO.getProductName().toUpperCase()))
throw new DAOException("Product name already exists.");

ProductDTOInterface newProduct = new ProductDTO();
newProduct.setProductId(productDTO.getProductId());
newProduct.setProductName(productDTO.getProductName());
newProduct.setProductQuantity(productDTO.getProductQuantity());

idWiseProductMap.put(newProduct.getProductId(), newProduct);
nameWiseProductMap.put(newProduct.getProductName().toUpperCase(), newProduct);
productSet.add(newProduct);
}
public void updateProduct(ProductDTOInterface productDTO) throws DAOException
{
if(productDTO == null) throw new DAOException("Product is null.");
if(!idWiseProductMap.containsKey(productDTO.getProductId())) throw new DAOException("Invalid Product ID.");

ProductDTOInterface existing = idWiseProductMap.get(productDTO.getProductId());
nameWiseProductMap.remove(existing.getProductName().toUpperCase());
productSet.remove(existing);

existing.setProductName(productDTO.getProductName());
existing.setProductQuantity(productDTO.getProductQuantity());

nameWiseProductMap.put(existing.getProductName().toUpperCase(), existing);
productSet.add(existing);
}

public void delete(int code) throws DAOException
{
if(!idWiseProductMap.containsKey(code))
throw new DAOException("Product not found.");

ProductDTOInterface product = idWiseProductMap.get(code);
idWiseProductMap.remove(code);
nameWiseProductMap.remove(product.getProductName().toUpperCase());
productSet.remove(product);
}
public Set<ProductDTOInterface> getAll() throws DAOException
{
return new TreeSet<>(productSet);
}
public ProductDTOInterface getByProductId(int productId) throws DAOException
{
if(!idWiseProductMap.containsKey(productId))
throw new DAOException("Product ID not found.");
return idWiseProductMap.get(productId);
}
public ProductDTOInterface getByProductName(String productName) throws DAOException
{
if(!nameWiseProductMap.containsKey(productName.toUpperCase()))
throw new DAOException("Product name not found.");
return nameWiseProductMap.get(productName.toUpperCase());
}
public void addProductToStock(ProductDTOInterface productDTO) throws DAOException
{
if(productDTO == null) throw new DAOException("Product is null.");
if(!idWiseProductMap.containsKey(productDTO.getProductId()))
throw new DAOException("Invalid product ID.");

ProductDTOInterface product = idWiseProductMap.get(productDTO.getProductId());
int newQty = product.getProductQuantity() + productDTO.getProductQuantity();
product.setProductQuantity(newQty);
}
public void dispatchOrder(ProductDTOInterface productDTO) throws DAOException
{
if(productDTO == null) throw new DAOException("Product is null.");
if(!idWiseProductMap.containsKey(productDTO.getProductId()))
throw new DAOException("Invalid product ID.");

ProductDTOInterface product = idWiseProductMap.get(productDTO.getProductId());
int currentQty = product.getProductQuantity();
int reduceBy = productDTO.getProductQuantity();

if(reduceBy > currentQty)
throw new DAOException("Insufficient stock to fulfill order.");

int updatedQty = currentQty - reduceBy;
product.setProductQuantity(updatedQty);

// Check threshold
checkAndNotify(product);
}
public void checkAndNotify(ProductDTOInterface productDTO) throws DAOException
{
int threshold = 5; // Predefined reorder threshold
if(productDTO.getProductQuantity() < threshold)
{
System.out.println("Restock Alert: Low stock for " + productDTO.getProductName() + " – only " + productDTO.getProductQuantity() + " left!");
}
}

}