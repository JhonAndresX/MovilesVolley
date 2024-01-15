package com.example.demo.models.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import com.example.demo.models.entity.Proveedor;
import com.example.demo.models.services.IProveedorService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })

public class ProveedorRestController {
	
	@Autowired
	private IProveedorService proveedorService;
	private static final String CARPETA_IMAGENES = "C:\\Users\\andre\\Documents\\workspace-spring-tool-suite-4-4.20.1.RELEASE\\PracticaWeb\\Imagenes_Carpeta";

	
	@GetMapping("/proveedor")
	public List<Proveedor> indext() {
		return proveedorService.findAll();

	}

	@GetMapping("/proveedor/{id}")
	public Proveedor show(@PathVariable Long id) {
		return proveedorService.findById(id);
	}

    @PostMapping("/proveedor")
    @ResponseStatus(HttpStatus.CREATED)
    public Proveedor create(@RequestPart("proveedor") Proveedor proveedor,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) {
        if (imagen != null && !imagen.isEmpty()) {
            try {
                String imagenPath = almacenarImagen(imagen);
                proveedor.setImagenPath(imagenPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return proveedorService.save(proveedor);
    }
    private String almacenarImagen(MultipartFile imagen) throws IOException {
        String nombreUnico = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();
        Path rutaCompleta = Paths.get(CARPETA_IMAGENES, nombreUnico);
        Files.write(rutaCompleta, imagen.getBytes());
        return nombreUnico; 
    }
    
	@PutMapping("/proveedor/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Proveedor update(@RequestBody Proveedor proveedor, @PathVariable Long id) {
		Proveedor proveedorac = proveedorService.findById(id);
		proveedorac.setNombrep(proveedor.getNombrep());
		proveedorac.setApellidop(proveedor.getApellidop());
		proveedorac.setTelefono(proveedor.getTelefono());
		proveedorac.setEmpresa(proveedor.getEmpresa());
		return proveedorService.save(proveedorac);
	}
	
	@GetMapping("/proveedor/imagen/{imagenFileName}")
	public ResponseEntity<Resource> getImagen(@PathVariable String imagenFileName) {
	    Path imagenPath = Paths.get(CARPETA_IMAGENES, imagenFileName);
	    Resource imagenResource = new FileSystemResource(imagenPath);
	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
	            .body(imagenResource);
	}
	
	@DeleteMapping("/proveedor/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		proveedorService.delete(id);
	}
}
