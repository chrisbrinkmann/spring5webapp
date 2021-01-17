package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    /**
     * We just declare this interface, and then at runtime Spring Data JPA
     * will provide the implementation, which gives us all of the CRUD
     * stuff from CrudRepository for doing DB operations.
     */
}
