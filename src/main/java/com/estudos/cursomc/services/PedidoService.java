package com.estudos.cursomc.services;

import com.estudos.cursomc.domain.Categoria;
import com.estudos.cursomc.domain.Pedido;
import com.estudos.cursomc.repositories.PedidoRepository;
import com.estudos.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscar(Integer id) {
        Optional<Pedido> obj = pedidoRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }
}
