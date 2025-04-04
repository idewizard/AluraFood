package br.com.alurafood.pedidos.controller;

import br.com.alurafood.pedidos.dto.PedidoDto;
import br.com.alurafood.pedidos.dto.StatusDto;
import br.com.alurafood.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<PedidoDto> listarTodos(){
       return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> listarPorId(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(service.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<PedidoDto> realizarPedido(@RequestBody @Valid PedidoDto dto, UriComponentsBuilder uriBuilder){
        PedidoDto pedidoRealizado = service.criarPedido(dto);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(uri).body(pedidoRealizado);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDto> atualizarStatus(@PathVariable Long id, @RequestBody StatusDto status){
        PedidoDto pedidoDto = service.atualizaStatus(id, status);
        return ResponseEntity.ok(pedidoDto);
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Long id) {
        service.aprovaPagamentoPedido(id);
        return ResponseEntity.ok().build();
    }
}
