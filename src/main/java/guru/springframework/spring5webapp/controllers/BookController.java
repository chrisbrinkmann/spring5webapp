package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        /**
         * We need to load the books records from the repo into the Model.
         * This will allow Thymeleaf to access the Book object in the template
         */
        model.addAttribute("books", bookRepository.findAll());

        /**
         * The return value tells the view resolver to look for
         * a template in resources/templates/books/list
         */
        return "books/list";
    }
}
