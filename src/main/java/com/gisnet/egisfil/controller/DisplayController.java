
package com.gisnet.egisfil.controller;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.gisnet.egisfil.RepositoryService.TicketRepositoryService;
import com.gisnet.egisfil.domain.Ticket;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gisnet.egisfil.util.ReponseTurnos;
import java.util.ArrayList;
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
        this.template.convertAndSend("/call/message",lista  );
        return "Llamado a display por medio de rest call";
    }
    @PostMapping("/api/call")
    public String call(@RequestBody Ticket llamado){
        List<Ticket> espera = repo.findByTipo_Servicio(llamado.getServicio().getTipo_servicio(), llamado.getId_sucursal());
        List<Ticket> atencion = repo.findByTipo_Servicio_enAtencion(llamado.getServicio().getTipo_servicio(), llamado.getId_sucursal());
        List<Ticket> esperacopy = new ArrayList<>();
        for(Ticket t : espera){
            if(t.getId().compareTo(llamado.getId()) != 0){
                esperacopy.add(t);
            }
        }
 
        ReponseTurnos res = new ReponseTurnos();
        if(llamado.getMostrador() != null){
            res.setLlamado(llamado);
        }else{
            esperacopy.add(llamado);
        }
        if(atencion.size() > 0)
            atencion.remove(atencion.size()-1);
        res.setEnAtencion(atencion);
        res.setProximos(esperacopy);
        
        System.err.println("/call/message/"+llamado.getId_sucursal()+"/"+llamado.getServicio().getTipo_servicio());
        this.template.convertAndSend("/call/message/"+llamado.getId_sucursal()+"/"+llamado.getServicio().getTipo_servicio(),res  );
        return "Llamado a display por medio de rest call";
    }
}
