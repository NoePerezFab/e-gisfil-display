
package com.gisnet.egisfil.controller;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.egisfil.RepositoryService.TicketRepositoryService;
import com.gisnet.egisfil.domain.Ticket;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DisplayController {
    private ObjectMapper maper = new ObjectMapper();
    private SimpMessagingTemplate template;
    
    @Autowired
        public DisplayController(SimpMessagingTemplate template) {
            this.template = template;
        }
        
   @Autowired
   TicketRepositoryService repo;
          
    @GetMapping("/api/llamado")
    public String llamado(@RequestParam String id_sucursal,@RequestParam String tipo_servicio){
        List<Ticket> lista = repo.findByTipo_Servicio(tipo_servicio, id_sucursal);
        this.template.convertAndSend("/topic/message",lista  );
        return "Llamado a display por medio de rest call";
    }
}
