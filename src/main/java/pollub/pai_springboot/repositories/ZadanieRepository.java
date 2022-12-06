/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pollub.pai_springboot.repositories;
import pollub.pai_springboot.entities.Zadanie;
import org.springframework.data.repository.CrudRepository;
/**
 *
 * @author adaml
 */
public interface ZadanieRepository extends CrudRepository<Zadanie, Long> {

    public Iterable<Zadanie> findByWykonane(boolean wykonane);

    public Iterable<Zadanie> findByKosztLessThan(double koszt);

    public Iterable<Zadanie> findByKosztBetween(double min, double max);

}
