package br.com.beertech.fusion.unittests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.beertech.fusion.controller.dto.OperationDTO;
import br.com.beertech.fusion.domain.OperationType;
import br.com.beertech.fusion.domain.Balance;
import br.com.beertech.fusion.service.SaldoService;
import br.com.beertech.fusion.service.impl.SaldoServiceImpl;

public class SaldoTest {
	
	private static final String HASH = "94fc5d08-8269-4d0d-b45c-41dec9914e9d";
	
    @Test
    void testaSaldoDeposito() {
        List<OperationDTO> operacoes = new ArrayList<>();
        operacoes.add(new OperationDTO(OperationType.DEPOSITO, 100.,HASH));
        SaldoService saldoService = new SaldoServiceImpl();
        assertEquals(saldoService.calcularSaldo(operacoes), new Balance(100.));
    }

    @Test
    void testaSaldoSaque() {
        List<OperationDTO> operacoes = new ArrayList<>();
        operacoes.add(new OperationDTO(OperationType.SAQUE, 10.,HASH));
        SaldoService saldoService = new SaldoServiceImpl();
        assertEquals(saldoService.calcularSaldo(operacoes), new Balance(-10.));
    }

    @Test
    void testaSaldoOperacoesVariadas() {
        List<OperationDTO> operacoes = new ArrayList<>();
        operacoes.add(new OperationDTO(OperationType.DEPOSITO, 100.,HASH));
        operacoes.add(new OperationDTO(OperationType.SAQUE, 25.,HASH));
        operacoes.add(new OperationDTO(OperationType.SAQUE, 10.,HASH));
        SaldoService saldoService = new SaldoServiceImpl();
        assertEquals(saldoService.calcularSaldo(operacoes), new Balance(65.));
    }

    @Test
    void testaSaldoSemOperacoes() {
        List<OperationDTO> operacoes = new ArrayList<>();
        SaldoService saldoService = new SaldoServiceImpl();
        assertEquals(saldoService.calcularSaldo(operacoes), new Balance(0.));
    }
}
