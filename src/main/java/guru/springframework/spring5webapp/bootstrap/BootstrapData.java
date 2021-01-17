package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    /**
     * When Spring implements this component into the Spring context,
     * it will do dependency injection into the constructor for an instance
     * of the AuthorRepository and BookRepository
     */

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Author rod = new Author("Rod", "Johnson");
        Book ddd = new Book("Domain Driven Design", "123123");
        Book noEJB = new Book("J2EE Development without EJB", "2342463");
        Publisher randomHouse = new Publisher("Random House", "123 Main St., Ny, New York 212345");

        publisherRepository.save(randomHouse);

        /**
         * Note: Trying to save/persist an object with a reference to a
         * transient object (one that has not been saved/persisted) will
         * result in a TransientObjectException.
         *
         * In this case, we must persist our Publisher before we persist
         * our books, because the Publisher property of the book is otherwise
         * uninstantiated... I think?
         *
         * We must persist our authors before the books
         */

        // add books to author objects, persist authors
        eric.getBooks().add(ddd);
        rod.getBooks().add(noEJB);
        authorRepository.save(eric);
        authorRepository.save(rod);

        // add authors & publisher to book objects, persist books
        ddd.getAuthors().add(eric);
        ddd.setPublisher(randomHouse);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(randomHouse);
        bookRepository.save(ddd);
        bookRepository.save(noEJB);

        // add books to publisher object, persist publisher
        randomHouse.getBooks().add(ddd);
        randomHouse.getBooks().add(noEJB);
        publisherRepository.save(randomHouse);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Number of books published by Random House: " + randomHouse.getBooks().size());
    }
}
