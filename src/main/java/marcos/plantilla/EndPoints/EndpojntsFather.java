/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marcos.plantilla.EndPoints;

import java.util.List;
import java.util.Optional;
import marcos.plantilla.DataAccess.Models.FatherModel;
import marcos.plantilla.Logic.FatherLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author maalf
 */
@RestController
public abstract class EndpojntsFather<T extends FatherModel, I> {

    @Autowired
    private FatherLogic<T, I> fatherLogic;

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable I id) {
        Optional<T> entity = fatherLogic.findById(id);
        return entity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> entities = fatherLogic.findAll();
        return ResponseEntity.ok(entities);
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T savedEntity = fatherLogic.save(entity);
        return ResponseEntity.ok(savedEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable I id, @RequestBody T entity) {
        T updatedEntity = fatherLogic.update(id, entity);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable I id) {
        fatherLogic.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
