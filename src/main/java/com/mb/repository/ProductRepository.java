package com.mb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.mb.entity.Product;
import com.mb.model.ProductModel;

public interface ProductRepository extends JpaRepository<Product, Long>
{
	@Query("SELECT p FROM Product p WHERE CONCAT(p.productname, p.productcategory, p.productbrand) LIKE %?1%")
	public List<Product> search(String keyword);

	@Query(value = "SELECT * FROM product_list  WHERE product_price BETWEEN(?1) AND (?2)", nativeQuery = true)
	public List<Product> filterByPriceRange(int max, int min);
	// @Query("SELECT p FROM Product p WHERE CONCAT(p.product_name, p.product_category, p.product_brand) LIKE %?1%")
	// public List<Product> search(String keyword);
	//
	//
	// @Query("SELECT p.product_info FROM Product p WHERE p.product_name LIKE %?1%")
	// // @Query(value = "SELECT product_info FROM product_list WHERE product_name LIKE %?1%", nativeQuery = true)
	// public String getDetails(String product_name);

	public Product save(ProductModel product);

	// @Query("SELECT p FROM Product p WHERE ORDER BY p.product_price ASC")
	// public List<Product> AscPrice();
	//
	// @Query("SELECT p FROM Product p WHERE ORDER BY p.product_price DESC")
	// public List<Product> Price();
}
