package br.com.escolaoctogono.controllers;

import br.com.escolaoctogono.comparator.AlunoComparator;
import br.com.escolaoctogono.dto.AlunoDTO;
import br.com.escolaoctogono.dto.PresencaInfoDTO;
import br.com.escolaoctogono.models.Aluno;
import br.com.escolaoctogono.models.RelatorioAlunosPDF;
import br.com.escolaoctogono.projections.AlunoWithMateriaCodigo;
import br.com.escolaoctogono.repositories.AlunoAulaRepository;
import br.com.escolaoctogono.repositories.AlunoRepository;
import br.com.escolaoctogono.repositories.RelatorioAluno;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.io.ByteArrayOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "https://dt95ctjg4nmhd.cloudfront.net"})
@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoAulaRepository alunoAulaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<AlunoDTO> getAllAlunosWithMateriaCodigo() {
        List<AlunoWithMateriaCodigo> alunoAulas = alunoRepository.findAllAlunosWithMateriaCodigo();

        Map<String, List<AlunoWithMateriaCodigo>> grouped = alunoAulas.stream()
                .collect(Collectors.groupingBy(AlunoWithMateriaCodigo::getIdentificacao));

        List<AlunoDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<AlunoWithMateriaCodigo>> entry : grouped.entrySet()) {
            AlunoDTO dto = new AlunoDTO();
            dto.setIdentificacao(entry.getKey());
            dto.setMateriaCodigo(entry.getValue().stream()
                    .map(AlunoWithMateriaCodigo::getMateriaCodigo)
                    .collect(Collectors.toList()));

            AlunoWithMateriaCodigo firstAluno = entry.getValue().get(0);
            dto.setNome(firstAluno.getNome());
            dto.setEmailResponsavel(firstAluno.getEmailResponsavel());
            dto.setTurmaIdentificacao(firstAluno.getTurmaIdentificacao());
            dto.setTurmaAno(firstAluno.getTurmaAno());

            result.add(dto);
        }

        return result;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable(value = "id") String id) {
        Aluno aluno = alunoRepository.findByIdentificacao(id.toUpperCase());
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(aluno);
    }

    @GetMapping("/turma/{turmaIdentificacao}")
    public ResponseEntity<List<Aluno>> getAlunosByTurma(@PathVariable(value = "turmaIdentificacao") String turmaIdentificacao) {
        List<Aluno> alunos = alunoRepository.findByTurmaIdentificacao(turmaIdentificacao.toUpperCase());
        if (alunos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(alunos);
    }

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@ApiParam(value = "Dados do aluno", required = true) @RequestBody AlunoDTO alunoDTO) {

        Aluno existingAluno = alunoRepository.findByIdentificacao(alunoDTO.getIdentificacao());
        if (existingAluno != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Aluno aluno = new Aluno();
        aluno.setIdentificacao(alunoDTO.getIdentificacao());
        aluno.setNome(alunoDTO.getNome());
        aluno.setEmailResponsavel(alunoDTO.getEmailResponsavel());
        aluno.setTurmaAno(alunoDTO.getTurmaAno());
        aluno.setTurmaIdentificacao(alunoDTO.getTurmaIdentificacao());

        Aluno novoAluno = alunoRepository.save(aluno);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable(value = "id") String id) {
        Aluno aluno = alunoRepository.findByIdentificacao(id.toUpperCase());
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        alunoRepository.delete(aluno);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<PresencaInfoDTO> getPercentageById(@PathVariable(value = "id") String id) {
        try {
            Aluno aluno = alunoRepository.findByIdentificacao(id.toUpperCase());

            if (aluno == null) {
                return ResponseEntity.notFound().build(); // Retorna status 404 Not Found
            }

            List<PresencaProjection> presencasAluno = alunoAulaRepository.findPresencasByAlunoIdentificacao(aluno.getIdentificacao());

            int totalDias = presencasAluno.size();
            long totalFaltas = presencasAluno.stream().filter(p -> !p.isPresente()).count();
            long totalPresencas = totalDias - totalFaltas;

            double porcentagemDeFaltas = ((double) totalFaltas / totalDias) * 100;
            double porcentagemDePresencas = ((double) totalPresencas / totalDias) * 100;

            PresencaInfoDTO presencaInfo = new PresencaInfoDTO(porcentagemDeFaltas, porcentagemDePresencas);
            return ResponseEntity.ok(presencaInfo);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna status 500 Internal Server Error em caso de exceção
        }
    }

    @GetMapping("/relatorio/alunos")
    public ResponseEntity<List<RelatorioAluno>> gerarRelatorioAlunos(
            @RequestParam int semana,
            @RequestParam int mes,
            @RequestParam int ano,
            @RequestParam String disciplina,
            @RequestParam String turma) {

        try {
            LocalDate dataInicial = LocalDate.of(ano, mes, 1).with(TemporalAdjusters.firstDayOfMonth());
            while (dataInicial.getDayOfWeek() != DayOfWeek.MONDAY) {
                dataInicial = dataInicial.plusDays(1);
            }
            dataInicial = dataInicial.plusWeeks(semana - 1);

            LocalDate dataFinal = dataInicial.plusDays(6);

            String dataInicialFormatada = dataInicial.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String dataFinalFormatada = dataFinal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ConsultaPresencaPorData");
            query.registerStoredProcedureParameter("turma", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dataInicial", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dataFinal", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("disciplina", Integer.class, ParameterMode.IN);
            query.setParameter("turma", turma);
            query.setParameter("dataInicial", dataInicialFormatada);
            query.setParameter("dataFinal", dataFinalFormatada);
            query.setParameter("disciplina", Integer.parseInt(disciplina));

            List<RelatorioAluno> relatorioAlunos = query.getResultList();

            return new ResponseEntity<>(relatorioAlunos, HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}


