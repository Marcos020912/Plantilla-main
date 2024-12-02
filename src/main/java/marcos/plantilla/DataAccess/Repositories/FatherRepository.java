/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marcos.plantilla.DataAccess.Repositories;

import marcos.plantilla.DataAccess.Models.FatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FatherRepository<E extends FatherModel, I> extends JpaRepository<E, I> {

}
