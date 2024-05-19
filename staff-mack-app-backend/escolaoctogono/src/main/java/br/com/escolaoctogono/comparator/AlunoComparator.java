package br.com.escolaoctogono.comparator;

import java.util.Comparator;

public class AlunoComparator implements Comparator<String> {
    @Override
    public int compare(String id1, String id2) {

        int num1 = Integer.parseInt(id1.substring(1));
        int num2 = Integer.parseInt(id2.substring(1));

        return Integer.compare(num1, num2);
    }
}