package com.kh.demo.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
class ProductDAOImplTest {

  @Autowired
  ProductDAO productDAO;

  @Test
  @DisplayName("상품등록")
  void save() {
    Product product = new Product();
    product.setProductId(1L);
    product.setPname("휴대폰");
    product.setQuantity(1L);
    product.setPrice(1300000L);

    Long affectedRow = productDAO.save(product);

    log.info("affectedRow={}",affectedRow);
    Assertions.assertThat(1).isEqualTo(1);

  }


  @Test
  @DisplayName("상품조회")
  void findById(){
    Long productId = 1L;
    Optional<Product> findedProduct = productDAO.findByProductId(productId);
    log.info("product={}",findedProduct);

    Assertions.assertThat(findedProduct.get().getPname()).isEqualTo("휴대폰");
  }

  @Test
  @DisplayName("상품수정")
  void update(){
    Long productId = 1L;

    Product product = new Product();
    product.setProductId(productId);
    product.setPname("책상");
    product.setQuantity(1L);
    product.setPrice(300000L);

    productDAO.update(productId, product);

    Optional<Product> updateProduct = productDAO.findByProductId(productId);

    Assertions.assertThat(updateProduct.get().getProductId()).isEqualTo(1L);
    Assertions.assertThat(updateProduct.get().getPname()).isEqualTo("책상");
    
  }

  @Test
  @DisplayName("상품삭제")
  void delete(){
    Long productId = 1L;
    productDAO.deleteByProductId(productId);

    Optional<Product> findedProduct = productDAO.findByProductId(productId);
    Assertions.assertThat(findedProduct.isEmpty()).isTrue();

  }

  @Test
  @DisplayName("상품목록")
  void all(){
    List<Product> list = productDAO.findAll();
    log.info("sizeOfList={}", list.size());

    list.stream().forEach(product->log.info(product.toString()));
  }
}