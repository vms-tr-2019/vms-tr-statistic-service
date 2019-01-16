package wms.statistic.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wms.statistic.jpa.ProductJPA;


@Repository
public interface ProductsRepository extends JpaRepository<ProductJPA, Integer> {

  @Query("SELECT product FROM ProductJPA as product WHERE product.name = :name")
  Optional<ProductJPA> findByName(@Param("name") String name);
}
