alert("Funciona el JS");

function obtenerHabitaciones() {
    $.ajax({
        url: "/v1/api/habitaciones",
        method: "GET",
        success: function (resultado) {
            let tabla = $('#example').DataTable();
            tabla.clear();
            resultado.forEach(h => {
                let botones = `
                    <button class="btn btn-primary mb-2" onclick="seleccionarHabitacionActualizar(${h.id}, '${h.tipo}', ${h.precioNoche}, ${h.disponible})">Editar</button>
                    <button class="btn btn-danger mb-2" onclick="eliminarHabitacion(${h.id})">Eliminar</button>`;
                let rowNode = tabla.row.add([
                    h.id,
                    h.tipo,
                    h.precioNoche,
                    h.disponible ? "Sí" : "No",
                    botones
                ]).draw(false).node();
                $(rowNode).attr("id", "renglon_" + h.id);
            });
        },
        error: function () {
            alert("Error al cargar las habitaciones");
        }
    });
}

function guardarHabitacion() {
    let tipo = $("#tipo_habitacion").val();
    let precioNoche = parseFloat($("#precio_habitacion").val());
    let disponible = $("#disponible_habitacion").is(':checked');

    if (tipo && precioNoche > 0) {
        $.ajax({
            url: "/v1/api/habitacion",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                tipo: tipo,
                precioNoche: precioNoche,
                disponible: disponible
            }),
            success: function (resultado) {
                if (resultado.estado === 1) {
                    let h = resultado.habitacion;
                    let botones = `
                        <button class="btn btn-primary mb-2" onclick="seleccionarHabitacionActualizar(${h.id}, '${h.tipo}', ${h.precioNoche}, ${h.disponible})">Editar</button>
                        <button class="btn btn-danger mb-2" onclick="eliminarHabitacion(${h.id})">Eliminar</button>`;
                    let tabla = $('#example').DataTable();
                    let rowNode = tabla.row.add([
                        h.id,
                        h.tipo,
                        h.precioNoche,
                        h.disponible ? "Sí" : "No",
                        botones
                    ]).draw(false).node();
                    $(rowNode).attr("id", "renglon_" + h.id);

                    const modal = bootstrap.Modal.getInstance(document.getElementById('modalNuevaHabitacion'));
                    modal.hide();
                    alert(resultado.mensaje);
                } else {
                    alert(resultado.mensaje);
                }
            },
            error: function () {
                alert("Error al guardar habitación");
            }
        });
    } else {
        alert("Completa los datos correctamente");
    }
}

function seleccionarHabitacionActualizar(id, tipo, precioNoche, disponible) {
    $("#id_habitacion").val(id);
    $("#tipo_habitacion").val(tipo);
    $("#precio_habitacion").val(precioNoche);
    $("#disponible_habitacion").prop("checked", disponible);
    $("#btnGuardar").hide();
    $("#btnActualizar").show();

    const modal = new bootstrap.Modal(document.getElementById('modalNuevaHabitacion'));
    modal.show();
}

function actualizarHabitacion() {
    let id = $("#id_habitacion").val();
    let tipo = $("#tipo_habitacion").val();
    let precioNoche = parseFloat($("#precio_habitacion").val());
    let disponible = $("#disponible_habitacion").is(':checked');

    if (tipo && precioNoche > 0) {
        $.ajax({
            url: `/v1/api/habitacion/${id}`,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify({
                tipo: tipo,
                precioNoche: precioNoche,
                disponible: disponible
            }),
            success: function (resultado) {
                if (resultado.estado === 1) {
                    let tabla = $('#example').DataTable();
                    let rowNode = document.getElementById('renglon_' + id);

                    if (rowNode) {
                        tabla.row(rowNode).data([
                            id,
                            tipo,
                            precioNoche,
                            disponible ? "Sí" : "No",
                            `
                            <button class="btn btn-primary mb-2" onclick="seleccionarHabitacionActualizar(${id}, '${tipo}', ${precioNoche}, ${disponible})">Editar</button>
                            <button class="btn btn-danger mb-2" onclick="eliminarHabitacion(${id})">Eliminar</button>
                            `
                        ]).draw(false);

                        const modal = bootstrap.Modal.getInstance(document.getElementById('modalNuevaHabitacion'));
                        modal.hide();
                        alert(resultado.mensaje);
                    } else {
                        alert("No se encontró la fila en la tabla para actualizar.");
                    }
                } else {
                    alert(resultado.mensaje);
                }
            },
            error: function () {
                alert("Error al actualizar habitación");
            }
        });
    } else {
        alert("Completa los datos correctamente");
    }
}

function eliminarHabitacion(id) {
    if (confirm("¿Seguro que deseas eliminar esta habitación?")) {
        $.ajax({
            url: `/v1/api/habitacion/${id}`,
            method: "DELETE",
            success: function (resultado) {
                if (resultado.estado === 1) {
                    let tabla = $('#example').DataTable();
                    tabla.row($('#renglon_' + id)).remove().draw();
                    alert(resultado.mensaje);
                } else {
                    alert(resultado.mensaje);
                }
            },
            error: function () {
                alert("Error al eliminar habitación");
            }
        });
    }
}

function limpiarModal() {
    $("#id_habitacion").val("");
    $("#tipo_habitacion").val("individual");
    $("#precio_habitacion").val("");
    $("#disponible_habitacion").prop("checked", false);
    $("#btnGuardar").show();
    $("#btnActualizar").hide();
}
