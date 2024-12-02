/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marcos.plantilla.Logic;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import marcos.plantilla.DataAccess.Models.FatherModel;
import marcos.plantilla.DataAccess.Repositories.FatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maalf
 */
public abstract class FatherLogic<T extends FatherModel, I> {

    protected final FatherRepository<T, I> repository;

    @Autowired
    public FatherLogic(FatherRepository<T, I> repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Optional<T> findById(I id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Transactional
    public void deleteById(I id) {
        repository.deleteById(id);
    }

    @Transactional
    public T update(I id, T entity) {
        T ent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        updateObject(ent, entity);
        return repository.save(ent);
    }

    private void updateObject(FatherModel oldObject, FatherModel newObject) {
        try {
            if (!oldObject.getClass().equals(newObject.getClass())) {
                throw new IllegalArgumentException("Los objetos deben ser de la misma clase.");
            }

            Class<FatherModel> clazz = (Class<FatherModel>) oldObject.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true); // Permite modificar campos privados
                Object newValue = field.get(newObject);
                if (newValue != null) {
                    field.set(oldObject, newValue);
                }
            }
        } catch (IllegalAccessException e) {

        }
    }

}
