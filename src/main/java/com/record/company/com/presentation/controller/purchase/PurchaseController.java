package com.record.company.com.presentation.controller.purchase;

import com.record.company.com.bussines.services.IPurchaseServices;
import com.record.company.com.domain.dto.purchase.PurchaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recordCompany/purchase")
public class PurchaseController {

    @Autowired
    IPurchaseServices purchaseServices;

    @GetMapping
    public ResponseEntity<List<PurchaseDto>> getAllPurchase(){
        return ResponseEntity.ok(purchaseServices.GetAllPrePurchase());
    }

    @GetMapping("{id}")
    public ResponseEntity<PurchaseDto> getPurchaseById(@PathVariable("id") int id){
        return ResponseEntity.ok(purchaseServices.getPurchaseById(id));
    }

    @PostMapping("{idUser}/{idAlbum}")
    public ResponseEntity<PurchaseDto> createPurchase (@PathVariable("idUser") int idUser,@PathVariable("idAlbum") int idAlbum){
        return ResponseEntity.created(URI.create("/api/v1/recordCompany/purchase")).body(purchaseServices.createPrePurchase(idUser,idAlbum));
    }

    @PutMapping("{id}/{idAlbum}")
    public ResponseEntity<PurchaseDto> updatePurchase(@PathVariable("id") int id,@PathVariable("idAlbum") int idAlbum){
        return ResponseEntity.ok(purchaseServices.updatePrePurchase(id,idAlbum));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PurchaseDto> deletePurchase(@PathVariable("id") int id){
        return ResponseEntity.ok(purchaseServices.deletePrePurchase(id));
    }
}
