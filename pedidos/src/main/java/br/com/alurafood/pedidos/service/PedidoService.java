package br.com.alurafood.pedidos.service;

import br.com.alurafood.pedidos.dto.PedidoDto;
import br.com.alurafood.pedidos.dto.StatusDto;
import br.com.alurafood.pedidos.model.Pedido;
import br.com.alurafood.pedidos.model.Status;
import br.com.alurafood.pedidos.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private PedidoRepository repository;

    public List<PedidoDto> obterTodos() {
        return repository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, PedidoDto.class))
                .collect(Collectors.toList());
    }

    public PedidoDto obterPorId(Long id) {
        return modelMapper.map(repository.findById(id), PedidoDto.class);
    }

    public PedidoDto criarPedido(@Valid PedidoDto dto) {
        Pedido pedido = modelMapper.map(dto, Pedido.class);

        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(Status.REALIZADO);
        pedido.getItens().forEach(item -> item.setPedido(pedido));

        Pedido pedidoSalvo = repository.save(pedido);
        return modelMapper.map(pedidoSalvo, PedidoDto.class);
    }

    public PedidoDto atualizaStatus(Long id, StatusDto status) {
        Pedido pedido = repository.porIdComItens(id);

        if(pedido == null){
            throw new EntityNotFoundException();
        }

        pedido.setStatus(status.getStatus());
        repository.atualizaStatus(status.getStatus(), pedido);

        return modelMapper.map(pedido, PedidoDto.class);
    }

    public void aprovaPagamentoPedido(Long id) {
        Pedido pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(Status.PAGO);
        repository.atualizaStatus(Status.PAGO, pedido);
    }
}
