package com.upiiz.Examen_3.repositories;

import com.upiiz.Examen_3.models.HabitacionModel;

import java.util.List;

public interface HabitacionRepository {
    List<HabitacionModel> findAllHabitaciones();
    HabitacionModel findHabitacionById(int id);
    HabitacionModel save(HabitacionModel habitacion);
    int update(HabitacionModel habitacion);
    int delete(int id);
}
