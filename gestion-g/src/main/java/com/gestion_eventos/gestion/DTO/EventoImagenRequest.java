//Clodenary
//DTO CREADO PARA RECIBIR LAS URLS DE LAS IMGANES DESDE EL FRON END
package com.gestion_eventos.gestion.DTO;

import java.util.List;

public class EventoImagenRequest {
    
    private String portadaUrl;
    private List<String> galeriaUrls;

    public String getPortadaUrl() { return portadaUrl; }
    public void setPortadaUrl(String portadaUrl) { this.portadaUrl = portadaUrl; }

    public List<String> getGaleriaUrls() { return galeriaUrls; }
    public void setGaleriaUrls(List<String> galeriaUrls) { this.galeriaUrls = galeriaUrls; }
}
