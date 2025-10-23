import com.thinking.machines.inventory.dl.dao.*;
import com.thinking.machines.inventory.dl.dto.*;
import com.thinking.machines.inventory.dl.interfaces.dto.*;
import com.thinking.machines.inventory.dl.interfaces.dao.*;
import com.thinking.machines.inventory.dl.exceptions.*;
import java.util.*;
import java.io.*;
class ProductTestCase
{
public static void main(String gg[])
{
Scanner sc=new Scanner(System.in);
try
{
ProductDAOInterface productDAO=new ProductDAO();

while (true)
{
System.out.println("\n=== Warehouse Inventory Menu ===");
System.out.println("1. Add Product");
System.out.println("2. Delete Product");
System.out.println("3. Update Product");
System.out.println("4. Purchase/Receive Shipment");
System.out.println("5. Dispatch Order");
System.out.println("6. Show All Products");
System.out.println("7. Exit");
System.out.print("Enter your choice: ");
int choice = sc.nextInt();
sc.nextLine();
switch (choice)
{
case 1:
System.out.print("Enter Product ID: ");
int addId = sc.nextInt();
sc.nextLine();
System.out.print("Enter Product Name: ");
String addName = sc.nextLine();
System.out.print("Enter Quantity: ");
int addQty = sc.nextInt();

ProductDTOInterface newProduct = new ProductDTO();
newProduct.setProductId(addId);
newProduct.setProductName(addName);
newProduct.setProductQuantity(addQty);

productDAO.addProduct(newProduct);
System.out.println("Product added successfully!");
break;

case 2:
System.out.print("Enter Product ID to delete: ");
int delId = sc.nextInt();
productDAO.delete(delId);
System.out.println("Product deleted successfully!");
break;

case 3:
System.out.print("Enter Product ID to update: ");
int upId = sc.nextInt();
sc.nextLine();
System.out.print("Enter New Product Name: ");
String upName = sc.nextLine();
System.out.print("Enter New Quantity: ");
int upQty = sc.nextInt();

ProductDTOInterface updateProduct = new ProductDTO();
updateProduct.setProductId(upId);
updateProduct.setProductName(upName);
updateProduct.setProductQuantity(upQty);

productDAO.updateProduct(updateProduct);
System.out.println("Product updated successfully!");
break;

case 4:
System.out.print("Enter Product ID to add stock: ");
int stockId = sc.nextInt();
System.out.print("Enter quantity to add: ");
int stockQty = sc.nextInt();

ProductDTOInterface stockProduct = new ProductDTO();
stockProduct.setProductId(stockId);
stockProduct.setProductQuantity(stockQty);

productDAO.addProductToStock(stockProduct);
System.out.println("Stock updated successfully!");
break;

case 5:
System.out.print("Enter Product ID to dispatch: ");
int dispatchId = sc.nextInt();
System.out.print("Enter quantity to dispatch: ");
int dispatchQty = sc.nextInt();

ProductDTOInterface dispatchProduct = new ProductDTO();
dispatchProduct.setProductId(dispatchId);
dispatchProduct.setProductQuantity(dispatchQty);

productDAO.dispatchOrder(dispatchProduct);
System.out.println("Order dispatched successfully!");
break;

case 6:
Set<ProductDTOInterface> all = productDAO.getAll();
System.out.println("\n=== All Products ===");
for (ProductDTOInterface p : all)
{
System.out.println("ID: " + p.getProductId() + " | Name: " + p.getProductName() + " | Qty: " + p.getProductQuantity());
}
break;
case 7:
System.out.println("Exiting... ");
return;

default:
System.out.println("Invalid choice, please try again.");
}
}
}catch(DAOException daoException)
{
System.out.println("Error : "+daoException);
}
}
}