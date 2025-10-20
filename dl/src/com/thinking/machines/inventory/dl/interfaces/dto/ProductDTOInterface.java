package com.thinking.machines.inventory.dl.interfaces.dto;
public interface ProductDTOInterface extends Comparable<ProductDTOInterface>,java.io.Serializable
{
public void setProductId(int productId);
public int getProductId();
public void setProductName(String name);
public String getProductName();
public void setProductQuantity(int quantity);
public int getProductQuantity();
}