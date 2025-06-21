package com.upiiz.Examen_3.services;

import com.upiiz.Examen_3.models.HabitacionModel;
import com.upiiz.Examen_3.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Service
public class HabitacionService implements HabitacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<HabitacionModel> findAllHabitaciones() {
        return jdbcTemplate.query(
                "SELECT * FROM habitaciones",
                new BeanPropertyRowMapper<>(HabitacionModel.class)
        );
    }

    @Override
    public HabitacionModel findHabitacionById(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM habitaciones WHERE id = ?",
                new BeanPropertyRowMapper<>(HabitacionModel.class),
                id
        ).stream().findFirst().orElse(new HabitacionModel());
    }

    @Override
    public HabitacionModel save(HabitacionModel habitacion) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO habitaciones(tipo, precio_noche, disponible) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, habitacion.getTipo());
            ps.setBigDecimal(2, habitacion.getPrecioNoche());
            ps.setBoolean(3, habitacion.isDisponible());
            return ps;
        }, keyHolder);

        Number generatedId = keyHolder.getKey();
        habitacion.setId(generatedId != null ? generatedId.longValue() : 0L);

        return habitacion;
    }

    @Override
    public int update(HabitacionModel habitacion) {
        return jdbcTemplate.update(
                "UPDATE habitaciones SET tipo = ?, precio_noche = ?, disponible = ? WHERE id = ?",
                habitacion.getTipo(),
                habitacion.getPrecioNoche(),
                habitacion.isDisponible(),
                habitacion.getId()
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(
                "DELETE FROM habitaciones WHERE id = ?",
                id
        );
    }
}
