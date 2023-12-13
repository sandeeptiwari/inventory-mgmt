package com.app.inventorymgmt.controller;


import com.app.inventorymgmt.domain.dto.ProductDTO;
import com.app.inventorymgmt.domain.dto.ResponseDTO;
import com.app.inventorymgmt.security.ITokenGenerator;
import com.app.inventorymgmt.service.IProductService;
import com.app.inventorymgmt.utils.AppConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/inv/api/v0", produces = {MediaType.APPLICATION_JSON_VALUE})
public class InventoryMgmtRestController {

    private final IProductService productService;

    private final ITokenGenerator tokenGenerator;

    public InventoryMgmtRestController(IProductService productService, ITokenGenerator tokenGenerator) {
        this.productService = productService;
        this.tokenGenerator = tokenGenerator;
    }

    @GetMapping(value = "/ping")
    public String ping() {
        return "pong!";
    }

    @PostMapping(value = "/product")
    public ResponseEntity<ResponseDTO> addProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request) {
        preCheck(request);
        productService.addProduct(productDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AppConstants.STATUS_201, AppConstants.MESSAGE_201));
    }

    private void preCheck(HttpServletRequest request) {
        String token = request.getHeader(AppConstants.AUTHORIZATION);
        if ((token != null && token.isEmpty()) || !tokenGenerator.isAdmin(token)) {
            throw new IllegalArgumentException("Token is not provide or it's invalid");
        }
    }


    @DeleteMapping("/product/{productId}")
    public ResponseEntity<ResponseDTO> deleteProductById(@PathVariable("productId") Long productId, HttpServletRequest request) {
        preCheck(request);
        productService.deleteProduct(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(AppConstants.STATUS_200, AppConstants.PRODUCT_DELETE_MESSAGE_200));
    }


    @PutMapping("/product/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable("id") Long id,
                              @RequestBody ProductDTO productDTO,
                              HttpServletRequest request) {
        preCheck(request);
        productService.updateProduct(id, productDTO);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new ResponseDTO(AppConstants.STATUS_202, AppConstants.PRODUCT_UPDATE_MESSAGE_200));
    }


}
