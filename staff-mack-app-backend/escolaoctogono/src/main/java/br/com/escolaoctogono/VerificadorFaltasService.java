package br.com.escolaoctogono;

import br.com.escolaoctogono.controllers.PresencaProjection;
import br.com.escolaoctogono.models.Aluno;
import br.com.escolaoctogono.repositories.AlunoAulaRepository;
import br.com.escolaoctogono.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class VerificadorFaltasService {
    @Autowired
    private AlunoAulaRepository alunoAulaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Scheduled(fixedRate = 5 * 24 * 60 * 60 * 1000) // Executa a cada 5 dias
    public void verificarFaltasTodosAlunos() {
        // Buscar todos os alunos do banco de dados
        List<Aluno> alunos = alunoRepository.findAll();

        int diasAteAgora;
        // Iterar sobre cada aluno para verificar as faltas
        for (Aluno aluno : alunos) {
            List<PresencaProjection> presencasAluno = alunoAulaRepository.findPresencasByAlunoIdentificacao(aluno.getIdentificacao());
            diasAteAgora = presencasAluno.size();
            long totalFaltas = presencasAluno.stream().filter(p -> !p.isPresente()).count();

            double porcentagemDeFaltas = ((double)totalFaltas / diasAteAgora) * 100;

            if (porcentagemDeFaltas >= (double)20) {
                // Chamar o script Python para enviar o e-mail para este aluno
                chamarScriptPython(aluno.getEmailResponsavel(), "Seu filho vai reprovar por faltas!", "Atenção responsável de " + aluno.getNome() + ", seu(sua) filho(a) está quase estourando em faltas!<br><br> Atualmente ele já faltou " + (int)porcentagemDeFaltas +"% do período letivo. Por favor, verifique a situação.<br><br> Atenciosamente, Caio, Evelin, Gabrielly :)<br><br>");
                try {
                    Thread.sleep(1000 * 60);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void chamarScriptPython(String destEmail, String subject, String body) {
        try {

            Process process = Runtime.getRuntime().exec("python enviar_email.py " + destEmail + " \"" + subject + "\" \"" + body + "\" escola.png");

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}


