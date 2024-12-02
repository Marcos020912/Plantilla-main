/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marcos.plantilla.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    //Agregar los cors requeridos en el proyecto
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Añade los orígenes permitidos aquí
                .allowedMethods("*") // Permite todos los métodos HTTP
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Permite credenciales si es necesario
    }
}
