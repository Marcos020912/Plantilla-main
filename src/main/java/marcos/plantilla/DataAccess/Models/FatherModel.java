/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marcos.plantilla.DataAccess.Models;

import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 *
 * @author maalf
 */
@MappedSuperclass
@EntityScan(basePackages = "back.demo.entities")
public abstract class FatherModel implements Serializable {
}
