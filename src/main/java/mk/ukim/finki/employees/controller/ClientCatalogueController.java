package mk.ukim.finki.employees.controller;

import mk.ukim.finki.employees.model.Client;
import mk.ukim.finki.employees.service.ClientCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients/catalogue")
public class ClientCatalogueController {

//    @Autowired
//    private ClientCatalogueService clientCatalogueService;
//
//    @GetMapping("/")
//    public ResponseEntity<List<Client>> getAll(){
//        return this.clientCatalogueService.getAll().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
