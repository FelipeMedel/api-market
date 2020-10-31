package com.medel.market.persistence;

import com.medel.market.domain.Purchase;
import com.medel.market.domain.repository.PurchaseRepository;
import com.medel.market.persistence.crud.CompraCrudrepository;
import com.medel.market.persistence.entity.Compra;
import com.medel.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudrepository compraCrudrepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchase((List<Compra>) compraCrudrepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudrepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchase(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra));
        return mapper.toPurchase(compraCrudrepository.save(compra));
    }
}
