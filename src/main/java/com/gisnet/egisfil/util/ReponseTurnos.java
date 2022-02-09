
package com.gisnet.egisfil.util;

import com.gisnet.egisfil.domain.Ticket;
import java.io.Serializable;
import java.util.List;

public class ReponseTurnos implements Serializable{
    
    private List<Ticket> proximos;
    private List<Ticket> enAtencion;
    private Ticket llamado;
    
    public List<Ticket> getProximos(){
        return proximos;
    }
    
    public List<Ticket> getEnAtencion(){
        return enAtencion;
    }
    
    public Ticket getLlamado(){
        return llamado;
    }
    
    public void setProximos(List<Ticket> proximos){
        this.proximos = proximos;
    }
    public void setEnAtencion(List<Ticket> enAtencion){
        this.enAtencion = enAtencion;
    }
    public void setLlamado(Ticket llamado){
        this.llamado = llamado;
    }
}
