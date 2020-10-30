package com.medel.market.persistence.crud;

import com.medel.market.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    @Query(value = "select * from productos where id_categoria = ?", nativeQuery = true)
    List<Producto> getByIdCategoria(int idCategoria);

    List<Producto> findByIdCategoriaOrderByNombre(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
