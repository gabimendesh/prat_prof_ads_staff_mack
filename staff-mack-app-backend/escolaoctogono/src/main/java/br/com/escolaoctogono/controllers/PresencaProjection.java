package br.com.escolaoctogono.controllers;

import java.time.LocalDate;

public interface PresencaProjection {
    String getAluno();
    String getAulaPeriodo();
    LocalDate getData();
    boolean isPresente();
}