package guru.springframework.spring7webapp.bootstrap;

import guru.springframework.spring7webapp.domain.Author;
import guru.springframework.spring7webapp.domain.Book;
import guru.springframework.spring7webapp.domain.Publisher;
import guru.springframework.spring7webapp.repositories.AuthorRepository;
import guru.springframework.spring7webapp.repositories.BookRepository;
import guru.springframework.spring7webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author Harry = new Author();
        Harry.setFirstName("Harry");
        Harry.setLastName("Ward");

        Book computer = new Book();
        computer.setTitle("Computer Science");
        computer.setIsbn("123456789");

        Publisher publisher = new Publisher();
        publisher.setAddress("123 Main St.");
        publisher.setCity("New York");
        publisher.setState("NY");
        publisher.setName("Books Publishing");
        publisher.setZipCode(22485);

        Author HarrySaved = authorRepository.save(Harry);
        Book HarrySavedBook = bookRepository.save(computer);
        Publisher savedPublisher = publisherRepository.save(publisher);

        HarrySaved.getBooks().add(HarrySavedBook);
        authorRepository.save(HarrySaved);
        //HarrySaved.setPublisher(publisher); need to figure out how to get author object to connect with publisher
        HarrySavedBook.setPublisher(savedPublisher);
        bookRepository.save(HarrySavedBook);

        System.out.println("In BootStrap");
        System.out.println("Author Count: " +  authorRepository.count());
        System.out.println("Book Count: " +  bookRepository.count());
        System.out.println("Publisher Count: " +  publisherRepository.count());
    }
}
