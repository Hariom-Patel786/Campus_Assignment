package com.thinking.machines.inventory.dl.dto;
import com.thinking.machines.inventory.dl.interfaces.dto.*;
public class ProductDTO implements ProductDTOInterface
{
private int productId;
private String name;
private int quantity;
public ProductDTO()
{
this.productId=0;
this.name="";
this.quantity=0;
}
public void setProductId(int productId)
{
this.productId=productId;
}
public int getProductId()
{
return this.productId;
}
public void setProductName(String name)
{
this.name=name;
}
public String getProductName()
{
return this.name;
}
public void setProductQuantity(int quantity)
{
this.quantity=quantity;
}
public int getProductQuantity()
{
return this.quantity;
}
public boolean equals(Object other)
{
if(!(other instanceof ProductDTOInterface)) return false;
ProductDTOInterface productDTO;
productDTO=(ProductDTO)other;
return this.productId==productDTO.getProductId();
}
public int compareTo(ProductDTOInterface productDTO)
{
return this.productId-productDTO.getProductId();
}
public int hashCode()
{
return productId;
}
}