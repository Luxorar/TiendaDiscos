package com.TiendaDisco.RegistrarDiscos.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import com.TiendaDisco.RegistrarDiscos.dto.DiscoDTO;
import com.TiendaDisco.RegistrarDiscos.model.Disco;
import com.TiendaDisco.RegistrarDiscos.service.IDiscoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controlador REST que expone los endpoints para la gestion del catalogo de discos.
 * Se encarga de procesar las peticiones http y delegar la logica al servicio correspondiente.
 * * Ruta base: /api/v1/productos
 * * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/discos")
@Tag(
        name="Discos",
        description="Se administran los discos"
)
public class DiscoController {

    @Autowired
    private IDiscoService discoService;

    @Value("${upload.path:/app/uploads}")
    private String uploadPath;

    /**
     * Endpoint para registrar un nuevo disco en el sistema.
     * * @param disco Objeto con los datos del disco a registrar
     * @return El disco persistido
     */
    @Operation(
            summary="Registro de un disco",
            description="Permite agregar un nuevo disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode="201",
                    description="Disco creado"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
})
    @PostMapping
    public Disco registrarDisco(@Valid @RequestBody Disco disco) {
        return discoService.postDisco(disco);
    }

    /**
     * Endpoint para buscar la información detallada de un disco específico.
     * * @param id El identificador único del disco enviado por URL.
     * @return Una respuesta HTTP 200 con el DTO del disco, o 400/404 si hay un error.
     */
    @Operation(
            summary="Obtencion de un disco por id",
            description ="Obtiene un disco en base a su id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Obtencion exitosa"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DiscoDTO> obtenerDiscoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(discoService.getDiscoId(id));
    }

    /**
     * Endpoint para modificar los datos de un disco ya existente.
     * * @param id El identificador del disco a modificar.
     * @param disco El objeto con los nuevos datos del disco.
     * @return Un mensaje de confirmación en formato String.
     */
    @Operation(
            summary="Actualizar disco",
            description = "Actualiza los datos de un disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Actualizacion completa"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos"

            )
    })
    @PutMapping("/{id}")
    public String actualizarDisco(@PathVariable Long id, @Valid @RequestBody Disco disco) {
        return discoService.putDisco(id, disco);
    }

    /**
     * Endpoint para eliminar un disco del catálogo.
    * * @param id El identificador del disco que se desea borrar.
    * @return Un mensaje de confirmación de la eliminación.
    */
    @Operation(
            summary="Eliminar disco",
            description = "Elimina un disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Eloiminacion exitosa"),
            @ApiResponse(responseCode = "400",
                    description = "Datos invalidos")
    })
    @DeleteMapping("/{id}")
    public String eliminarDisco(@PathVariable Long id) {
        return discoService.deleteDisco(id);
    }

    /**
     * Endpoint para obtener el catálogo completo de discos registrados.
     * * @return Una respuesta HTTP 200 con la lista completa de discos en formato DTO.
     */
    @GetMapping
    public ResponseEntity<List<DiscoDTO>> obtenerTodosLosDiscos() {
        return ResponseEntity.ok(discoService.getAllDiscos());
    }

    @Operation(
            summary="Buscar discos por nombre o artista",
            description="Busca discos cuyo nombre o artista contengan el texto indicado"
    )
    @GetMapping("/nombre/{query}")
    public ResponseEntity<List<DiscoDTO>> buscarPorNombre(@PathVariable String query) {
        return ResponseEntity.ok(discoService.searchByNombre(query));
    }

    @Operation(
            summary="Subir portada de un disco",
            description="Permite subir una imagen como portada de un disco"
    )
    @ApiResponses({
            @ApiResponse(responseCode="200",
                    description="Imagen subida exitosamente"),
            @ApiResponse(responseCode="400",
                    description="Datos invalidos")
    })
    @PostMapping("/{id}/imagen")
    public ResponseEntity<String> subirImagen(@PathVariable Long id,
                                              @RequestParam("imagen") MultipartFile imagen) throws IOException {
        DiscoDTO disco = discoService.getDiscoId(id);
        if (disco == null) {
            return ResponseEntity.notFound().build();
        }

        String ext = Paths.get(imagen.getOriginalFilename()).toString().replaceAll(".*\\.", "");
        String nombreArchivo = "disco_" + id + "_" + UUID.randomUUID() + "." + ext;

        Path directorio = Paths.get(uploadPath);
        if (!Files.exists(directorio)) {
            Files.createDirectories(directorio);
        }

        Path destino = directorio.resolve(nombreArchivo);
        imagen.transferTo(destino.toFile());

        String url = "/uploads/" + nombreArchivo;
        discoService.setImagen(id, url);

        return ResponseEntity.ok(url);
    }
}
