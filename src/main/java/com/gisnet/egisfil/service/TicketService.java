
package com.gisnet.egisfil.service;


import com.gisnet.egisfil.domain.Ticket;
import java.util.List;
import java.util.Optional;


public interface TicketService {
    Optional<Ticket> findOne(String id);
    List<Ticket> findAll();
    Ticket create(Ticket ticket);
    Ticket update(Ticket ticket);
    List<Ticket> findByTipo_Servicio(String tipo_servicio,String id_sucursal);
    List<Ticket> findByTipo_Servicio_enAtencion(String tipo_servicio,String id_sucursal);
    void delete(Ticket ticket);
}
