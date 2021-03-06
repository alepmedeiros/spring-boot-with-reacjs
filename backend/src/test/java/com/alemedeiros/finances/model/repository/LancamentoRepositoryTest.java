package com.alemedeiros.finances.model.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.alemedeiros.finances.model.entity.Lancamentos;
import com.alemedeiros.finances.model.enums.StatusLancamento;
import com.alemedeiros.finances.model.enums.TipoLancamento;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
    

    @Autowired
    LancamentoRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void deveSalvarUmLancamento(){
        Lancamentos lancamento = criarLancamento();
        
        lancamento = repository.save(lancamento);

        assertThat(lancamento.getId()).isNotNull();
    }

    
    @Test
    void deveDeletarUmLancamento(){
        Lancamentos lancamento = criarEPersistirLancamento();

        lancamento = entityManager.find(Lancamentos.class, lancamento.getId());


        repository.delete(lancamento);
        
        Lancamentos lancamentoInexistente = entityManager.find(Lancamentos.class, lancamento.getId());

        assertThat(lancamentoInexistente).isNull();
    }
    
    
    @Test
    void deveAtualiarUmLancamento(){
        Lancamentos lancamento = criarEPersistirLancamento();

        lancamento.setAno(2023);
        lancamento.setDescricao("Teste de atualizacao");
        lancamento.setStatus(StatusLancamento.CANCELADO);

        repository.save(lancamento);

        Lancamentos lancamentoAtualizado = entityManager.find(Lancamentos.class, lancamento.getId());

        assertThat(lancamentoAtualizado.getAno()).isEqualTo(2023);
        assertThat(lancamentoAtualizado.getDescricao()).isEqualTo("Teste de atualizacao");
        assertThat(lancamentoAtualizado.getStatus()).isEqualTo(StatusLancamento.CANCELADO);
    }
    
    @Test
    void deveBuscarUmLancamentoPorId(){
        Lancamentos lancamento = criarEPersistirLancamento();

        Optional<Lancamentos> lancamentoEncontrado = repository.findById(lancamento.getId());

        assertThat(lancamentoEncontrado.isPresent()).isTrue();
    }

    private Lancamentos criarEPersistirLancamento() {
        Lancamentos lancamento = criarLancamento();
        entityManager.persist(lancamento);
        return lancamento;
    }

    public static Lancamentos criarLancamento() {
        Lancamentos lancamento = Lancamentos.builder()
                            .ano(2022)
                            .mes(5)
                            .descricao("lancamento qualquer")
                            .valor(BigDecimal.valueOf(10))
                            .tipo(TipoLancamento.RECEITA)
                            .status(StatusLancamento.PENDENTE)
                            .dataCadastro(LocalDate.now())
                            .build();
        return lancamento;
    }
}
