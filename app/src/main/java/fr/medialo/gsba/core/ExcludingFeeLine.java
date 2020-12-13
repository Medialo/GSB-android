package fr.medialo.gsba.core;

import java.time.LocalDate;

@Deprecated
public class ExcludingFeeLine extends Fee{

    @Deprecated
    public ExcludingFeeLine(int id, int file_id, int statu_id, String name, double cost, String date) {
        super(id, file_id, statu_id, name, cost, date);
    }
}
